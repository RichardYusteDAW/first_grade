/* DATA BASE */
CREATE TABLE Clientes (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Clientes_confidencial (
    id_cliente INT PRIMARY KEY,
    datos_confidenciales VARCHAR(255),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

CREATE TABLE Productos (
    codigo_producto VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    cantidad_en_stock INT
);

CREATE TABLE Pedidos (
    codigo_pedido INT PRIMARY KEY,
    id_cliente INT,
    fecha_pedido DATE,
    total DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

CREATE TABLE Detalles_pedido (
    codigo_pedido INT,
    codigo_producto VARCHAR(15),
    cantidad INT,
    precio_unidad DECIMAL(10,2),
    PRIMARY KEY (codigo_pedido, codigo_producto),
    FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido),
    FOREIGN KEY (codigo_producto) REFERENCES Productos(codigo_producto)
);


INSERT INTO Clientes (id, nombre, email) VALUES (1, 'Juan Pérez', 'juan.perez@example.com');
INSERT INTO Clientes (id, nombre, email) VALUES (2, 'María López', 'maria.lopez@example.com');
INSERT INTO Clientes (id, nombre, email) VALUES (3, 'Carlos Ruiz', 'carlos.ruiz@example.com');
INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P001', 'Laptop HP', 750.00, 15);
INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P002', 'Mouse Inalámbrico', 25.00, 50);
INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P003', 'Teclado Mecánico', 100.00, 30);
INSERT INTO Pedidos (codigo_pedido, id_cliente, fecha_pedido) VALUES (1, 1, DATE '2024-01-15');
INSERT INTO Pedidos (codigo_pedido, id_cliente, fecha_pedido) VALUES (2, 2, DATE '2024-01-16');
INSERT INTO Pedidos (codigo_pedido, id_cliente, fecha_pedido) VALUES (3, 3, DATE '2024-01-17');
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad) VALUES (1, 'P001', 1, 750.00);
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad) VALUES (1, 'P002', 2, 25.00);
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad) VALUES (2, 'P003', 1, 100.00);
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad) VALUES (3, 'P002', 2, 100.00);



/* 
Ejericio 1.
Crea un usuario 'admin' que tenga acceso desde cualquier ip, con contraseña 'admin1234' 
y otórgale permisos para acceder y modificar todas las bases de datos desde su host, 
pero sin el permiso para borrar bases de datos o tablas.

Concede los privilegios de SELECT, INSERT, y UPDATE sobre la base de datos 'desarrollo'
y restringe su acceso a la tabla 'clientes_confidencial' dentro de la misma base, pero esta vez desde cualquier host.

Establece la política de caducidad de la contraseña a 60 días 
y asegúrate de que su cuenta se bloquee si hay 3 intentos fallidos de inicio de sesión.
*/

CREATE USER 'admin'@'%' IDENTIFIED BY 'admin1234';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
REVOKE DROP ON *.* FROM 'admin'@'localhost';

GRANT SELECT, INSERT, UPDATE ON desarrollo.* TO 'admin'@'%';
REVOKE ALL PRIVILEGES ON desarrollo.clientes_confidencial FROM 'admin'@'%';

ALTER USER 'admin'@'%' PASSWORD EXPIRE INTERVAL 60 DAY;
ALTER USER 'admin'@'%' FAILED_LOGIN_ATTEMPTS 3 ACCOUNT LOCK;

/* Ejericio 2.
Crea un usuario 'audit' que solo tenga acceso desde la ip 192.168.0.25,
con contraseña 'audit1234' con acceso de solo lectura a todas las bases de datos,
excepto para la base de datos 'operaciones_sensibles'. 

Otorga al usuario 'audit' permisos para ejecutar reportes (SELECT) en la base de datos 'reportes_financieros' 
y permite la actualización (UPDATE) solo en la tabla 'revisiones_anuales'.

Cambia su contraseña  a 'SecureAudit2024', establece que expire cada 90 días, 
y configura la cuenta para que se bloquee tras 5 intentos fallidos de acceso.

Revoca cualquier privilegio que el usuario 'audit' pueda tener sobre la tabla 'transacciones_privadas' 
en la base de datos 'operaciones_sensibles', asegurando su exclusión total de esa área.
*/

CREATE USER 'audit'@'192.168.0.25' IDENTIFIED BY 'audit1234';
GRANT SELECT ON *.* TO 'audit'@'192.168.0.25';
REVOKE SELECT ON operaciones_sensibles.* FROM 'audit'@'192.168.0.25';

GRANT SELECT ON reportes_financieros.* TO 'audit'@'192.168.0.25';
GRANT UPDATE ON reportes_financieros.revisiones_anuales TO 'audit'@'192.168.0.25';

ALTER USER 'audit'@'192.168.0.25' IDENTIFIED BY 'SecureAudit2024';
ALTER USER 'audit'@'192.168.0.25' PASSWORD EXPIRE INTERVAL 90 DAY;
ALTER USER 'audit'@'192.168.0.25' FAILED_LOGIN_ATTEMPTS 5 ACCOUNT LOCK;

REVOKE ALL PRIVILEGES ON operaciones_sensibles.transacciones_privadas FROM 'audit'@'192.168.0.25';

/* Ejericio 3.
Crear un trigger que se active después de cada inserción en la tabla Detalles_pedido. 
Este trigger debe llamar a una función que calcule el total del pedido correspondiente en la tabla Pedidos.
*/
CREATE OR REPLACE TRIGGER trg_actualizar_total_pedido
AFTER INSERT ON Detalles_pedido
FOR EACH ROW
BEGIN
    calcular_total_pedido(:NEW.codigo_pedido);
END;

CREATE OR REPLACE FUNCTION calcular_total_pedido(new_cod_pedido INT) RETURN DECIMAL IS 
    total_pedido DECIMAL(10,2); # Declaración de variable
BEGIN
    SELECT SUM(cantidad * precio_unidad) INTO total_pedido
    FROM Detalles_pedido
    WHERE codigo_pedido = new_cod_pedido;
    
    UPDATE Pedidos
    SET total = total_pedido
    WHERE codigo_pedido = new_cod_pedido;
    
    RETURN total_pedido;
END;


/* Ejericio 4.
Crear un trigger que se active después de cada nuevo pedido en la tabla Pedidos. 
Este trigger debe llamar a un procedimiento que registre la acción de inserción en una tabla de log de pedidos,
con los campos codigo_pedido, accion (INSERT), y fecha (SYSDATE).
*/
CREATE OR REPLACE TRIGGER trg_log_pedido
AFTER INSERT ON Pedidos
FOR EACH ROW
BEGIN
    registrar_log_pedido(:NEW.codigo_pedido, 'INSERT');
END;

CREATE OR REPLACE PROCEDURE registrar_log_pedido(cod_pedido INT, accion VARCHAR(10))
BEGIN
    INSERT INTO Log_pedidos (codigo_pedido, accion, fecha)
    VALUES (cod_pedido, accion, SYSDATE);
END;


/* Ejericio 5.
Desarrollar un trigger que se active después de cada inserción en la tabla Detalles_pedido. 
Este trigger debe usar un cursor para verificar el stock de cada producto en el pedido y actualizar el stock en la tabla Productos.
*/
CREATE OR REPLACE TRIGGER trg_actualizar_stock
AFTER INSERT ON Detalles_pedido
FOR EACH ROW
DECLARE
    CURSOR c_stock_productos IS
        SELECT cantidad_en_stock 
        FROM Productos 
        WHERE codigo_producto = :NEW.codigo_producto;
BEGIN
    FOR r IN (c_stock_productos) LOOP
        UPDATE Productos 
        SET cantidad_en_stock = (r.cantidad_en_stock - :NEW.cantidad) 
        WHERE codigo_producto = :NEW.codigo_producto;
    END LOOP;
END;
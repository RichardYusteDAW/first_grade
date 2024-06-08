CREATE TABLE Clientes (
id INT PRIMARY KEY,
nombre VARCHAR(100),
email VARCHAR(100),
telefono VARCHAR(20)
);
CREATE TABLE Productos (
codigo_producto VARCHAR(15) PRIMARY KEY,
nombre VARCHAR(100),
precio DECIMAL(10,2),
cantidad_en_stock INT
);
CREATE TABLE Pedidos (
codigo_pedido INT PRIMARY KEY,
fecha_pedido DATE,
fecha_entrega DATE,
estado VARCHAR(20));
CREATE TABLE Detalles_pedido (
codigo_pedido INT,
codigo_producto VARCHAR(15),
cantidad INT,
precio_unidad DECIMAL(10,2),
entregado NUMBER(1) DEFAULT 0,
PRIMARY KEY (codigo_pedido, codigo_producto),
FOREIGN KEY (codigo_pedido) REFERENCES Pedidos(codigo_pedido),
FOREIGN KEY (codigo_producto) REFERENCES Productos(codigo_producto)
);

INSERT INTO Clientes VALUES (1,'Juan Perez', 'juan@example.com', '123456789');
INSERT INTO Clientes VALUES (2,'María López', 'maria@example.com', '987654321');
INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P1', 'Producto 1', 10.99, 100);
INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P2', 'Producto 2', 20.50, 50);
INSERT INTO Pedidos VALUES (1,TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-05', 'YYYY-MM-DD'), 'Pendiente');
INSERT INTO Pedidos VALUES (2,TO_DATE('2024-04-02', 'YYYY-MM-DD'), TO_DATE('2024-04-06', 'YYYY-MM-DD'), 'En Proceso');
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad, entregado) VALUES (1, 'P1', 2, 10.99, 1);
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad, entregado) VALUES (1, 'P2', 3, 20.50, 1);
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad, entregado) VALUES (2, 'P1', 1, 10.99, 1);

/*
EJERCICIO 1. Crea un trigger que se active después de insertar un nuevo cliente en la tabla Clientes. 
El trigger debe registrar la acción de inserción en una tabla llamada “auditoría de clientes”. 

Esa tabla tiene los siguientes campos:
auditoria_clientes(id_auditoria number, id_cliente number, accion varchar2(30), fecha date )
*/
CREATE TABLE auditoria_clientes(
    id_auditoria NUMBER PRIMARY KEY,
    id_cliente NUMBER,
    accion VARCHAR2(30),
    fecha DATE
);

CREATE SEQUENCE auditoria_clientes_seq
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER cliente_insertado
AFTER INSERT ON Clientes
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_clientes(id_auditoria, id_cliente, accion, fecha)
    VALUES (auditoria_clientes_seq.NEXTVAL, :NEW.id, 'Cliente insertado', SYSDATE);
    /*VALUES (1, 2, 'Cliente insertado', '2023-05-13');*/
END;
/

/* Comporbamos */
INSERT INTO Clientes VALUES (3,'Pedro', 'pedro@example.com', '123456789');
SELECT * FROM auditoria_clientes;


/*
EJERCICIO 2. Crea un trigger que se active después de insertar o actualizar un detalle de pedido en la tabla Detalles_pedido.
El trigger debe actualizar automáticamente el campo cantidad_en_stock del producto
correspondiente en la tabla Productos basado en la cantidad vendida en el detalle de pedido.
*/
CREATE OR REPLACE TRIGGER actualizar_stock
AFTER INSERT OR UPDATE ON Detalles_pedido
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE Productos
        SET cantidad_en_stock = cantidad_en_stock - :NEW.cantidad
        WHERE codigo_producto = :NEW.codigo_producto;
    ELSIF UPDATING THEN
        UPDATE Productos
        SET cantidad_en_stock = cantidad_en_stock + :OLD.cantidad - :NEW.cantidad
        WHERE codigo_producto = :NEW.codigo_producto;
    END IF;
END;
/

/* Comprobamos */
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad) VALUES (2, 'P2', 2, 10.99);
SELECT * FROM Productos;

/*
EJERCICIO 3. Crea un trigger que se active después de insertar o actualizar un detalle de
pedido en la tabla Detalles_pedido. El trigger debe calcular automáticamente el total de un
pedido sumando el precio total de todos los detalles de pedido asociados al pedido y
actualizar el campo total en la tabla Pedidos.
*/
CREATE OR REPLACE TRIGGER calcular_total_pedido
AFTER INSERT OR UPDATE ON Detalles_pedido
FOR EACH ROW
BEGIN
    UPDATE Pedidos
    SET total = (SELECT SUM(cantidad * precio_unidad) FROM Detalles_pedido WHERE codigo_pedido = :NEW.codigo_pedido)
    WHERE codigo_pedido = :NEW.codigo_pedido;
END;
/

/*
EJERCICIO 4. Crea un trigger que se active después de insertar o actualizar un detalle de
pedido en la tabla Detalles_pedido. El trigger debe verificar si todos los productos de un
pedido han sido entregados y, si es así, actualizar automáticamente el estado del pedido a
"Entregado" en la tabla Pedidos.
*/
CREATE OR REPLACE TRIGGER verificar_entrega_pedido
AFTER INSERT OR UPDATE ON Detalles_pedido
FOR EACH ROW
DECLARE
    v_total_productos INT;
    v_total_entregados INT;
BEGIN
    SELECT COUNT(*)
    INTO v_total_productos
    FROM Detalles_pedido
    WHERE codigo_pedido = :NEW.codigo_pedido;

    SELECT COUNT(*)
    INTO v_total_entregados
    FROM Detalles_pedido
    WHERE codigo_pedido = :NEW.codigo_pedido AND entregado = 1;

    IF v_total_productos = v_total_entregados THEN
        UPDATE Pedidos
        SET estado = 'Entregado'
        WHERE codigo_pedido = :NEW.codigo_pedido;
    END IF;
END;
/

/*
EJERCICIO 5. Crea un trigger que, después de insertar un nuevo pedido en la tabla "Pedidos",
actualice la cantidad en stock de todos los productos asociados a ese pedido en la tabla
"Detalles_pedido". Utiliza un cursor para recorrer los detalles del pedido y actualizar la
cantidad en stock de cada producto.
*/
CREATE OR REPLACE TRIGGER actualizar_stock_pedido
AFTER INSERT ON Pedidos
FOR EACH ROW
DECLARE
    CURSOR c_detalle_pedido IS
        SELECT codigo_producto, cantidad
        FROM Detalles_pedido
        WHERE codigo_pedido = :NEW.codigo_pedido;
    v_codigo_producto Productos.codigo_producto%TYPE;
    v_cantidad Detalles_pedido.cantidad%TYPE;
BEGIN
    OPEN c_detalle_pedido;
    LOOP
        FETCH c_detalle_pedido INTO v_codigo_producto, v_cantidad;
        EXIT WHEN c_detalle_pedido%NOTFOUND;
        UPDATE Productos
        SET cantidad_en_stock = cantidad_en_stock - v_cantidad
        WHERE codigo_producto = v_codigo_producto;
    END LOOP;
    CLOSE c_detalle_pedido;
END;
/

CREATE OR REPLACE TRIGGER actualizar_stock_pedido_with_for
AFTER INSERT ON pedidos
FOR EACH ROW
DECLARE
    CURSOR c_cursor IS
        SELECT * FROM detalles_pedido WHERE codigo_pedido = :NEW.codigo_pedido;
    r_detalles detalles_pedido%ROWTYPE;

BEGIN
    FOR r_detalles IN c_cursor LOOP
        UPDATE productos
        SET cantidad_en_stock = cantidad_en_stock - r_detalles.cantidad
        WHERE codigo_producto = r_detalles.codigo_producto;
    END LOOP;
END;
/

INSERT INTO Productos (codigo_producto, nombre, precio, cantidad_en_stock) VALUES ('P2', 'Producto 2', 20.50, 50);
INSERT INTO Pedidos VALUES (1,TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-05', 'YYYY-MM-DD'), 'Pendiente');
INSERT INTO Detalles_pedido (codigo_pedido, codigo_producto, cantidad, precio_unidad, entregado) VALUES (1, 'P1', 2, 10.99, 1);

/*actualice la cantidad en stock de todos los productos asociados a ese pedido en la tabla
"Detalles_pedido". Utiliza un cursor para recorrer los detalles del pedido y actualizar la
cantidad en stock de cada producto.*/
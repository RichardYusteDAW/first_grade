CREATE TABLE departamentos (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(50)
);


CREATE TABLE empleados (
	id NUMBER PRIMARY KEY,
	nombre VARCHAR2(50),
	salario NUMBER,
    departamento_id NUMBER,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)

);



INSERT INTO departamentos (id, nombre) VALUES (1, 'Ventas');
INSERT INTO departamentos (id, nombre) VALUES (2, 'Marketing');
INSERT INTO departamentos (id, nombre) VALUES (3, 'Tecnología');

INSERT INTO empleados (id, nombre, salario, departamento_id) VALUES (1, 'Juan', 3000, 1);
INSERT INTO empleados (id, nombre, salario, departamento_id) VALUES (2, 'María', 3500, 1);
INSERT INTO empleados (id, nombre, salario, departamento_id) VALUES (3, 'Pedro', 3200, 2);

/*
EJERCICIO 1. Escribe un cursor que seleccione empleados junto con el nombre de su departamento. 
Después recorre dicho cursor y muestra su contenido.
*/
DECLARE
    CURSOR c_empleados IS
        SELECT e.nombre, e.salario, d.nombre AS departamento
        FROM empleados e
        JOIN departamentos d
        ON e.departamento_id = d.id;
BEGIN
    FOR empleado IN c_empleados LOOP
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || empleado.nombre || ', Salario: ' || empleado.salario || ', Departamento: ' || empleado.departamento);
    END LOOP;
END;
/

/*
EJERCICIO 2. Escribe un cursor que seleccione empleados de un departamento específico que se le pase como parámetro 
y muestre su nombre y salario.
*/
DECLARE
    CURSOR c_empleados (id_departamento NUMBER) IS
        SELECT nombre, salario
        FROM empleados
        WHERE departamento_id = id_departamento;
BEGIN
    FOR empleado IN c_empleados(1) LOOP
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || empleado.nombre || ', Salario: ' || empleado.salario);
    END LOOP;
END;
/

# EJERCICIO 3. Escribe un cursor que muestre el nombre y salario de los empleados hasta que el salario sea mayor que 4000.
DECLARE
    CURSOR c_empleados IS
        SELECT nombre, salario
        FROM empleados;
BEGIN
    FOR empleado IN c_empleados LOOP
        EXIT WHEN empleado.salario > 4000;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || empleado.nombre || ', Salario: ' || empleado.salario);
    END LOOP;
END;
/

/*
# EJERCICIO 4. Escribe un cursor que seleccione empleados y muestre su nombre y salario, 
manejando la excepción de cuando no hay datos disponibles.
*/
DECLARE
    CURSOR c_empleados IS
        SELECT nombre, salario
        FROM empleados;
BEGIN
    FOR empleado IN c_empleados LOOP
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || empleado.nombre || ', Salario: ' || empleado.salario);
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No hay datos disponibles.');
END;
/

# EJERCICIO 5. Escribe un cursor que cuente el número de empleados y muestre el total.
DECLARE
    CURSOR c_empleados IS
        SELECT COUNT(*) AS total_empleados
        FROM empleados;
    total NUMBER;
BEGIN
    OPEN c_empleados;
    FETCH c_empleados INTO total;
    CLOSE c_empleados;
    DBMS_OUTPUT.PUT_LINE('Total de empleados: ' || total);
END;
/

# EJERCICIO 6. Escribe un cursor que actualice el salario de todos los empleados en un departamento específico.
DECLARE
    CURSOR c_empleados IS
        SELECT id, salario
        FROM empleados
        WHERE departamento_id = 1
        FOR UPDATE OF salario;
BEGIN
    FOR empleado IN c_empleados LOOP
        UPDATE empleados
        SET salario = empleado.salario * 1.1
        WHERE CURRENT OF c_empleados;
    END LOOP;
    COMMIT;
END;
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
EJERCICIO 1. Crea un procedimiento para insertar un nuevo empleado. 
Este procedimiento recibirá como parámetros el nombre, salario y departamento del nuevo empleado 
y lo insertará en la tabla “empleados”.
*/
CREATE OR REPLACE PROCEDURE insertar_empleado (nombre VARCHAR2, salario NUMBER, departamento NUMBER)
IS
BEGIN
    INSERT INTO empleados (nombre, salario, departamento_id)
    VALUES (nombre, salario, departamento);
    COMMIT;
END;

/*
# EJERCICIO 2. Crea un procedimiento para actualizar el salario de un empleado: 
Este procedimiento recibirá como parámetros el ID del empleado y el nuevo salario, 
y actualizará el salario del empleado correspondiente en la tabla empleados.
*/
CREATE OR REPLACE PROCEDURE actualizar_salario (id_empleado NUMBER, nuevo_salario NUMBER)
IS
BEGIN
    UPDATE empleados
    SET salario = nuevo_salario
    WHERE id = id_empleado;
    COMMIT;
END;

/*
# EJERCICIO 3. Crear un procedimiento para calcular el salario promedio de un departamento: 
Este procedimiento recibirá como parámetro el ID del departamento 
y calculará el salario promedio de todos los empleados en ese departamento.
*/
CREATE OR REPLACE PROCEDURE salario_promedio_departamento (id_departamento NUMBER)
IS
    salario_total NUMBER := 0;
    contador NUMBER := 0;
    salario_promedio NUMBER;
BEGIN
    FOR empleado IN (SELECT salario FROM empleados WHERE departamento_id = id_departamento) LOOP
        salario_total := salario_total + empleado.salario;
        contador := contador + 1;
    END LOOP;
    salario_promedio := salario_total / contador;
    DBMS_OUTPUT.PUT_LINE('El salario promedio del departamento ' || id_departamento || ' es: ' || salario_promedio);
END;

CREATE OR REPLACE PROCEDURE salario_promedio_departamento (id_departamento NUMBER)
IS
    promedio_salario number;
BEGIN
    SELECT AVG(salario) INTO promedio_salario
    FROM empleados
    WHERE departamento_id = id_departamento;

    DBMS_OUTPUT.PUT_LINE('El salario promedio del departamento ' || id_departamento || ' es: ' || promedio_salario);
END;

/*
# EJERCICIO 4. Crear un procedimiento para eliminar un empleado: 
Este procedimiento recibirá como parámetro el ID del empleado 
y lo eliminará de la tabla empleados.
*/

CREATE OR REPLACE PROCEDURE eliminar_empleado (id_empleado NUMBER)
IS
BEGIN
    DELETE FROM empleados
    WHERE id = id_empleado;
    COMMIT;
END;
# EJERCICIO 1. Crea una función que sume dos números y devuelva el resultado. Realiza una llamada a dicha función desde un bloque anónimo.
--Función:
CREATE OR REPLACE FUNCTION sumar_numeros (a NUMBER, b NUMBER) 
RETURN NUMBER 
IS
BEGIN
    RETURN a + b;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(sumar_numeros(5, 3));
END;

# EJERCICIO 2. Crear una función que calcule el área de un triángulo dado su base y altura. Realiza una llamada a dicha función desde un bloque anónimo.
--Función:
CREATE OR REPLACE FUNCTION area_triangulo (base NUMBER, altura NUMBER)
RETURN NUMBER
IS
BEGIN
    RETURN (base * altura) / 2;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(area_triangulo(5, 3));
END;

# EJERCICIO 3. Crea una función que determine si un número dado es par o impar. Realiza una llamada a dicha función desde un bloque anónimo.
CREATE OR REPLACE FUNCTION par_impar (num NUMBER)
RETURN VARCHAR2
IS
BEGIN
    IF MOD(num, 2) = 0 THEN
        RETURN 'Par';
    ELSE
        RETURN 'Impar';
    END IF;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(par_impar(5));
    DBMS_OUTPUT.PUT_LINE(par_impar(6));
END;

# EJERCICIO 4. Crea una función que calcule el factorial de un número dado. Realiza una llamada a dicha función desde un bloque anónimo.
CREATE OR REPLACE FUNCTION factorial (num NUMBER)
RETURN NUMBER
IS
    resultado NUMBER := 1;
BEGIN
    FOR i IN 1..num LOOP
        resultado := resultado * i;
    END LOOP;
    RETURN resultado;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(factorial(5));
END;

# EJERCICIO 5. Crea una función que determine si un número dado es primo o no. Realiza una llamada a dicha función desde un bloque anónimo.
CREATE OR REPLACE FUNCTION es_primo (num NUMBER)
RETURN VARCHAR2
IS
    contador NUMBER := 0;
BEGIN
    FOR i IN 1..num LOOP
        IF MOD(num, i) = 0 THEN
            contador := contador + 1;
        END IF;
    END LOOP;
    IF contador = 2 THEN
        RETURN 'Primo';
    ELSE
        RETURN 'No Primo';
    END IF;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(es_primo(5));
    DBMS_OUTPUT.PUT_LINE(es_primo(6));
END;

# EJERCICIO 6. Crea una función que determine si una cadena dada es un palíndromo o no. Realiza una llamada a dicha función desde un bloque anónimo.
CREATE OR REPLACE FUNCTION es_palindromo (cadena VARCHAR2)
RETURN VARCHAR2
IS
    cadena_invertida VARCHAR2(100);
BEGIN
    FOR i IN REVERSE 1..LENGTH(cadena) LOOP
        cadena_invertida := cadena_invertida || SUBSTR(cadena, i, 1);
    END LOOP;
    IF cadena = cadena_invertida THEN
        RETURN 'Palíndromo';
    ELSE
        RETURN 'No Palíndromo';
    END IF;
END;
/
--Bloque anónimo:
BEGIN
    DBMS_OUTPUT.PUT_LINE(es_palindromo('reconocer'));
    DBMS_OUTPUT.PUT_LINE(es_palindromo('hola'));
END;
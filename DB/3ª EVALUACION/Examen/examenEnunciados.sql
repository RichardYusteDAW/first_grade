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




/* Ejericio 3.
Crear un trigger que se active después de cada inserción en la tabla Detalles_pedido. 
Este trigger debe llamar a una función que calcule el total del pedido correspondiente en la tabla Pedidos.
*/




/* Ejericio 4.
Crear un trigger que se active después de cada nuevo pedido en la tabla Pedidos. 
Este trigger debe llamar a un procedimiento que registre la acción de inserción en una tabla de log de pedidos,
con los campos codigo_pedido, accion (INSERT), y fecha (SYSDATE).
*/




/* Ejericio 5.
Desarrollar un trigger que se active después de cada inserción en la tabla Detalles_pedido. 
Este trigger debe usar un cursor para verificar el stock de cada producto en el pedido y actualizar el stock en la tabla Productos.
*/
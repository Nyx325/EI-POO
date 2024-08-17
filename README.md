# EI-POO
## """Desplegar"""

### Crear BD y Usuarios
Se debe ejecutar la base de datos en `./bd` y asegurarse de 
poder crear algun usuario. Eh aqui un ejemplo:
```sql
DROP USER IF EXISTS 'rubenrs'@'localhost';
CREATE USER 'rubenrs'@'localhost' identified by "1234";
GRANT ALL PRIVILEGES ON Muestreos.* TO 'rubenrs'@'localhost';
FLUSH PRIVILEGES;

DROP USER IF EXISTS 'marictt'@'localhost';
CREATE USER 'marictt'@'localhost' identified by "1234";
GRANT ALL PRIVILEGES ON Muestreos.* TO 'marictt'@'localhost';
FLUSH PRIVILEGES;

DROP USER IF EXISTS 'marcuspa'@'localhost';
CREATE USER 'marcuspa'@'localhost' identified by "1234";
GRANT ALL PRIVILEGES ON Muestreos.* TO 'marcuspa'@'localhost';
FLUSH PRIVILEGES;
```

Ahora también hay una función que crea el usuario con sus permisos de
acuerdo a la posicion, pero para que funcione he visto que necesito 
crear el script o al menos el procedimiento supongo como `root` para
que funcione el procedimiento
```sql
DROP USER IF EXISTS 'rubenrs'@'localhost';
CALL CrearUsuario("rubenrs", "1234", "Dirección");
```

### Asignar un registro de signatario de la BD con el cliente creado
En el login se solicitaran los usuarios y contraseñas colocados
en mysql. También asegurese de incluir el campo de posicion,
y usuario en la bd, el usuario obvio correspondiente al usuario
creado en el punto anterior
```sql
INSERT INTO Signatario (idSignatario, primNombre, segNombre, apellidoP, apellidoM,fIngreso,fNacimiento, posicion, usuario) VALUES
(1,"Rubén","Omar","Román","Salinas", NOW(), "2004-05-28", "Dirección", "rubenrs@localhost"),
(2,"Maricruz",NULL,"Toledano","Torres", NOW(), "2004-09-12", "Sindicalizado", "marictt@localhost"),
(3,"Ian","Marcus","Prado","Acevedo", NOW(), "2000-06-10",  "Muestreo", "marcuspa@localhost");
```

## TODO list
### Del programa
- [x] Tarea 1: CRUD sitios
    1. [ ] Enlazar clientes a nuevos sitios o existenes
    2. [ ] Reenlazar clientes y muestras o modificar apropiadamente
- [x] Tarea 2: Personalizar vista sitios segun permisos
    1. [ ] Incluir patron observer para busquedas
    2. [ ] Hacer que no se vea desproporcionado el menu
- [x] Tarea 3: CRUD clientes
- [x] Tarea 4: CRUD Pruebas
    1. [x] Vista que asigne pruebas a parámetros
- [ ] Tarea 5: Vista que asigne clientes a sitios
- [x] Tarea 6: Vista bitácora
- [ ] Tarea 7: Ventanas de captura de resultados
- [x] Tarea 8: Gestion muestras
    1. [x] Selector de signatario
- [ ] Tarea 9: Generacion de graficos

### Del script
- [x] Tarea 1: Funcion #4 del script
- [x] Tarea 2: Funcion #10 del script
- [x] Tarea 3: Pruebas de to'
- [x] Tarea 4: Transacciones distribuidas y tipos bloqueo

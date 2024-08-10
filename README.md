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
- [ ] Tarea 1: CRUD sitios
- [ ] Tarea 2: Personalizar vista sitios segun permisos
- [ ] Tarea 3: CRUD clientes
- [ ] Tarea 4: Vista que asigne pruebas a parámetros
- [ ] Tarea 5: Vista que asigne clientes a sitios
- [ ] Tarea 6: Vista bitácora
- [ ] Tarea 7: Ventanas de captura de resultados

### Del script
- [ ] Tarea 1: Funcion #4 del script
- [ ] Tarea 2: Funcion #10 del script
- [ ] Tarea 3: Pruebas de to'

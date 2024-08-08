# EI-POO
## """Desplegar"""
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

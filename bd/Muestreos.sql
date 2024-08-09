DROP DATABASE IF EXISTS Muestreos;
CREATE DATABASE Muestreos;
USE Muestreos;

# PADRES

CREATE TABLE Muestreos.Bitacora(
	folio INT PRIMARY KEY AUTO_INCREMENT,
	mensaje VARCHAR(511) NOT NULL
);

CREATE TABLE Muestreos.Cliente (
	idCliente int PRIMARY KEY auto_increment,
	nombre varchar(255) NOT NULL
);

INSERT INTO Cliente (idCliente, nombre) VALUES 
(1, "Pfizer"),
(2, "Nestle"),
(3, "Unilever"),
(4, "Procter & Gamble"),
(5, "Coca-Cola"),
(6, "PepsiCo"),
(7, "Merck"),
(8, "Novartis"),
(9, "GlaxoSmithKine"),
(10, "Johnson & Johnson"),
(11, "L'Oreal"),
(12, "Reckitt Benckiser"),
(13, "Colgate-Palmolive"),
(14, "Heinz"),
(15, "Kraft Foods"),
(16, "General Mills"),
(17, "Kellogg's"), 
(18, "Pepsi Bottling Group"),
(19, "Dr. Pepper Snapple Group"),
(20, "Red Bull"),
(21, "Monster Beverage"),
(22, "Bayer"),
(23, "Roche"),
(24, "AstraZeneca"),
(25, "Eli Lilly"),
(26, "Abbott"),
(27, "Sanofi"),
(28, "Boehringer Ingelheim"),
(29, "Bristol-Myers"),
(30, "3M"),
(31, "DuPont"),
(32, "Dow Chemical"),
(33, "Syngenta"),
(34, "Monsanto"),
(35, "BASF"),
(36, "ExxonMobil"),
(37, "Chevron"),
(38, "ConocoPhillips"),
(39, "Total"),
(40, "Shell");

CREATE TABLE Muestreos.Norma (
	idNorma INT PRIMARY KEY AUTO_INCREMENT,
	norma VARCHAR(100),
	unidades VARCHAR(32)
    DEFAULT "mg/L",
	tipoVentana INT
    DEFAULT 1
);

# Mediciones directas y Físicoquimicos
INSERT INTO Norma (idNorma, norma) VALUES
(1,"NMX-AA-014-1980"),
(2,"NMX-AA-003-1980"),
(3,"NMX-AA-005-SCFI-2013"),
(4,"NMX-AA-007-SCFI-2013"),
(5,"NMX-AA-008-SCFI-2016"),
(6,"NMX-AA-034-SCFI-2015"),
(7,"NMX-AA-028-SCFI-2021"),
(8,"NMX-AA-036-SCFI-2021"),
(9,"NMX-AA-073-SCFI-2001"),
(10,"NMX-AA-093-SCFI-2018"),
(11,"NMX-AA-072-SCFI-2001"),
(12,"NMX-AA-012-SCFI-2001"),
(13,"NMX-AA-006-SCFI-2010"),
(14,"Método: 4500-NH3. F. Standard Methods APHA AWWA. WEF. Ed. 21st. 2005"),
(15,"EPA 365.3 (1978)"),
(16,"NMX-AA-026-SCFI-2010"),
(17,"NMX-AA-004-SCFI-2013"),
(18,"NMX-AA-030/1-SCFI-2012");

# Espectrofotométricos UV/VIS/IR
INSERT INTO Norma (idNorma, norma) VALUES
(19,"NMX-AA-029-SCFI-2001"),
(20,"NMX-AA-079-SCFI-2001"),
(21,"NMX-AA-099-SCFI-2021"),
(22,"NMX-AA-044-SCFI-2014"),
(23,"NMX-AA-077-SCFI-2001"),
(24,"NMX-AA-039-SCFI-2001"),
(25,"NMX-AA-074-SCFI-2014"),
(26,"Método HACH (Método pyridine-pyrazalone) 8027 5° edición"),
(27,"Método HACH 10175 5° edición"),
(28,"NMX-AA-017-SCFI-2021");

# Microbiología
INSERT INTO Norma (idNorma, norma) VALUES
(29,"NMX-AA-042-SCFI-2015"),
(30,"NMX-AA-102-SCFI-2019"),
(31,"Método ASTM D-6503-99 (1999)"),
(32,"Método: 9223.B. Standard Methods APHA AWWA. WEF. Ed. 21st 2005"),
(33,"NMX-AA-113-SCFI-2012");

# Toxicidad
INSERT INTO Norma VALUES
(34,"NMX-AA-087-SCFI-2010", NULL, NULL),
(35,"NMX-AA-112-SCFI-2017 ()", NULL, NULL), /*Le falta un simbolo que viene en el doc)*/
(36,"Método 10200 H Standard Methods APHA. AWWA. WEF. Ed. 21st. 2005 (Método tricromático)", NULL, NULL);

# Espectrofotometria de absorción atómica
INSERT INTO Norma (idNorma, norma, unidades, tipoVentana) VALUES
(37,"NMX-AA-051-SCFI-2016", "mg/L", NULL);

# Otros
INSERT INTO Norma (idNorma, norma, unidades, tipoVentana) VALUES
(38,"XML-AA-001-2024", "mg/L", NULL),
(39,"XML-AA-002-2024", "mg/L", NULL),
(40,"XML-AA-003-2024", "mg/L", NULL);

CREATE TABLE Muestreos.Signatario(
	idSignatario INT PRIMARY KEY AUTO_INCREMENT,
	primNombre VARCHAR(16),
	segNombre VARCHAR(16),
	apellidoP VARCHAR(16),
	apellidoM VARCHAR(16),
	sueldo FLOAT DEFAULT 1000,
	bono FLOAT DEFAULT 0,
	fIngreso DATE,
	fNacimiento DATE,
	posicion VARCHAR(32) DEFAULT "Pruebas",
        usuario VARCHAR(32) 
);

INSERT INTO Signatario (idSignatario, primNombre, segNombre, apellidoP, apellidoM,fIngreso,fNacimiento, posicion, usuario) VALUES
(1,"Rubén","Omar","Román","Salinas", NOW(), "2004-05-28", "Dirección", "rubenrs@localhost"),
(2,"Maricruz",NULL,"Toledano","Torres", NOW(), "2004-09-12", "Sindicalizado", "marictt@localhost"),
(3,"Ian","Marcus","Prado","Acevedo", NOW(), "2000-06-10",  "Muestreo", "marcuspa@localhost");

INSERT INTO Signatario (idSignatario, primNombre, segNombre, apellidoP, apellidoM, posicion) VALUES
(4,"Berenice","Celia","Huerta","Miralrío", "Sindicalizado"),
(5,"Cesar","Andrea","Rosas","Rodriguez", "Sindicalizado"),
(6,"Alberto","Alicia","Nuñez","Rodriguez", "Sindicalizado"),
(7,"Celia","Alma","Puentes","Maldonado", "Sindicalizado"),
(8,"Alicia","Berenice","Ortíz","Rosas", "Muestreo"),
(9,"Cesar","Armando","Solis","Sánchez", "Muestreo"),
(10,"Celia","Carlos","Huerta","Ortíz", "Pruebas"),
(11,"Alicia","Alberto","Moreno","Nodal", "Pruebas"),
(12,"Berenice","Armando","Maldonado","Rojas", "Pruebas"),
(13,"Carolina","Alfredo","Sánchez","Sotelo", "Pruebas"),
(14,"Alejandra","Carolina","Prado","Sotelo", "Pruebas"),
(15,"Benito","Alondra","Suarez","Flores", "Pruebas"),
(16,"Alfredo","Alma","Nuñez","Ramirez", "Pruebas"),
(17,"Carolina","Carmen","Quiñonez","Suarez", "Pruebas"),
(18,"Armando","Armando","Sánchez","Quiroga", "Pruebas"),
(19,"Carolina","Alicia","Rojas","Huerta", "Pruebas"),
(20,"Alberto","Alan","López","Pérez", "Pruebas"),
(21,"Benito","Benito","Rojas","Puentes", "Pruebas"),
(22,"Alma","Cesar","Nuñez","Fernandez", "Pruebas"),
(23,"Alejandra","Berenice","Hernández","Pichardo", "Pruebas"),
(24,"Cesar","Benito","Quiroga","Miranda", "Pruebas"),
(25,"Armando","Alejandra","Nodal","Nodal", "Pruebas"),
(26,"Alfredo","Alma","Felipez","Ortíz", "Pruebas"),
(27,"Armando","Carolina","Rebolledo","Quiñonez", "Pruebas"),
(28,"Benito","Alma","García","Rojas", "Pruebas"),
(29,"Carlos","Andrea","Sotelo","Nuñez", "Pruebas"),
(30,"Benito","Berenice","Moreno","Felipez", "Pruebas"),
(31,"Alondra","Alejandra","Rodriguez","Flores", "Pruebas"),
(32,"Cesar","Andrea","Quiroga","Dorantes", "Pruebas"),
(33,"Alan","Carolina","Puentes","Hernández", "Pruebas"),
(34,"Carolina","Alicia","Pichardo","Rojas", "Pruebas"),
(35,"Benito","Andrea","Hernández","Quiroga", "Pruebas"),
(36,"Alfredo","Berenice","Nodal","Huerta", "Pruebas"),
(37,"Alejandra","Celia","Prado","Gorostieta", "Pruebas"),
(38,"Alejandra","Berenice","Sotelo","Hernández", "Pruebas"),
(39,"Alondra","Celia","Hernández","Montez", "Pruebas"),
(40,"Alicia","Carolina","Rojas","Prado", "Pruebas");

CREATE TABLE Muestreos.Parametro(
	idParametro INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(45)
);

INSERT INTO Parametro VALUES
(1, "Metales pesados"),
(2, "Microbiología"),
(3, "Físico-químicos"),
(4, "Nutrientes"),
(5, "Orgánicos"),
(6, "Tóxicos"),
(7, "Biológicos"),
(8, "Inorgánicos"),
(9, "Sólidos"),
(10, "Líquidos"),
(11, "Gases"),
(12, "Radiactivos"),
(13, "Termidinámicos"),
(14, "Ópticos"),
(15, "Acústicos"),
(16, "Eléctricos"),
(17, "Magnéticos"),
(18, "Bioquímicos"),
(19, "Geológicos"),
(20, "Hidrogeológicos"),
(21, "Atmosféricos"),
(22, "Climáticos"),
(23, "Hidrológicos"),
(24, "Químicos"),
(25, "Sedimentológicos"),
(26, "Petroquímicos"),
(27, "Geoquímicos"),
(28, "Biogeoquímicos"),
(29, "Ecotoxicológicos"),
(30, "Fitoplantológicos"),
(31, "Zooplantológicos"),
(32, "Microbiología ambiental"),
(33, "Calidad del agua"),
(34, "Calidad del aire"),
(35, "Calidad del suelo"),
(36, "Residuos peligrosos"),
(37, "Residuos sólidos"),
(38, "Residuos líquidos"),
(39, "Residuos gaseosos"),
(40, "Contaminantes emergentes");

# Hijos
CREATE TABLE Muestreos.Sitio(
    idSitio INT PRIMARY KEY AUTO_INCREMENT,
    clave VArCHAR(256),
    nombre VARCHAR(256),
    cuenca VARCHAR(256),
    cAcuifero VARCHAR(256), /*Clave acuifero*/
    acuifero VARCHAR(256),
    organismo VARCHAR(256),
    dirLocal VARCHAR(256), /*Dirección local*/
    edo VARCHAR(256),
    municipio VARCHAR(256),
    cAgua VARCHAR(256), /*Cuerpo de agua*/
    tipoC VARCHAR(256), /*Tipo cuerpo*/
    subtipoC VARCHAR(256),
    latitud VARCHAR(256),
    longitud VARCHAR(256),
    uso VARCHAR(256),
    lugarT VARCHAR(256), /*Lugar toma*/
	idCliente INT,
	FOREIGN KEY (idCliente) 
		REFERENCES Muestreos.Cliente(idCliente)
	    ON DELETE SET NULL
	    ON UPDATE CASCADE
);


INSERT INTO Sitio VALUES
(1,"OCBAL2825","RIO TETLAMA ANTES RIO APATLACO","RIO AMACUZAC",NULL,NULL,
"OCBAL",NULL,"MORELOS","XOXOCOTLA","RIO APATLACO","LÓTICO",NULL,"18.702203",
"-99.240932","RIOS. PROTECCIÓN DE LA VIDA ACUATICA","CUERPO DE AGUA NATURAL", NULL);

INSERT INTO Sitio (idSitio, clave, nombre, edo, municipio, tipoC, latitud, longitud) VALUES
(2, "ABCD001RNL", "Río Papagallo", "Guerrero", "Acapulco", "Río", "16.8631", "-99.8763"),
(3, "ABCD002RNL", "Río Jamapa", "Veracruz", "Veracruz", "Río", "19.1722", "-96.1333"),
(4, "ABCD007", "Lago de Pátzcuaro", "Michoacán", "Morelia", "Río", "19.5042", "-96.1333"),
(5, "ABCD008", "Río Atoyac", "Puebla", "Puebla", "Río", "19.1722", "-96.1333");


CREATE TABLE Muestreos.Prueba (
	idPrueba INT PRIMARY KEY auto_increment,
	nombre varchar(100) NOT NULL,
	idParametro INT,
	FOREIGN KEY (idParametro) 
		REFERENCES Parametro (idParametro)
	    ON DELETE SET NULL
	    ON UPDATE CASCADE
);

INSERT INTO Prueba VALUES
(1,"Arsénico", 1),
(2,"Cadmio", 1),
(3,"Calcio", 1),
(4,"Cobre", 1),
(5,"Cromo", 1),
(6,"Fierro", 1),
(7,"Magnesio", 1),
(8,"Mercurio", 1),
(9,"Niquel", 1),
(10,"Potasio", 1),
(11,"Plomo", 1),
(12,"Zinc", 1);

CREATE TABLE Muestreos.DetalleNorma(
	folio INT PRIMARY KEY AUTO_INCREMENT,
	idNorma INT,
	idPrueba INT,
	FOREIGN KEY (idNorma) 
		REFERENCES Muestreos.Norma(idNorma)
		ON DELETE SET NULL 
		ON UPDATE CASCADE,
	FOREIGN KEY (idPrueba) 
		REFERENCES Muestreos.Prueba(idPrueba)
		ON DELETE SET NULL 
		ON UPDATE CASCADE
);

INSERT INTO DetalleNorma (idPrueba, idNorma) VALUES
(1, 37),
(2, 37),
(3, 37),
(4, 37),
(5, 37),
(6, 37),
(7, 37),
(8, 37),
(9, 37),
(10, 37),
(11, 37),
(12, 37);

CREATE TABLE Muestreos.DetalleSignatarios(
	idDetalle INT PRIMARY KEY AUTO_INCREMENT,
	idSignatario INT,
	idPrueba INT,
	FOREIGN KEY (idSignatario) REFERENCES Muestreos.Signatario(idSignatario)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	FOREIGN KEY (idPrueba) REFERENCES Muestreos.Prueba(idPrueba)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

INSERT INTO DetalleSignatarios VALUES 
(0,1,1),
(0,1,2),
(0,1,3),
(0,1,4),
(0,1,5),
(0,1,6),
(0,1,7),
(0,1,8),
(0,1,9),
(0,1,10),
(0,1,11),
(0,1,12),
(0,5,1),
(0,5,2),
(0,5,3),
(0,5,4),
(0,5,5),
(0,5,6),
(0,5,7),
(0,5,8),
(0,5,9),
(0,5,10),
(0,5,11),
(0,5,12);

CREATE TABLE Muestreos.Muestra(
	numControl VARCHAR(30) PRIMARY KEY,
	proyecto VARCHAR(100),
	fMuestreo DATE,
	hMuestreo TIME,
	fRecepcion DATE,
	muestreador INT,
	idSitio INT,
	FOREIGN KEY (muestreador) REFERENCES Muestreos.Signatario(idSignatario)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	FOREIGN KEY (idSitio) REFERENCES Muestreos.Sitio(idSitio)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

INSERT INTO Muestra VALUES
("240124220802", "Red Nacional de Moritoreo", "2024-01-24", "14:30:00","2024-01-24", 8, 1),
("123456778901", "Proyecto2", "2024-01-27", "13:28:00", "2024-01-27", 8, 2),
("123456778902", "Proyecto3", "2024-02-02", "12:28:00", "2024-02-02", 8, 3),
("123456778903", "Proyecto4", "2024-02-17", "14:08:00", "2024-02-17", 8, 4),
("123456778904", "Proyecto5", "2024-03-30", "12:12:00", "2024-03-30", 8, 5);

CREATE TABLE Muestreos.Resultados(
	folio INT PRIMARY KEY AUTO_INCREMENT,
	resultado VARCHAR(45),
	fAnalisis DATE,
	idSignatario INT,
	idPrueba INT,
	idNorma INT,
	numControl VARCHAR(30),
	FOREIGN KEY (idSignatario) REFERENCES Muestreos.Signatario(idSignatario)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	FOREIGN KEY (idPrueba) REFERENCES Muestreos.Prueba(idPrueba)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	FOREIGN KEY (idNorma) REFERENCES Muestreos.Norma(idNorma)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	FOREIGN KEY (numControl) REFERENCES Muestreos.Muestra(numControl)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);
 
INSERT INTO Resultados (resultado, fAnalisis, idSignatario, idPrueba, idNorma, numControl) VALUES
("<0.002", "2024-02-07", 5, 1, 37, "240124220802"),
("<0.05", "2024-01-30", 5, 2, 37, "240124220802"),
("<0.20", "2024-01-30", 5, 4, 37, "240124220802"),
("<0.50", "2024-01-30", 5, 5, 37, "240124220802"),
("<0.10", "2024-01-30", 5, 6, 37, "240124220802"),
("<0.05", "2024-01-30", 5, 7, 37, "240124220802"),
("<0.001", "2024-02-01", 5, 8, 37, "240124220802"),
("<0.20", "2024-01-30", 5, 9, 37, "240124220802"),
("<0.30", "2024-01-30", 5, 11, 37, "240124220802"),
("<0.20", "2024-01-30", 5, 12, 37, "240124220802");




SELECT *, SiglasSignatario(idSignatario) FROM Signatario


# FUNCIONES
# Convertir un DATETIME a una cadena que Java puede parsear en un
# LocalDateTime
# 1
DROP FUNCTION IF EXISTS LocalDateTimeFmt;
DELIMITER //
CREATE FUNCTION LocalDateTimeFmt(
	fecha DATETIME
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
    RETURN CONCAT(DATE(fecha),"T", LEFT(TIME(fecha),5));
END //

# Dado una entrada, concatenar los caracteres de porciento para su uso en LIKE
# 2
DROP FUNCTION IF EXISTS LikeFmt;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS LikeFmt(
	variable TEXT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
    RETURN CONCAT("%",variable,"%");
END //

# 3 
DROP FUNCTION IF EXISTS SiglasSignatario;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS SiglasSignatario(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE siglas TEXT;
	DECLARE nombre, nombre2, apeP, apeM TEXT;
	
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el signatario";
	END IF;

	SET nombre = (SELECT primNombre FROM Signatario WHERE idSignatario = id);
	SET nombre2 = (SELECT segNombre FROM Signatario WHERE idSignatario = id);
  	SET apeP = (SELECT apellidoP FROM Signatario WHERE idSignatario = id);
	SET apeM = (SELECT apellidoM FROM Signatario WHERE idSignatario = id);

	IF nombre2 IS NOT NULL THEN
		SET siglas = CONCAT(LEFT(nombre,1), LEFT(nombre2, 1), LEFT(apeP, 1), LEFT(apeM,1));
	ELSE
		SET siglas = CONCAT(LEFT(nombre,1), LEFT(apeP, 1), LEFT(apeM,1));
	END IF;
	
	RETURN siglas;
END //

SELECT *, SiglasSignatario(idSignatario) 
FROM Signatario WHERE idSignatario = 1;

# 4 TODO
DROP FUNCTION IF EXISTS CalcularBonoPorResultado;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS CalcularBonoPorResultado(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el signatario";
	END IF;

	RETURN "";
END //

#5
DROP FUNCTION IF EXISTS NombreCS;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS NombreCS(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE nombreSig TEXT;
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el signatario";
	END IF;

	SET nombreSig = (
		SELECT 
		CONCAT_WS(" ", primNombre,segNombre,apellidoP,apellidoM) 
		FROM Signatario WHERE idSignatario = id
	);
	RETURN nombreSig;
END //

# 6 
DROP FUNCTION IF EXISTS ClavePorIdSitio;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS ClavePorIdSitio(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE nombreSitio TEXT;
	IF NOT EXISTS (SELECT * FROM Sitio WHERE idSitio = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el sitio";
	END IF;

	SET nombreSitio = (SELECT clave FROM Sitio WHERE idSitio = id);

	RETURN nombreSitio;
END //

#7
DROP FUNCTION IF EXISTS NombrePruebaPorId;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS NombrePruebaPorId(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE nombreP TEXT;
	IF NOT EXISTS (SELECT nombre FROM Prueba WHERE idPrueba = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe la prueba";
	END IF;

	SET nombreP = (SELECT nombre FROM Prueba WHERE idPrueba = id);

	RETURN nombreP;
END //

#8 
DROP FUNCTION IF EXISTS NormaPorId;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS NormaPorId(
	id INT
)
RETURNS TEXT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE norma TEXT;
	IF NOT EXISTS (SELECT norma FROM Norma WHERE idNorma = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe la norma";
	END IF;

	SET norma = (SELECT nombre FROM Prueba WHERE idNorma = id);

	RETURN norma;
END //

# 9
DROP FUNCTION IF EXISTS UsuarioExiste;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS UsuarioExiste(
	usr TEXT
)
RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE res INT;
	SET res = 0;
	IF EXISTS (select user from user where user = usr) THEN 
		SET res = 1;
	END IF;

	RETURN res;
END //

#PROCEDIMIENTOS
# 1
DROP PROCEDURE IF EXISTS ResultadosInformePorNumeroControl;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS ResultadosInformePorNumeroControl(
	IN numC VARCHAR(30)
)
BEGIN
	IF NOT EXISTS (SELECT numControl FROM Muestra WHERE numControl = numC) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe la muestra";
	END IF;
	
	select Parametro.nombre As Parametro, Prueba.nombre As Prueba, Norma.unidades, 
	resultado, Norma.norma As MetodoAnalitico, fAnalisis As FechaAnálisis,
	SiglasSignatario(Signatario.idSignatario) 
	FROM Resultados 
	INNER JOIN Muestra ON Muestra.numControl = Resultados.numControl 
	INNER JOIN Prueba ON Resultados.idPrueba = Prueba.idPrueba 
	INNER JOIN Norma ON Resultados.idNorma = Norma.idNorma 
	INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro 
	INNER JOIN Signatario ON Resultados.idSignatario = Signatario.idSignatario
	WHERE Resultados.numControl = numC;	
END //

CALL ResultadosInformePorNumeroControl("240124220802");

# 2
DROP PROCEDURE IF EXISTS ParametrosPorSignatario;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS ParametrosPorSignatario(
	IN id INT
)
BEGIN
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = id) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el signatario";
	END IF;
	
	SELECT Parametro.idParametro, Parametro.nombre AS Parametro 
	FROM DetalleSignatarios 
	INNER JOIN Prueba ON DetalleSignatarios.idPrueba = Prueba.idPrueba 
	INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro 
	WHERE DetalleSignatarios.idSignatario = id 
	GROUP BY Parametro.nombre 
	ORDER BY Parametro.nombre;
END //

CALL ParametrosPorSignatario(1);

# 3
DROP PROCEDURE IF EXISTS PruebasPorSignatario;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS PruebasPorSignatario(
	IN idS INT,
	IN idP INT
)
BEGIN
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = idS) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el signatario";
	END IF;

	IF NOT EXISTS (SELECT * FROM Parametro WHERE idParametro = idP) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe el parámetro";
	END IF;
	
	SELECT Prueba.idPrueba, Prueba.Nombre, Parametro.idParametro FROM DetalleSignatarios 
	INNER JOIN Prueba ON DetalleSignatarios.idPrueba  = Prueba.idPrueba
	INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro 
	WHERE 
		DetalleSignatarios.idSignatario = idS
		AND Prueba.idParametro = idP;
END //

CALL Muestreos.PruebasPorSignatario(1, 1);

# 4
DROP PROCEDURE IF EXISTS TopPruebasMasRealizadasPorAnio;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS TopPruebasMasRealizadasPorAnio(
	IN Anio INT,
	IN top INT
)
BEGIN
	IF Anio <= 0 THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El año debe ser mayor a 0";
	END IF;
	
	IF top <= 0 THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El número de puestos debe ser mayor a 0";
	END IF;
	
	SELECT Prueba.Nombre, COUNT(Resultados.folio) As Total FROM Resultados 
	INNER JOIN Prueba ON Resultados.idPrueba  = Prueba.idPrueba
	INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro 
	WHERE YEAR(Resultados.fAnalisis) = Anio
	GROUP BY Prueba.nombre
	ORDER BY COUNT(Resultados.folio)
	LIMIT top;
END //

CALL TopPruebasMasRealizadasPorAnio(0, 3); 
CALL TopPruebasMasRealizadasPorAnio(2023, -3); 
CALL TopPruebasMasRealizadasPorAnio(2024, 5); 

# 5
DROP PROCEDURE IF EXISTS TopSignatariosConMasResultados;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS TopSignatariosConMasResultados(
	IN top INT
)
BEGIN
	IF top <= 0 THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El número de puestos debe ser mayor a 0";
	END IF;
	
	SELECT 
		SiglasSignatario(Signatario.idSignatario), Signatario.apellidoP, Signatario.apellidoM, 
		Signatario.primNombre, COUNT(Resultados.idSignatario) As Total 
	FROM Resultados 
	INNER JOIN Signatario ON Signatario.idSignatario = Resultados.idSignatario 
	GROUP BY SiglasSignatario(Signatario.idSignatario)
	ORDER BY COUNT(Resultados.idSignatario)
	LIMIT top;
END //

CALL TopSignatariosConMasResultados(5);

# 6
DROP PROCEDURE IF EXISTS BuscarSitio;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS BuscarSitio(
	IN clav TEXT,
	IN latid TEXT,
	IN longi TEXT,
	IN muni TEXT,
	IN estado TEXT,
	IN nom TEXT
)
BEGIN
	/*SELECT LikeFmt(clav), LikeFmt(latid), LikeFmt(longi), LikeFmt(muni), LikeFmt(estado), LikeFmt(nom);*/

    SELECT * FROM Sitio WHERE 
    clave LIKE LikeFmt(clav)
    AND latitud LIKE LikeFmt(latid)
    AND longitud LIKE LikeFmt(longi)
    AND municipio LIKE LikeFmt(muni)
    AND edo LIKE LikeFmt(estado)
    AND nombre LIKE LikeFmt(nom);	
END //

CALL Muestreos.BuscarSitio("OCBAL2825", "", "", "", "", "");

# 7
DROP PROCEDURE IF EXISTS BuscarMuestras;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS BuscarMuestras(
	IN numC TEXT,
	IN proj TEXT,
	IN fM TEXT,
	IN hM TEXT,
	IN fR TEXT
)
BEGIN
	SELECT * FROM Muestra
	WHERE numControl LIKE LikeFmt(numC)
	AND proyecto LIKE LikeFmt(proj)
	AND fMuestreo LIKE LikeFmt(fM)
	AND hMuestreo LIKE LikeFmt(hM)
	AND fRecepcion LIKE LikeFmt(fR);
END //

CALL Muestreos.BuscarMuestras("", "", "2024-01-04", "", "");

# 8 
DROP PROCEDURE IF EXISTS NormasPorPrueba;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS NormasPorPrueba(
	IN idP INT
)
BEGIN
	IF NOT EXISTS (SELECT idPrueba FROM Prueba WHERE idPrueba = idP) THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No existe la prueba";
	END IF;

	SELECT * FROM Norma 
	INNER JOIN DetalleNorma ON DetalleNorma.idNorma  = Norma.idNorma 
	INNER JOIN Prueba ON DetalleNorma.idPrueba = Prueba.idPrueba 
	WHERE Prueba.idPrueba = idP;
END //

CALL NormasPorPrueba(1); 

DROP PROCEDURE IF EXISTS QueryFromStr;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS QueryFromStr(
	IN query TEXT
)
BEGIN
	SELECT query;
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
END //

CALL QueryFromStr("SELECT * FROM Signatarios");

#10
DROP PROCEDURE IF EXISTS CrearUsuario;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS CrearUsuario(
	IN usr TEXT,
	IN pwd TEXT,
	IN posicion TEXT
)
BEGIN
	IF (SELECT UsuarioExiste(usr)) = 1 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El usuario existe";
	END IF;

	IF posicion NOT IN ("Dirección", "Muestreo", "Pruebas", "Sindicalizado") THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Posición no válida";
	END IF;

	CALL QueryFromStr(CONCAT("CREATE USER '",usr,"'@'localhost' IDENTIFIED BY '",pwd,"'"));

	IF posicion IN ("Dirección") THEN
		CALL QueryFromStr(CONCAT("GRANT ALL PRIVILEGES ON Muestreos.* TO '",usr,"'@'localhost'"));
		CALL QueryFromStr(CONCAT("GRANT CREATE USER ON *.* TO '",usr,"'@'localhost'"));
	END IF;

	IF posicion IN ("Pruebas", "Sindicalizado", "Muestreo") THEN
		CALL QueryFromStr(CONCAT("GRANT EXECUTE ON Muestreos.* TO '",usr,"'@'localhost'"));
		CALL QueryFromStr(
			CONCAT(
				"GRANT SELECT ON ",
				"Muestreos.Cliente, Muestreos.DetalleNorma, Muestreos.Muestra, Muestreos.Norma,",
				"Muestra.Parametro, Muestreos.Prueba, Muestreos.Resultados, Muestreos.Bitacora",
				"TO '",
				usr,
				"'@'localhost'"
			)
		);

		CALL QueryFromStr(
			CONCAT(
				"GRANT INSERT, UPDATE, DELETE ON ",
				"Muestreos.Bitacora, Muestreos.Resultados",
				"TO '",
				usr,
				"'@'localhost'"
			)
		);
	END IF;

	IF posicion IN ("Muestreos") THEN
		CALL QueryFromStr(
			CONCAT(
				"GRANT SELECT ON ",
				"Muestreos.Sitio ",
				"TO '",
				usr,
				"'@'localhost'"
			)
		);
	
		CALL QueryFromStr(
			CONCAT(
				"GRANT INSERT, UPDATE, DELETE ON ",
				"Muestreos.Muestras",
				"TO '",
				usr,
				"'@'localhost'"
			)
		);
	END IF;

	FLUSH PRIVILEGES;
END //

DROP USER IF EXISTS 'rubenrs'@'localhost';
CALL CrearUsuario("rubenrs", "1234", "Dirección");
CALL CrearUsuario("rubenrs", "1234", "Dirección");
CALL CrearUsuario("rubenrs", "1234", "Direcció");

DROP USER IF EXISTS 'marictt'@'localhost';
CALL CrearUsuario("marictt", "1234", "Pruebas");

DROP USER IF EXISTS 'marcuspa'@'localhost';
CALL CrearUsuario("rubenrs", "1234", "Muestreo");

# TIGGERS BEFORE
#1
DROP TRIGGER IF EXISTS InCliente;
DELIMITER //
CREATE TRIGGER InCliente
BEFORE INSERT ON Cliente
FOR EACH ROW
BEGIN
	IF (SELECT COUNT(idCliente) FROM Cliente GROUP BY nombre) <> 0 THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Se trata de ingresar dos clientes con el mismo nombre";
	END IF;
END //

# 2
DROP TRIGGER IF EXISTS LogErrorInsertDetalleNorma;
DELIMITER //
CREATE TRIGGER LogErrorInsertDetalleNorma
BEFORE INSERT ON DetalleNorma
FOR EACH ROW
BEGIN
	DECLARE msg TEXT DEFAULT "";
	IF NOT EXISTS (SELECT * FROM Norma WHERE idNorma = NEW.idNorma) THEN 
		SET msg = "Se trata de referenciar una norma que no existe ";
	END IF;
	
	IF NOT EXISTS(SELECT * FROM Prueba WHERE idPrueba = NEW.idPrueba) THEN
		SET msg = (CONCAT(msg, "Se trata de referenciar una prueba que no existe"));
	END IF;

	IF LENGTH(msg) <> 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#3 
DROP TRIGGER IF EXISTS LogErrorInsertDetalleSignatarios;
DELIMITER //
CREATE TRIGGER LogErrorInsertDetalleSignatarios
BEFORE INSERT ON DetalleSignatarios
FOR EACH ROW
BEGIN
	DECLARE msg TEXT DEFAULT "";
	IF NOT EXISTS (SELECT * FROM Signatario WHERE idSignatario = NEW.idSignatario) THEN 
		SET msg = "Se trata de referenciar un signatario que no existe ";
	END IF;
	
	IF NOT EXISTS(SELECT * FROM Prueba WHERE idPrueba = NEW.idPrueba) THEN
		SET msg = (CONCAT(msg, "Se trata de referenciar una prueba que no existe"));
	END IF;

	IF LENGTH(msg) <> 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#4
DROP TRIGGER IF EXISTS LogErrorInsertMuestra;
DELIMITER //
CREATE TRIGGER LogErrorInsertMuestra
BEFORE INSERT ON Muestra
FOR EACH ROW
BEGIN
	DECLARE msg TEXT DEFAULT "";
	IF EXISTS (SELECT numControl FROM Muestra WHERE numControl = NEW.numControl) THEN 
		SET msg = "Ya existe una muestra con esta clave";
	END IF;
	
	IF NOT EXISTS(SELECT idSignatario FROM Signatario WHERE idSignatario = NEW.muestreador) THEN
		SET msg = (CONCAT(msg, "El signatario no existe"));
	END IF;

	IF NOT EXISTS(SELECT idSitio FROM Sitio WHERE idSitio = NEW.idSitio) THEN
		SET msg = (CONCAT(msg, "El sitio no existe"));
	END IF;

	IF LENGTH(msg) <> 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#5
DROP TRIGGER IF EXISTS LogErrorDeleteSitio;
DELIMITER //
CREATE TRIGGER LogErrorDeleteSitio
BEFORE DELETE ON Sitio
FOR EACH ROW 
BEGIN 
	DECLARE msg TEXT;
	IF OLD.clave NOT LIKE "%RNL" THEN 
		SET msg = "Sólo pueden eliminarse sitios internos";
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#6
DROP TRIGGER IF EXISTS LogErrorInsertNorma
DELIMITER //
CREATE TRIGGER LogErrorInsertNorma
BEFORE INSERT ON Norma
FOR EACH ROW 
BEGIN 
	DECLARE msg TEXT;
	IF (SELECT COUNT(idNorma) FROM Norma WHERE norma = NEW.norma) <> 0 THEN 
		SET msg = CONCAT("Ya existe otra norma con nombre",NEW.norma);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#7
DROP TRIGGER IF EXISTS LogErrorInsertParametro
DELIMITER //
CREATE TRIGGER LogErrorInsertParametro
BEFORE INSERT ON Parametro
FOR EACH ROW 
BEGIN 
	DECLARE msg TEXT;
	IF (SELECT COUNT(idParametro) FROM Parametro WHERE nombre = NEW.nombre) <> 0 THEN 
		SET msg = CONCAT("Ya existe otra parametro con nombre ",NEW.nombre);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

#8
DROP TRIGGER IF EXISTS LogErrorInsertSignatario;
DELIMITER //
CREATE TRIGGER LogErrorInsertSignatario
BEFORE INSERT ON Signatario
FOR EACH ROW 
BEGIN
	DECLARE msg TEXT;
	IF TIMESTAMPDIFF(YEAR, NEW.fNacimiento, NOW()) < 18 THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No se cumple con la mayoría de edad";
	END IF;

	IF NEW.posicion NOT IN ("Dirección", "Muestreo", "Pruebas", "Sindicalizado") THEN 
		SET msg = CONCAT_WS(
			" ",
			"Las posiciones disponibles son: ",
			"Dirección", "Muestreo", "Pruebas", "Sindicalizado"
		);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;

	SET NEW.bono = 0;

	IF NEW.posicion IN ("Dirección", "Muestreo", "Pruebas", "Sindicalizado") THEN
		SET NEW.sueldo = 2000;
	ELSE 
		IF NEW.posicion IN ("Dirección") THEN
			SET NEW.sueldo = 15000;
		ELSE
			IF NEW.posicion IN ("Sindicalizado") THEN
				SET NEW.sueldo = 5000;
			ELSE
				IF NEW.posicion IN ("Muestreo", "Pruebas") THEN
					SET NEW.sueldo = 3000;
				END IF;
			END IF;
		END IF;
	END IF;
END //

# 9
DROP TRIGGER IF EXISTS TiggerDeleteSignatario;
DELIMITER //
CREATE TRIGGER TiggerDeleteSignatario
BEFORE DELETE ON Signatario
FOR EACH ROW 
BEGIN
	IF OLD.posicion IN ("Sindicalizado") THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No se pueden despedir a los sindicalizados";
	END IF;
END //

#10 
DROP TRIGGER IF EXISTS LogErrorInsertSitio;
DELIMITER //
CREATE TRIGGER LogErrorInsertSitio
BEFORE INSERT ON Sitio
FOR EACH ROW 
BEGIN
	DECLARE claveSitioRep, msg TEXT;

	IF (SELECT COUNT(idSitio) FROM Sitio WHERE clave = NEW.clave) <> 0 THEN 
		SET msg = CONCAT("Ya existe un sitio con la clave ", NEW.clave);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;

	IF (SELECT COUNT(idSitio) FROM Sitio WHERE nombre = NEW.nombre) <> 0 THEN 
		SET msg = CONCAT("Ya existe un sitio con nombre ", NEW.nombre);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
	
	IF (SELECT COUNT(idSitio) FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud) <> 0 THEN 
		SET claveSitioRep = (SELECT clave FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud);
		SET msg = CONCAT("Ya existe un sitio con estas coordenadas: ", claveSitioRep);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

# 12 
DROP TRIGGER IF EXISTS LogErrorUpdateSitio;
DELIMITER //
CREATE TRIGGER LogErrorUpdateSitio
BEFORE INSERT ON Sitio
FOR EACH ROW 
BEGIN
	DECLARE claveSitioRep, msg TEXT;

	IF (SELECT COUNT(idSitio) FROM Sitio WHERE clave = NEW.clave) <> 0 THEN 
		SET msg = CONCAT("Ya existe un sitio con la clave ", NEW.clave);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;

	IF (SELECT COUNT(idSitio) FROM Sitio WHERE nombre = NEW.nombre) <> 0 THEN 
		SET msg = CONCAT("Ya existe un sitio con nombre ", NEW.nombre);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
	
	IF (SELECT COUNT(idSitio) FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud) <> 0 THEN 
		SET claveSitioRep = (SELECT clave FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud);
		SET msg = CONCAT("Ya existe un sitio con estas coordenadas: ", claveSitioRep);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
END //

# 13
DROP TRIGGER IF EXISTS LogErroUpdateDetalleNorma;
DELIMITER //
CREATE TRIGGER LogErroUpdateDetalleNorma
AFTER UPDATE ON DetalleNorma
FOR EACH ROW
BEGIN
	IF EXISTS (
		SELECT 
			folio FROM DetalleNorma 
		WHERE 
			idNorma = NEW.idNorma AND 
			idPrueba = NEW.idPrueba AND 
			folio <> NEW.folio
	)
	THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Relación entre norma y prueba ya existente";
	END IF;
END //

#14
DROP TRIGGER IF EXISTS LogErroUpdateDetalleSignatarios;
DELIMITER //
CREATE TRIGGER LogErroUpdateDetalleSignatarios
AFTER UPDATE ON DetalleSignatarios
FOR EACH ROW
BEGIN
	IF EXISTS (
		SELECT 
			idDetalle FROM DetalleSignatarios
		WHERE 
			idSignatario = NEW.idSignatario AND 
			idPrueba = NEW.idPrueba AND 
			idDetalle <> NEW.idDetalle
	)
	THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Relación entre signatario y prueba ya existente";
	END IF;
END //

#15
DROP TRIGGER IF EXISTS BloqueoUpdateBitacora;
DELIMITER //
CREATE TRIGGER BloqueoUpdateBitacora
AFTER UPDATE ON Bitacora
FOR EACH ROW
BEGIN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Las modificaciones en bitácora no están permitidas";
END //

# Tiggers AFTER
# 1
DROP TRIGGER IF EXISTS BitacoraCliente;
DELIMITER //
CREATE TRIGGER BitacoraCliente
AFTER INSERT ON Cliente
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se insertó cliente",
			NEW.nombre,
			"realizado por:",
			USER(),
			"Fecha:",
			NOW()
		)
	);
END //

# 2
DROP TRIGGER IF EXISTS BitacoraDetalleNorma;
DELIMITER //
CREATE TRIGGER BitacoraDetalleNorma
AFTER INSERT ON DetalleNorma
FOR EACH ROW
BEGIN
	DECLARE Norma, Prueba TEXT;
	SET norma = (SELECT norma FROM Norma WHERE idNorma = NEW.idNorma);
	SET prueba = (SELECT prueba FROM Prueba WHERE idPrueba = NEW.idPrueba);

	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se agregó a la prueba",
			prueba,
			"La norma",
			norma,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

#3
DROP TRIGGER IF EXISTS BitacoraDetalleSignatarios;
DELIMITER //
CREATE TRIGGER BitacoraDetalleSignatarios
AFTER INSERT ON DetalleSignatarios
FOR EACH ROW
BEGIN
	DECLARE nombreSig, Prueba TEXT;
	SET nombreSig = (
		SELECT 
		CONCAT_WS(" ", primNombre,segNombre,apellidoP,apellidoM) 
		FROM Signatario WHERE idSignatario = NEW.idSignatario
	);

	SET prueba = (SELECT prueba FROM Prueba WHERE idPrueba = NEW.idPrueba);

	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Ahora",
			nombreSig,
			"puede realizar la prueba:",
			prueba,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 4
DROP TRIGGER IF EXISTS BitacoraInsertMuestra;
DELIMITER //
CREATE TRIGGER BitacoraInsertMuestra
AFTER INSERT ON Muestra
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se ingresó la muestra con NumContrl:",
			NEW.numControl,
			"Muestreador:",
			NombreCS(NEW.muestreador),
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);

	UPDATE Signatario SET
		bono = bono + (sueldo*0.05)
	WHERE idSignatario = NEW.muestreador;
END //

# 5
DROP TRIGGER IF EXISTS BitacoraUpdateMuestra;
DELIMITER //
CREATE TRIGGER BitacoraUpdateMuestra
AFTER UPDATE ON Muestra
FOR EACH ROW
BEGIN
	DECLARE msg TEXT;
	SET msg = CONCAT("Se modificó muestra: ", OLD.numControl);

	IF OLD.numControl <> NEW.numControl THEN
		SET msg = CONCAT_WS(" ", msg, "NumC previo:",OLD.numControl,"NumC nuevo:",NEW.numControl);
	END IF;
	IF OLD.proyecto <> NEW.proyecto THEN
		SET msg = CONCAT_WS(" ", msg, "proyecto previo:",OLD.proyecto,"proyecto nuevo:",NEW.proyecto);
	END IF;
	IF OLD.fMuestreo <> NEW.fMuestreo THEN
		SET msg = CONCAT_WS(" ", msg, "Fecha muestreo previa:",OLD.fMuestreo,"Fecha muestreo nueva:",NEW.fMuestreo);
	END IF;
	IF OLD.hMuestreo <> NEW.hMuestreo THEN
		SET msg = CONCAT_WS(" ", msg, "Hora muestreo previa:",OLD.hMuestreo,"Hora muestreo nueva:",NEW.hMuestreo);
	END IF;
	IF ClavePorIdSitio(OLD.idSitio) <> ClavePorIdSitio(NEW.idSitio) THEN
		SET msg = CONCAT_WS(
			" ", 
			msg, 
			"sitio previo:",
			ClavePorIdSitio(OLD.idSitio),
			"sitio nuevo:",
			ClavePorIdSitio(NEW.idSitio)
		);
	END IF;
	
	IF LENGTH(msg) <> 0 THEN
		INSERT INTO Bitacora VALUES(
			0,
			CONCAT_WS(" ",msg,"realizado por:",USER(),"fecha:",NOW())
		);
	END IF;
END //

# 6
DROP TRIGGER IF EXISTS BitacoraDeleteMuestra;
DELIMITER //
CREATE TRIGGER BitacoraDeleteMuestra
AFTER DELETE ON Muestra
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora VALUES(
		0,
		CONCAT_WS(
			" ",
			"Se eliminó muestra:",
			OLD.numControl,
			"del sitio:",
			ClavePorIdSitio(OLD.idSitio),
			"del proyecto:",
			OLD.proyecto,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 7
DROP TRIGGER IF EXISTS BitacoraInsertNorma;
DELIMITER //
CREATE TRIGGER BitacoraInsertNorma
AFTER INSERT ON Norma
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se ingresó norma:",
			NEW.norma,
			"con unidades:",
			NEW.unidades,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 8
DROP TRIGGER IF EXISTS BitacoraUpdateNorma;
DELIMITER //
CREATE TRIGGER BitacoraUpdateNorma
AFTER UPDATE ON Norma
FOR EACH ROW
BEGIN
	DECLARE msg TEXT;
	SET msg = CONCAT("Se modificó norma: ", OLD.norma);
	
	IF OLD.idNorma <> NEW.idNorma THEN
		SET msg = CONCAT_WS(" ", msg, "ID previo:",OLD.idNorma,"ID nuevo:",NEW.idNorma);
	END IF;
	IF OLD.norma <> NEW.norma THEN
		SET msg = CONCAT_WS(" ", msg, "nombre previo:",OLD.norma,"nombre nuevo:",NEW.norma);
	END IF;
	IF OLD.unidades <> NEW.unidades THEN
		SET msg = CONCAT_WS(" ", msg, "unidades previas:",OLD.unidades,"unidades nuevas:",NEW.unidades);
	END IF;
	IF OLD.tipoVentana <> NEW.tipoVentana THEN
		SET msg = CONCAT_WS(" ", msg, "tipo ventana previa:",OLD.tipoVentana,"tipo ventana nueva:",NEW.tipoVentana);
	END IF;
	
	IF LENGTH(msg) <> 0 THEN
		INSERT INTO Bitacora VALUES(
			0,
			CONCAT_WS(" ",msg,"realizado por:",USER(),"fecha:",NOW())
		);
	END IF;
END //

#9 
DROP TRIGGER IF EXISTS BitacoraDeleteNorma;
DELIMITER //
CREATE TRIGGER BitacoraDeleteNorma
AFTER DELETE ON Norma
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora VALUES(
		0,
		CONCAT_WS(
			" ",
			"Se eliminó norma:",
			OLD.norma,
			"con unidades:",
			OLD.unidades,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 10
DROP TRIGGER IF EXISTS BitacoraInsertParametro;
DELIMITER //
CREATE TRIGGER BitacoraInsertParametro
AFTER INSERT ON Parametro
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se ingresó parámetro:",
			NEW.nombre,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 11
DROP TRIGGER IF EXISTS BitacoraDeleteParametro;
DELIMITER //
CREATE TRIGGER BitacoraDeleteParametro
AFTER DELETE ON Parametro
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora VALUES(
		0,
		CONCAT_WS(
			" ",
			"Se eliminó parámetro:",
			OLD.nombre,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

# 12
DROP TRIGGER IF EXISTS BitacoraInsertResultado;
DELIMITER //
CREATE TRIGGER BitacoraInsertResultado
AFTER INSERT ON Resultados
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"A la muestra:",
			NEW.numControl,
			"Del signatario:",
			NombreCS(NEW.idSignatario),
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);

	UPDATE Signatario SET 
		bono = bono + (sueldo*0.1)
	WHERE idSignatario = NEW.idSignatario;
END //

#13
DROP TRIGGER IF EXISTS BitacoraUpdateResultado;
DELIMITER //
CREATE TRIGGER BitacoraUpdateResultado
AFTER UPDATE ON Resultados
FOR EACH ROW
BEGIN
	DECLARE msg TEXT;
	SET msg = CONCAT("Se modificó resultado folio: ", OLD.folio);
	
	IF OLD.folio <> NEW.folio THEN
		SET msg = CONCAT_WS(" ", msg, "folio previo:",OLD.folio,"folio nuevo:",NEW.folio);
	END IF;
	IF OLD.resultado <> NEW.resultado THEN
		SET msg = CONCAT_WS(" ", msg, "resultado previo:",OLD.resultado,"resultado nuevo:",NEW.resultado);
	END IF;
	IF OLD.fAnalisis <> NEW.fAnalisis THEN
		SET msg = CONCAT_WS(" ", msg, "fecha previa:",OLD.fAnalisis,"fecha nueva:",NEW.fAnalisis);
	END IF;
	IF OLD.idSignatario <> NEW.idSignatario THEN
		SET msg = CONCAT_WS(" ", msg, "unidades previas:",NombreCS(OLD.idSignatario),"unidades nuevas:",NombreCS(NEW.idSignatario));
	END IF;
	IF OLD.idPrueba <> NEW.idPrueba THEN
		SET msg = CONCAT_WS(
			" ", 
			msg, 
			"prueba previa:",
			NombrePruebaPorId(OLD.idPrueba),
			"prueba nueva:",
			NombrePruebaPorId(NEW.idPrueba)
		);
	END IF;
	IF OLD.idNorma <> NEW.idNorma THEN
		SET msg = CONCAT_WS(
			" ", 
			msg, 
			"norma previa:",
			NormaPorId(OLD.idNorma),
			"norma nueva:",
			NormaPorId(NEW.idNorma)
		);
	END IF;

	IF OLD.numControl <> OLD.numControl THEN
		SET msg = CONCAT_WS(
			" ", 
			msg, 
			"norma previa:",
			OLD.numControl,
			"norma nueva:",
			NEW.numControl
		);
	END IF;
	
	IF LENGTH(msg) <> 0 THEN
		INSERT INTO Bitacora VALUES(
			0,
			CONCAT_WS(" ",msg,"realizado por:",USER(),"fecha:",NOW())
		);
	END IF;
END //

#14
DROP TRIGGER IF EXISTS BitacoraDeleteResultado;
DELIMITER //
CREATE TRIGGER BitacoraDeleteResultado
AFTER DELETE ON Resultados
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora VALUES(
		0,
		CONCAT_WS(
			" ",
			"Se eliminó resultado folio::",
			OLD.folio,
			"con resultado:",
			OLD.resultado,
			"fecha analisis:",
			OLD.fAnalisis,
			"del signatario",
			NombreCS(OLD.idSignatario),
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

#15
DROP TRIGGER IF EXISTS BitacoraInsertSignatario;
DELIMITER //
CREATE TRIGGER BitacoraInsertSignatario
AFTER INSERT ON Signatario
FOR EACH ROW
BEGIN
	INSERT INTO Bitacora (mensaje) VALUES(
		CONCAT_WS(
			" ",
			"Se agregó al signatario:",
			NombreCS(NEW.idSignatario),
			"Con sueldo:",
			NEW.sueldo,
			"Fecha ingreso:",
			NEW.fIngreso,
			"Posición:",
			NEW.posicion,
			"realizado por:",
			USER(),
			"fecha:",
			NOW()
		)
	);
END //

/*
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
*/

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
	unidades VARCHAR(32),
	tipoVentana INT
);

# Mediciones directas y Físicoquimicos
INSERT INTO Norma VALUES
(1,"NMX-AA-014-1980", NULL, NULL),
(2,"NMX-AA-003-1980", NULL, NULL),
(3,"NMX-AA-005-SCFI-2013", NULL, NULL),
(4,"NMX-AA-007-SCFI-2013", NULL, NULL),
(5,"NMX-AA-008-SCFI-2016", NULL, NULL),
(6,"NMX-AA-034-SCFI-2015", NULL, NULL),
(7,"NMX-AA-028-SCFI-2021", NULL, NULL),
(8,"NMX-AA-036-SCFI-2021", NULL, NULL),
(9,"NMX-AA-073-SCFI-2001", NULL, NULL),
(10,"NMX-AA-093-SCFI-2018", NULL, NULL),
(11,"NMX-AA-072-SCFI-2001", NULL, NULL),
(12,"NMX-AA-012-SCFI-2001", NULL, NULL),
(13,"NMX-AA-006-SCFI-2010", NULL, NULL),
(14,"Método: 4500-NH3. F. Standard Methods APHA AWWA. WEF. Ed. 21st. 2005", NULL, NULL),
(15,"EPA 365.3 (1978)", NULL, NULL),
(16,"NMX-AA-026-SCFI-2010", NULL, NULL),
(17,"NMX-AA-004-SCFI-2013", NULL, NULL),
(18,"NMX-AA-030/1-SCFI-2012", NULL, NULL);

# Espectrofotométricos UV/VIS/IR
INSERT INTO Norma VALUES
(19,"NMX-AA-029-SCFI-2001", NULL, NULL),
(20,"NMX-AA-079-SCFI-2001", NULL, NULL),
(21,"NMX-AA-099-SCFI-2021", NULL, NULL),
(22,"NMX-AA-044-SCFI-2014", NULL, NULL),
(23,"NMX-AA-077-SCFI-2001", NULL, NULL),
(24,"NMX-AA-039-SCFI-2001", NULL, NULL),
(25,"NMX-AA-074-SCFI-2014", NULL, NULL),
(26,"Método HACH (Método pyridine-pyrazalone) 8027 5° edición", NULL, NULL),
(27,"Método HACH 10175 5° edición", NULL, NULL),
(28,"NMX-AA-017-SCFI-2021", NULL, NULL);

# Microbiología
INSERT INTO Norma VALUES
(29,"NMX-AA-042-SCFI-2015", NULL, NULL),
(30,"NMX-AA-102-SCFI-2019", NULL, NULL),
(31,"Método ASTM D-6503-99 (1999)", NULL, NULL),
(32,"Método: 9223.B. Standard Methods APHA AWWA. WEF. Ed. 21st 2005", NULL, NULL),
(33,"NMX-AA-113-SCFI-2012", NULL, NULL);

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
	posicion VARCHAR(32) DEFAULT "Pruebas"
);

INSERT INTO Signatario (idSignatario, primNombre, segNombre, apellidoP, apellidoM, posicion) VALUES
(1,"Armando","Carlos","Rosas","Quiñonez", "Sindicalizado"),
(2,"Carmen","Carolina","Puentes","Merino", "Sindicalizado"),
(3,"Alicia","Alberto","Montez","Díaz", "Sindicalizado"),
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
	FOREIGN KEY (idCliente) REFERENCES Muestreos.Cliente(idCliente)
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
	FOREIGN KEY (idParametro) REFERENCES Parametro (idParametro)
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
	idNorma INT NOT NULL,
	idPrueba INT NOT NULL,
	FOREIGN KEY (idNorma) REFERENCES Muestreos.Norma(idNorma),
	FOREIGN KEY (idPrueba) REFERENCES Muestreos.Prueba(idPrueba)
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
	idSignatario INT NOT NULL,
	idPrueba INT NOT NULL,
	FOREIGN KEY (idSignatario) REFERENCES Muestreos.Signatario(idSignatario),
	FOREIGN KEY (idPrueba) REFERENCES Muestreos.Prueba(idPrueba)
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
	FOREIGN KEY (muestreador) REFERENCES Muestreos.Signatario(idSignatario),
	FOREIGN KEY (idSitio) REFERENCES Muestreos.Sitio(idSitio)
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
	FOREIGN KEY (idSignatario) REFERENCES Muestreos.Signatario(idSignatario),
	FOREIGN KEY (idPrueba) REFERENCES Muestreos.Prueba(idPrueba),
	FOREIGN KEY (idNorma) REFERENCES Muestreos.Norma(idNorma),
	FOREIGN KEY (numControl) REFERENCES Muestreos.Muestra(numControl)
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

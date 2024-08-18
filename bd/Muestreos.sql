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


CREATE TABLE Muestreos.Norma (
	idNorma INT PRIMARY KEY AUTO_INCREMENT,
	norma VARCHAR(100),
	unidades VARCHAR(32)
    DEFAULT "mg/L",
	tipoVentana INT
    DEFAULT 1
);


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


CREATE TABLE Muestreos.Parametro(
	idParametro INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(45)
);

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

CREATE TABLE Muestreos.Prueba (
	idPrueba INT PRIMARY KEY auto_increment,
	nombre varchar(100) NOT NULL,
	idParametro INT,
	FOREIGN KEY (idParametro) 
		REFERENCES Parametro (idParametro)
	    ON DELETE SET NULL
	    ON UPDATE CASCADE
);

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

/*
INSERT INTO Cliente (idCliente, nombre) VALUES 
(1, "3M"),
(2, "Abbott"),
(3, "AstraZeneca"),
(4, "BASF"),
(5, "Bayer"),
(6, "Boehringer Ingelheim"),
(7, "Bristol-Myers"),
(8, "Chevron"),
(9, "Coca-Cola"),
(10, "Colgate-Palmolive"),
(11, "ConocoPhillips"),
(12, "Dow Chemical"),
(13, "Dr. Pepper Snapple Group"),
(14, "DuPont"),
(15, "Eli Lilly"),
(16, "ExxonMobil"),
(17, "General Mills"),
(18, "GlaxoSmithKline"),
(19, "Heinz"),
(20, "Johnson & Johnson"),
(21, "Kellogg's"),
(22, "Kraft Foods"),
(23, "L'Oreal"),
(24, "Merck"),
(25, "Monsanto"),
(26, "Monster Beverage"),
(27, "Nestle"),
(28, "Novartis"),
(29, "Pepsi Bottling Group"),
(30, "PepsiCo"),
(31, "Pfizer"),
(32, "Procter & Gamble"),
(33, "Reckitt Benckiser"),
(34, "Red Bull"),
(35, "Roche"),
(36, "Sanofi"),
(37, "Shell"),
(38, "Syngenta"),
(39, "Total"),
(40, "Unilever");

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
(35,"NMX-AA-112-SCFI-2017", NULL, NULL), 
(36,"Método 10200 H Standard Methods APHA. AWWA. WEF. Ed. 21st. 2005 (Método tricromático)", NULL, NULL);

# Espectrofotometria de absorción atómica
INSERT INTO Norma (idNorma, norma, unidades, tipoVentana) VALUES
(37,"NMX-AA-051-SCFI-2016", "mg/L", NULL);

# Otros
INSERT INTO Norma (idNorma, norma, unidades, tipoVentana) VALUES
(38,"XML-AA-001-2024", "mg/L", NULL),
(39,"XML-AA-002-2024", "mg/L", NULL),
(40,"XML-AA-003-2024", "mg/L", NULL);

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

INSERT INTO Sitio VALUES
(1,"OCBAL2825","RIO TETLAMA ANTES RIO APATLACO","RIO AMACUZAC",NULL,NULL,
"OCBAL",NULL,"MORELOS","XOXOCOTLA","RIO APATLACO","LÓTICO",NULL,"18.702203",
"-99.240932","RIOS. PROTECCIÓN DE LA VIDA ACUATICA","CUERPO DE AGUA NATURAL", NULL);

INSERT INTO Sitio (idSitio, clave, nombre, edo, municipio, tipoC, latitud, longitud) VALUES
(2, "ABCD001RNL", "Río Papagallo", "Guerrero", "Acapulco", "Río", "16.8631", "-99.8763"),
(3, "ABCD002RNL", "Río Jamapa", "Veracruz", "Veracruz", "Río", "19.1722", "-96.1333"),
(4, "ABCD007", "Lago de Pátzcuaro", "Michoacán", "Morelia", "Río", "19.5042", "-96.1333"),
(5, "ABCD008", "Río Atoyac", "Puebla", "Puebla", "Río", "19.1722", "-96.1333");

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

INSERT INTO Muestra VALUES
("240124220802", "Red Nacional de Moritoreo", "2024-01-24", "14:30:00","2024-01-24", 8, 1),
("123456778901", "Proyecto2", "2024-01-27", "13:28:00", "2024-01-27", 8, 2),
("123456778902", "Proyecto3", "2024-02-02", "12:28:00", "2024-02-02", 8, 3),
("123456778903", "Proyecto4", "2024-02-17", "14:08:00", "2024-02-17", 8, 4),
("123456778904", "Proyecto5", "2024-03-30", "12:12:00", "2024-03-30", 8, 5);

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
*/


INSERT INTO Muestreos.Bitacora (mensaje) VALUES 
('Inicio del muestreo en el sitio A'),
('Recepción de muestras del sitio B'),
('Análisis de pH completado'),
('Error en la toma de muestras'),
('Registro de muestras completado'),
('Norma N-123 aplicada a la prueba P-001'),
('Verificación de datos realizada'),
('Actualización de datos en la base de datos'),
('Finalización del muestreo en el sitio C'),
('Muestra número M-1234 recibida'),
('Análisis de sulfatos iniciado'),
('Preparación de soluciones completada'),
('Elaboración de informe de resultados'),
('Revisión de normas aplicables'),
('Muestra número M-5678 en análisis'),
('Cambio de estado a finalizado para el muestreo'),
('Inconsistencias encontradas en los datos'),
('Solicitud de aclaración enviada a laboratorio'),
('Recepción de nueva muestra de control'),
('Finalización del proceso de análisis para la muestra M-6789');

INSERT INTO Muestreos.Cliente (nombre) VALUES 
('Compañía ABC'),
('Laboratorio XYZ'),
('Cliente 123'),
('Industria LMN'),
('Agropecuaria OPQ'),
('Minera RST'),
('Consultora UVW'),
('Empresa DEF'),
('Hidrocarburos GHI'),
('Tecnologías JKL'),
('Alimentos MNO'),
('Servicios PQR'),
('Consultores STU'),
('Agricultura VWX'),
('Energía YZA'),
('Automotriz BCD'),
('Textiles EFG'),
('Farmacéutica HIJ'),
('Educación KLM'),
('Construcción NOP');

INSERT INTO Muestreos.Norma (norma, unidades, tipoVentana) VALUES 
('NOM-001', 'mg/L', 1),
('NOM-002', 'mg/L', 1),
('NOM-003', 'mg/L', 2),
('NOM-004', 'mg/L', 1),
('NOM-005', 'mg/L', 1),
('NOM-006', 'mg/L', 2),
('NOM-007', 'mg/L', 1),
('NOM-008', 'mg/L', 1),
('NOM-009', 'mg/L', 2),
('NOM-010', 'mg/L', 1),
('NOM-011', 'mg/L', 1),
('NOM-012', 'mg/L', 1),
('NOM-013', 'mg/L', 2),
('NOM-014', 'mg/L', 1),
('NOM-015', 'mg/L', 2),
('NOM-016', 'mg/L', 1),
('NOM-017', 'mg/L', 2),
('NOM-018', 'mg/L', 1),
('NOM-019', 'mg/L', 1),
('NOM-020', 'mg/L', 1);


INSERT INTO Signatario (idSignatario, primNombre, segNombre, apellidoP, apellidoM,fIngreso,fNacimiento, posicion, usuario) VALUES
(1,"Rubén","Omar","Román","Salinas", NOW(), "2004-05-28", "Dirección", "rubenrs@localhost"),
(2,"Maricruz",NULL,"Toledano","Torres", NOW(), "2004-09-12", "Sindicalizado", "marictt@localhost"),
(3,"Ian","Marcus","Prado","Acevedo", NOW(), "2000-06-10",  "Muestreo", "marcuspa@localhost");

INSERT INTO Muestreos.Signatario (primNombre, segNombre, apellidoP, apellidoM, sueldo, bono, fIngreso, fNacimiento, posicion, usuario) VALUES 
('Juan', 'Carlos', 'Pérez', 'Gómez', 1200, 200, '2022-01-10', '1990-05-14', 'Analista', 'jcperez'),
('María', 'Elena', 'López', 'Martínez', 1300, 250, '2021-03-15', '1988-08-22', 'Químico', 'melopez'),
('Luis', 'Fernando', 'García', 'Rodríguez', 1400, 300, '2020-05-20', '1985-12-10', 'Ingeniero', 'lfgarcia'),
('Ana', 'Sofía', 'Ramírez', 'Sánchez', 1250, 100, '2019-07-25', '1992-03-30', 'Técnico', 'asramirez'),
('Carlos', 'Eduardo', 'Hernández', 'Gutiérrez', 1500, 400, '2018-09-05', '1987-11-18', 'Supervisor', 'cehernandez'),
('José', 'Miguel', 'Fernández', 'Ríos', 1350, 250, '2022-02-14', '1991-06-01', 'Técnico', 'jmfernandez'),
('Laura', 'Isabel', 'González', 'Torres', 1280, 180, '2017-04-12', '1989-01-05', 'Analista', 'ligonzalez'),
('Pedro', 'Antonio', 'Mendoza', 'Hernández', 1450, 320, '2021-08-19', '1993-09-23', 'Ingeniero', 'pamendoza'),
('Silvia', 'Patricia', 'Vargas', 'Ruiz', 1380, 280, '2020-11-25', '1990-02-17', 'Químico', 'spvargas'),
('Jorge', 'Alberto', 'Cruz', 'López', 1320, 240, '2019-03-30', '1988-07-14', 'Supervisor', 'jacruz'),
('Alejandra', 'Beatriz', 'Morales', 'Méndez', 1420, 310, '2018-12-10', '1986-10-02', 'Ingeniero', 'abmorales'),
('Ricardo', 'Daniel', 'Ortega', 'Ramírez', 1370, 270, '2022-05-17', '1992-03-01', 'Analista', 'rdortega'),
('Fernando', 'José', 'Pérez', 'Ramírez', 1500, 350, '2021-10-09', '1991-12-25', 'Supervisor', 'fjramirez'),
('Gabriela', 'Lucía', 'Navarro', 'Gómez', 1310, 220, '2020-07-21', '1993-04-11', 'Químico', 'glnavarro'),
('Miguel', 'Ángel', 'Reyes', 'Sosa', 1390, 290, '2019-01-15', '1987-06-09', 'Ingeniero', 'mareyes'),
('Sofía', 'Alejandra', 'Luna', 'Zamora', 1330, 250, '2018-11-05', '1990-08-21', 'Analista', 'saluna'),
('Andrés', 'Eduardo', 'Ramírez', 'Flores', 1440, 330, '2017-02-25', '1989-10-10', 'Técnico', 'aeramirez'),
('Mónica', 'Paola', 'Cisneros', 'Vega', 1290, 190, '2022-03-12', '1994-05-19', 'Supervisor', 'mpcisneros'),
('Roberto', 'Carlos', 'Hernández', 'Martínez', 1460, 340, '2021-06-14', '1992-01-28', 'Técnico', 'rchernandez'),
('Daniela', 'Fernanda', 'López', 'Ramírez', 1340, 260, '2020-04-23', '1991-09-07', 'Ingeniero', 'dflopez');

INSERT INTO Muestreos.Parametro (nombre) VALUES 
('pH'),
('Conductividad'),
('Turbidez'),
('Cloruro'),
('Sulfatos'),
('Nitratos'),
('Oxígeno disuelto'),
('Demanda bioquímica de oxígeno'),
('Demanda química de oxígeno'),
('Alcalinidad'),
('Dureza'),
('Calcio'),
('Magnesio'),
('Sodio'),
('Potasio'),
('Hierro'),
('Manganeso'),
('Fósforo'),
('Fluoruros'),
('Mercurio');

INSERT INTO Muestreos.Sitio (clave, nombre, cuenca, cAcuifero, acuifero, organismo, dirLocal, edo, municipio, cAgua, tipoC, subtipoC, latitud, longitud, uso, lugarT, idCliente)
VALUES 
('SIT-001', 'Río Azul', 'Cuenca 1', 'AC-001', 'Acuífero 1', 'Organismo A', 'Calle 123', 'Estado A', 'Municipio A', 'Agua 1', 'Río', 'Río Perennial', '21.1234', '-101.1234', 'Abastecimiento público', 'Punto A', 1),
('SIT-002', 'Laguna Verde', 'Cuenca 2', 'AC-002', 'Acuífero 2', 'Organismo B', 'Calle 456', 'Estado B', 'Municipio B', 'Agua 2', 'Lago', 'Lago Natural', '20.5678', '-100.5678', 'Recreación', 'Punto B', 2),
('SIT-003', 'Río Claro', 'Cuenca 1', 'AC-003', 'Acuífero 3', 'Organismo A', 'Calle 789', 'Estado C', 'Municipio C', 'Agua 3', 'Río', 'Río Perennial', '19.4321', '-99.4321', 'Abastecimiento público', 'Punto C', 3),
('SIT-004', 'Lago Verde', 'Cuenca 2', 'AC-004', 'Acuífero 4', 'Organismo B', 'Calle 101', 'Estado D', 'Municipio D', 'Agua 4', 'Lago', 'Lago Natural', '18.8765', '-98.8765', 'Conservación de flora y fauna', 'Punto D', 4),
('SIT-005', 'Río Grande', 'Cuenca 3', 'AC-005', 'Acuífero 5', 'Organismo C', 'Calle 202', 'Estado E', 'Municipio E', 'Agua 5', 'Río', 'Río Perennial', '17.6543', '-97.6543', 'Hidroelectricidad', 'Punto E', 5),
('SIT-006', 'Laguna Amarilla', 'Cuenca 4', 'AC-006', 'Acuífero 6', 'Organismo D', 'Calle 303', 'Estado F', 'Municipio F', 'Agua 6', 'Lago', 'Lago Natural', '16.3210', '-96.3210', 'Recreación', 'Punto F', 6),
('SIT-007', 'Río Azul', 'Cuenca 3', 'AC-007', 'Acuífero 7', 'Organismo C', 'Calle 404', 'Estado G', 'Municipio G', 'Agua 7', 'Río', 'Río Perennial', '15.9876', '-95.9876', 'Abastecimiento público', 'Punto G', 7),
('SIT-008', 'Lago Blanco', 'Cuenca 4', 'AC-008', 'Acuífero 8', 'Organismo D', 'Calle 505', 'Estado H', 'Municipio H', 'Agua 8', 'Lago', 'Lago Natural', '14.6543', '-94.6543', 'Conservación de flora y fauna', 'Punto H', 8),
('SIT-009', 'Río Negro', 'Cuenca 1', 'AC-009', 'Acuífero 9', 'Organismo A', 'Calle 606', 'Estado I', 'Municipio I', 'Agua 9', 'Río', 'Río Perennial', '13.3210', '-93.3210', 'Hidroelectricidad', 'Punto I', 9),
('SIT-010', 'Laguna Blanca', 'Cuenca 2', 'AC-010', 'Acuífero 10', 'Organismo B', 'Calle 707', 'Estado J', 'Municipio J', 'Agua 10', 'Lago', 'Lago Natural', '12.9876', '-92.9876', 'Agricultura', 'Punto J', 10),
('SIT-011', 'Río Verde', 'Cuenca 5', 'AC-011', 'Acuífero 11', 'Organismo E', 'Calle 808', 'Estado K', 'Municipio K', 'Agua 11', 'Río', 'Río Intermittent', '11.6543', '-91.6543', 'Industria', 'Punto K', 11),
('SIT-012', 'Lago Azul', 'Cuenca 6', 'AC-012', 'Acuífero 12', 'Organismo F', 'Calle 909', 'Estado L', 'Municipio L', 'Agua 12', 'Lago', 'Lago Artificial', '10.3210', '-90.3210', 'Recreación', 'Punto L', 12),
('SIT-013', 'Río Blanco', 'Cuenca 7', 'AC-013', 'Acuífero 13', 'Organismo G', 'Calle 1010', 'Estado M', 'Municipio M', 'Agua 13', 'Río', 'Río Perennial', '9.9876', '-89.9876', 'Agricultura', 'Punto M', 13),
('SIT-014', 'Laguna Roja', 'Cuenca 8', 'AC-014', 'Acuífero 14', 'Organismo H', 'Calle 1111', 'Estado N', 'Municipio N', 'Agua 14', 'Lago', 'Lago Natural', '8.6543', '-88.6543', 'Conservación de flora y fauna', 'Punto N', 14),
('SIT-015', 'Río Amarillo', 'Cuenca 9', 'AC-015', 'Acuífero 15', 'Organismo I', 'Calle 1212', 'Estado O', 'Municipio O', 'Agua 15', 'Río', 'Río Perennial', '7.3210', '-87.3210', 'Abastecimiento público', 'Punto O', 15);

INSERT INTO Muestreos.Prueba (nombre, idParametro)
VALUES
('pH', 1),
('Turbidez', 2),
('Conductividad', 3),
('Color', 4),
('Sólidos disueltos', 5),
('Cloruros', 6),
('Sulfatos', 7),
('Nitratos', 8),
('Fósforo', 9),
('Oxígeno disuelto', 10),
('Demanda bioquímica de oxígeno', 11),
('Demanda química de oxígeno', 12),
('Coliformes fecales', 13),
('Coliformes totales', 14),
('Cianuros', 15);


INSERT INTO Muestreos.DetalleNorma (idNorma, idPrueba)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);

INSERT INTO Muestreos.DetalleSignatarios (idSignatario, idPrueba)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);

INSERT INTO Muestreos.Muestra (numControl, proyecto, fMuestreo, hMuestreo, fRecepcion, muestreador, idSitio)
VALUES
('MC-001', 'Proyecto A', '2024-08-01', '08:00:00', '2024-08-02', 1, 1),
('MC-002', 'Proyecto B', '2024-08-03', '09:00:00', '2024-08-04', 2, 2),
('MC-003', 'Proyecto C', '2024-08-05', '10:00:00', '2024-08-06', 3, 3),
('MC-004', 'Proyecto D', '2024-08-07', '11:00:00', '2024-08-08', 4, 4),
('MC-005', 'Proyecto E', '2024-08-09', '12:00:00', '2024-08-10', 5, 5),
('MC-006', 'Proyecto F', '2024-08-11', '13:00:00', '2024-08-12', 6, 6),
('MC-007', 'Proyecto G', '2024-08-13', '14:00:00', '2024-08-14', 7, 7),
('MC-008', 'Proyecto H', '2024-08-15', '15:00:00', '2024-08-16', 8, 8),
('MC-009', 'Proyecto I', '2024-08-17', '16:00:00', '2024-08-18', 9, 9),
('MC-010', 'Proyecto J', '2024-08-19', '17:00:00', '2024-08-20', 10, 10),
('MC-011', 'Proyecto K', '2024-08-21', '18:00:00', '2024-08-22', 11, 11),
('MC-012', 'Proyecto L', '2024-08-23', '19:00:00', '2024-08-24', 12, 12),
('MC-013', 'Proyecto M', '2024-08-25', '20:00:00', '2024-08-26', 13, 13),
('MC-014', 'Proyecto N', '2024-08-27', '21:00:00', '2024-08-28', 14, 14),
('MC-015', 'Proyecto O', '2024-08-29', '22:00:00', '2024-08-30', 15, 15);

INSERT INTO Muestreos.Resultados (resultado, fAnalisis, idSignatario, idPrueba, idNorma, numControl)
VALUES
('Resultado 1', '2024-08-02', 1, 1, 1, 'MC-001'),
('Resultado 2', '2024-08-04', 2, 2, 2, 'MC-002'),
('Resultado 3', '2024-08-06', 3, 3, 3, 'MC-003'),
('Resultado 4', '2024-08-08', 4, 4, 4, 'MC-004'),
('Resultado 5', '2024-08-10', 5, 5, 5, 'MC-005'),
('Resultado 6', '2024-08-12', 6, 6, 6, 'MC-006'),
('Resultado 7', '2024-08-14', 7, 7, 7, 'MC-007'),
('Resultado 8', '2024-08-16', 8, 8, 8, 'MC-008'),
('Resultado 9', '2024-08-18', 9, 9, 9, 'MC-009'),
('Resultado 10', '2024-08-20', 10, 10, 10, 'MC-010'),
('Resultado 11', '2024-08-22', 11, 11, 11, 'MC-011'),
('Resultado 12', '2024-08-24', 12, 12, 12, 'MC-012'),
('Resultado 13', '2024-08-26', 13, 13, 13, 'MC-013'),
('Resultado 14', '2024-08-28', 14, 14, 14, 'MC-014'),
('Resultado 15', '2024-08-30', 15, 15, 15, 'MC-015');

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

SELET LocalDateTimeFmt(NOW());

# Dado una entrada, concatenar los caracteres de porciento para su uso en LIKE
# 2
DROP FUNCTION IF EXISTS LikeFmt;
DELIMITER //
CREATE FUNCTION LikeFmt(
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
CREATE FUNCTION SiglasSignatario(
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
CREATE FUNCTION CalcularBonoPorResultado(
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
CREATE FUNCTION NombreCS(
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
CREATE FUNCTION ClavePorIdSitio(
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
CREATE FUNCTION NombrePruebaPorId(
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
CREATE FUNCTION NormaPorId(
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
CREATE FUNCTION UsuarioExiste(
	usr TEXT
)
RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
	DECLARE res INT;
	SET res = 0;
	IF EXISTS (select user from mysql.user where user = usr) THEN 
		SET res = 1;
	END IF;

	RETURN res;
END //

#PROCEDIMIENTOS
# 1
DROP PROCEDURE IF EXISTS ResultadosInformePorNumeroControl;
DELIMITER //
CREATE PROCEDURE ResultadosInformePorNumeroControl(
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
CREATE PROCEDURE ParametrosPorSignatario(
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
CREATE PROCEDURE PruebasPorSignatario(
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
CREATE PROCEDURE TopPruebasMasRealizadasPorAnio(
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
    DESC
	LIMIT top;
END //

CALL TopPruebasMasRealizadasPorAnio(0, 3); 
CALL TopPruebasMasRealizadasPorAnio(2023, -3); 
CALL TopPruebasMasRealizadasPorAnio(2024, 5); 

# 5
DROP PROCEDURE IF EXISTS TopSignatariosConMasResultados;
DELIMITER //
CREATE PROCEDURE TopSignatariosConMasResultados(
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
	/*LIMIT top*/
    DESC;
END //

CALL TopSignatariosConMasResultados(5);

# 6
DROP PROCEDURE IF EXISTS BuscarSitio;
DELIMITER //
CREATE PROCEDURE BuscarSitio(
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
CREATE PROCEDURE BuscarMuestras(
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
CREATE PROCEDURE NormasPorPrueba(
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

SELECT norma, COUNT(norma) FROM Norma 
INNER JOIN DetalleNorma ON DetalleNorma.idNorma  = Norma.idNorma 
INNER JOIN Prueba ON DetalleNorma.idPrueba = Prueba.idPrueba 
GROUP BY norma 
ORDER BY COUNT(norma) 
DESC;

SELECT clave, COUNT(clave) FROM Resultados 
INNER JOIN Muestra ON Resultados.numControl = Muestra.numControl 
INNER JOIN Sitio ON Muestra.idSitio = Sitio.idSitio 
GROUP BY clave 
ORDER BY COUNT(clave) 
DESC;

SELECT Cliente.nombre, COUNT(Cliente.nombre) FROM Resultados 
INNER JOIN Muestra ON Resultados.numControl = Muestra.numControl 
INNER JOIN Sitio ON Muestra.idSitio = Sitio.idSitio 
INNER JOIN Cliente ON Sitio.idCliente = Cliente.idCliente 
GROUP BY Cliente.nombre 
ORDER BY COUNT(clave) 
DESC;

#9
DROP PROCEDURE IF EXISTS QueryFromStr;
DELIMITER //
CREATE PROCEDURE QueryFromStr(
	IN query TEXT
)
BEGIN
	PREPARE stmt FROM query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
END //

CALL QueryFromStr("SELECT * FROM Signatarios");

#10
DROP PROCEDURE IF EXISTS CrearUsuario;
DELIMITER //
CREATE PROCEDURE CrearUsuario(
	IN u TEXT,
	IN pwd TEXT,
	IN posicion TEXT
)
BEGIN
    DECLARE usr TEXT;
	IF (SELECT UsuarioExiste(u)) = 1 THEN
        ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El usuario existe";
	END IF;

	IF posicion NOT IN ("Dirección", "Muestreo", "Pruebas", "Sindicalizado") THEN
        ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Posición no válida";
	END IF;

    SET usr = CONCAT("'",u,"'@'localhost'");

	CALL QueryFromStr(CONCAT("CREATE USER ",usr," IDENTIFIED BY '",pwd,"'"));

	IF posicion IN ("Dirección") THEN
		CALL QueryFromStr(CONCAT("GRANT ALL PRIVILEGES ON Muestreos.* TO ",usr));
		CALL QueryFromStr(CONCAT("GRANT CREATE USER ON *.* TO ",usr));
	END IF;

	IF posicion IN ("Pruebas", "Sindicalizado", "Muestreo") THEN
		CALL QueryFromStr(CONCAT("GRANT EXECUTE ON Muestreos.* TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Cliente TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.DetalleNorma TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Muestra TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Norma TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Parametro TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Prueba TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Resultados TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Bitacora TO ",usr));

        CALL QueryFromStr(CONCAT("GRANT INSERT ON Muestreos.Bitacora TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT INSERT ON Muestreos.Resultados TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT UPDATE ON Muestreos.Bitacora TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT UPDATE ON Muestreos.Resultados TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT DELETE ON Muestreos.Bitacora TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT DELETE ON Muestreos.Resultados TO ",usr));
	END IF;

	IF posicion IN ("Muestreos") THEN
		CALL QueryFromStr(CONCAT("GRANT SELECT ON Muestreos.Sitio TO ",usr));
	
        CALL QueryFromStr(CONCAT("GRANT INSERT ON Muestreos.Muestras TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT UPDATE ON Muestreos.Muestras TO ",usr));
        CALL QueryFromStr(CONCAT("GRANT DELETE ON Muestreos.Muestras TO ",usr));
	END IF;

	FLUSH PRIVILEGES;
END //

DROP USER IF EXISTS 'rubenrs'@'localhost';
CALL CrearUsuario("rubenrs", "1234", "Dirección");

DROP USER IF EXISTS 'marictt'@'localhost';
CALL CrearUsuario("marictt", "1234", "Pruebas");

DROP USER IF EXISTS 'marcuspa'@'localhost';
CALL CrearUsuario("marcuspa", "1234", "Muestreo");

/*
# TIGGERS BEFORE
#1
DROP TRIGGER IF EXISTS InCliente;
DELIMITER //
CREATE TRIGGER InCliente
BEFORE INSERT ON Cliente
FOR EACH ROW
BEGIN
	IF EXISTS (SELECT * FROM Cliente WHERE nombre = NEW.nombre) THEN 
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
		SET msg = CONCAT("Ya existe un sitio con nombre: '", NEW.nombre,"'");
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
	END IF;
	
	IF (SELECT COUNT(idSitio) FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud) <> 0 THEN 
		SET claveSitioRep = (SELECT clave FROM Sitio WHERE longitud = NEW.longitud AND latitud = NEW.latitud);
		SET msg = CONCAT("Ya existe un sitio con estas coordenadas lat:", NEW.latitud, " long:", NEW.longitud);
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
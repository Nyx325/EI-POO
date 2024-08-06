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
			folio FROM DetalleSignatario 
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

	IF OLD.numControl <> NEW.OLD.numControl THEN
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

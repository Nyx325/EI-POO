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

# 9 
DROP PROCEDURE IF EXISTS VariacionResultadosRespecto;

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

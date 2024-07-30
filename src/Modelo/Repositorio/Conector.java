package Modelo.Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Conector {
    private static Connection conection = null; // Objeto que maneja la conexión
    public static Statement stmt = null; // Objeto donde colocaremos nuestras querys
    public static PreparedStatement pStmt = null;
    public static ResultSet resSet = null; // Objeto que almacena las respuestas de las querys en caso de haber

    public static Connection getConnection() throws Exception {
        if(Conector.conection == null){
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/Muestreos?characterEncoding=UTF-8";
            String usr = "rubenor";
            String pwd = "archsudoloco";
            Conector.conection = DriverManager.getConnection(servidor, usr, pwd);
        }

        return conection;
    }
}
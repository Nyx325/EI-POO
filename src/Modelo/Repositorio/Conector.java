package Modelo.Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conector {
    private static Connection conection = null; // Objeto que maneja la conexión
    public static Statement stmt = null; // Objeto donde colocaremos nuestras querys
    public static PreparedStatement pStmt = null;
    public static ResultSet resSet = null; // Objeto que almacena las respuestas de las querys en caso de haber

    public static Connection getConnection() throws Exception {
        if (Conector.conection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/Muestreos?characterEncoding=UTF-8";
            String usr = "guest";
            String pwd = "";
            Conector.conection = DriverManager.getConnection(servidor, usr, pwd);
        }

        return conection;
    }

    public static void login(String usr, String pwd) throws Exception {
        if("guest".equals(usr) && "".equals(pwd)) throw new SQLException("Incorrect credentials");
        Class.forName("com.mysql.jdbc.Driver");
        String servidor = "jdbc:mysql://localhost:3306/Muestreos?characterEncoding=UTF-8";
        Conector.conection = DriverManager.getConnection(servidor, usr, pwd);
    }
    
    public static String getUsr() throws Exception {
        Conector.pStmt = Conector.getConnection().prepareStatement("SELECT USER()");
        Conector.resSet = Conector.pStmt.executeQuery();
        Conector.resSet.next();
        return Conector.resSet.getString(1);
    }
}
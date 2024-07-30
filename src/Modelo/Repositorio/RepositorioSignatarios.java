package Modelo.Repositorio;

import java.sql.SQLException;
import java.util.List;
import Modelo.Entidad.Signatario;
import java.util.ArrayList;

public class RepositorioSignatarios {
    public boolean login(String usr, String pwd) throws Exception {
        Conector.pStmt = Conector.getConnection().prepareStatement("SELECT usr FROM Signatario WHERE usr = ?");
        Conector.pStmt.setString(1, usr);
        Conector.resSet = Conector.pStmt.executeQuery();
        
        
        try {
            String matchUsr = Conector.resSet.getString(1);
            return matchUsr.equals(usr);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Signatario> getAllSignatarios() throws Exception{
        List<Signatario> res = new ArrayList<>();

        String query = "SELECT idSignatario, primNombre, segNombre, apellidoP, apellidoM, siglas FROM Signatario";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();
        while (Conector.resSet.next()) {
            String segNombre = Conector.resSet.getString(3);
            segNombre = segNombre == null ? "" : segNombre; 

            Signatario s = new Signatario(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2),
                segNombre, 
                Conector.resSet.getString(4), 
                Conector.resSet.getString(5),
                Conector.resSet.getString(6)
            );
            System.out.println(s);
            res.add(s);
        }
        
        return res;
    }
}

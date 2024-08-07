package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Parametro;

public class RepositorioParametro {

    protected Parametro fromResSet() throws Exception {
        return new Parametro(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2));
    }
    
    public List<Parametro> getAllParams() throws Exception {
        List<Parametro> res = new ArrayList<>();
        
        String query = "SELECT * FROM Parametro ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }
        
        return res;
    }

    public List<Parametro> searchParametroBySignatario(long idSignatario) throws Exception {
        List<Parametro> res = new ArrayList<>();
        String query = "CALL ParametrosPorSignatario(?)";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

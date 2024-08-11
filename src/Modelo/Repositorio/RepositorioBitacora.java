package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioBitacora {
    public List<String> getBitacora() throws Exception{
        List<String> res = new ArrayList<>();

        String query = "SELECT mensaje FROM Bitacora ORDER BY folio DESC";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(Conector.resSet.getString(1));
        }

        return res;
    }
}

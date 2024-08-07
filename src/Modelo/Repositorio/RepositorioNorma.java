package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Norma;

public class RepositorioNorma {
    protected Norma fromResSet() throws Exception {
        return new Norma(
            Conector.resSet.getLong(1), 
            Conector.resSet.getString(2), 
            Conector.resSet.getString(3), 
            Conector.resSet.getLong(4));
    }

    public List<Norma> getNormaByPrueba(long idPrueba) throws Exception {
        List<Norma> res = new ArrayList<>();

        String query = "CALL NormasPorPrueba(?);";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idPrueba);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

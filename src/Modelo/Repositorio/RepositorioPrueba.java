package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Prueba;

public class RepositorioPrueba {
    public Prueba fromResSet() throws Exception {
        return new Prueba(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2),
                Conector.resSet.getLong(3));
    }

    public List<Prueba> searchBy(long idSignatario, long idParametro) throws Exception {
        List<Prueba> res = new ArrayList<>();

        String query = "CALL Muestreos.PruebasPorSignatario(?, ?)";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idParametro);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Parametro;

public class RepositorioParametro {

    private Parametro fromResSet() throws Exception {
        return new Parametro(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2));
    }

    public List<Parametro> searchParametroBySignatario(long idSignatario) throws Exception {
        List<Parametro> res = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT Parametro.idParametro, Parametro.nombre AS Parametro ");
        query.append("FROM DetalleSignatarios ");
        query.append("INNER JOIN Prueba ON DetalleSignatarios.idPrueba = Prueba.idPrueba ");
        query.append("INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro ");
        query.append("WHERE DetalleSignatarios.idSignatario = ? ");
        query.append("GROUP BY Parametro.nombre ");
        query.append("ORDER BY Parametro.nombre;");

        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

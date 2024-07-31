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

        StringBuilder query = new StringBuilder();
        query.append("SELECT Prueba.idPrueba, Prueba.Nombre, Parametro.idParametro FROM DetalleSignatarios ");
        query.append("INNER JOIN Prueba ON DetalleSignatarios.idPrueba  = Prueba.idPrueba ");
        query.append("INNER JOIN Parametro ON Prueba.idParametro = Parametro.idParametro ");
        query.append("WHERE ");
        query.append("DetalleSignatarios.idSignatario = ? ");
        query.append("AND Prueba.idParametro = ?");

        System.out.println(query.toString()+"\n");
        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idParametro);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

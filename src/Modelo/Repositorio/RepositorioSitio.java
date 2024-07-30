package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;
import Modelo.Entidad.Sitio;

public class RepositorioSitio {

    private Sitio fromResSet() throws Exception {
            Sitio s = new Sitio(
                    Conector.resSet.getLong(1),
                    Conector.resSet.getString(2),
                    Conector.resSet.getString(3),
                    Conector.resSet.getString(4),
                    Conector.resSet.getString(5),
                    Conector.resSet.getString(6),
                    Conector.resSet.getString(7),
                    Conector.resSet.getString(8),
                    Conector.resSet.getString(9),
                    Conector.resSet.getString(10),
                    Conector.resSet.getString(11),
                    Conector.resSet.getString(12),
                    Conector.resSet.getString(13),
                    Conector.resSet.getString(14),
                    Conector.resSet.getString(15),
                    Conector.resSet.getString(16),
                    Conector.resSet.getString(17),
                    Conector.resSet.getLong(18));

            return s;
    }

    public List<Sitio> getAllSitios() throws Exception {
        List<Sitio> res = new ArrayList<>();

        String query = "SELECT * FROM Sitio;";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }
        return res;
    }

    public List<Sitio> search(String clave, String latitud, String longitud, String municipio, String estado,
            String nombre) throws Exception {
        boolean and = false;
        List<Sitio> res = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Sitio WHERE ");
        query.append(" clave LIKE '%"+clave+"%'");
        query.append(" AND latitud LIKE '%"+latitud+"%'");
        query.append(" AND longitud LIKE '%"+longitud+"%'");
        query.append(" AND municipio LIKE '%"+municipio+"%'");
        query.append(" AND edo LIKE '%"+estado+"%'");
        query.append(" AND nombre LIKE '%"+nombre+"%'");
        
        System.out.println(query.toString());
        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

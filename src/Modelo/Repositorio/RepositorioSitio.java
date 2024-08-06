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
        
        List<Sitio> res = new ArrayList<>();
        String query = "CALL Muestreos.BuscarSitio(?, ?, ?, ?, ?, ?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.pStmt.setString(1, clave);
        Conector.pStmt.setString(2, latitud);
        Conector.pStmt.setString(3, longitud);
        Conector.pStmt.setString(4, municipio);
        Conector.pStmt.setString(5, estado);
        Conector.pStmt.setString(6, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

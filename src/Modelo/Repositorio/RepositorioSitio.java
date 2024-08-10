package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;
import Modelo.Entidad.Sitio;

public class RepositorioSitio {

    protected Sitio fromResSet() throws Exception {
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

    /*
     * A diferencia de con los otros datos, debemos considerar que haya más de un sitio
     * con esas claves, aunque no debería, pero es error de sus datos, no nuestra jaja
     */
    public List<Sitio> searchByClave(String clave) throws Exception {
        List<Sitio> res = new ArrayList<>();
        String query = "SELECT * FROM Sitio WHERE clave = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, clave);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    protected void addAI(Sitio s) throws Exception {
        String query = "INSERT INTO Sitio VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, s.clave);
        Conector.pStmt.setString(2, s.nombre);
        Conector.pStmt.setString(3, s.cuenca);
        Conector.pStmt.setString(4, s.cAcuifero);
        Conector.pStmt.setString(5, s.acuifero);
        Conector.pStmt.setString(6, s.organismo);
        Conector.pStmt.setString(7, s.dirLocal);
        Conector.pStmt.setString(8, s.edo);
        Conector.pStmt.setString(9, s.municipio);
        Conector.pStmt.setString(10, s.cAgua);
        Conector.pStmt.setString(11, s.tipoC);
        Conector.pStmt.setString(12, s.subtipoC);
        Conector.pStmt.setString(13, s.latitud);
        Conector.pStmt.setString(14, s.longitud);
        Conector.pStmt.setString(15, s.uso);
        Conector.pStmt.setString(16, s.lugarT);
        Conector.pStmt.setLong(17, s.idCliente);
        System.out.println(Conector.pStmt);
        Conector.pStmt.executeUpdate();
    }

    protected void add(Sitio s) throws Exception {
        String query = "INSERT INTO Sitio VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, s.idCliente);
        Conector.pStmt.setString(2, s.clave);
        Conector.pStmt.setString(3, s.nombre);
        Conector.pStmt.setString(4, s.cuenca);
        Conector.pStmt.setString(5, s.cAcuifero);
        Conector.pStmt.setString(6, s.acuifero);
        Conector.pStmt.setString(7, s.organismo);
        Conector.pStmt.setString(8, s.dirLocal);
        Conector.pStmt.setString(9, s.edo);
        Conector.pStmt.setString(10,s.municipio);
        Conector.pStmt.setString(11, s.cAgua);
        Conector.pStmt.setString(12, s.tipoC);
        Conector.pStmt.setString(13, s.subtipoC);
        Conector.pStmt.setString(14, s.latitud);
        Conector.pStmt.setString(15, s.longitud);
        Conector.pStmt.setString(16, s.uso);
        Conector.pStmt.setString(17, s.lugarT);
        Conector.pStmt.setLong(18, s.idCliente);
        System.out.println(Conector.pStmt);
        Conector.pStmt.executeUpdate();
    }

    public void remove(Sitio s) throws Exception {
        String query = "REMOVE FROM Sitio WHERE idSitio = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, s.idLugar);
        Conector.pStmt.executeUpdate();
    }
}

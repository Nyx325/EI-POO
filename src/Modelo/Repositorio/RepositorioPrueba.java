package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.DetalleSignatario;
import Modelo.Entidad.Prueba;

public class RepositorioPrueba {
    protected Prueba fromResSet() throws Exception {
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
    
    public List<Prueba> searchBy(long idParametro) throws Exception {
        List<Prueba> res = new ArrayList<>();
        
        String query = "SELECT * FROM Prueba WHERE idParametro = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idParametro);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }
        
        return res;
    }

    public List<DetalleSignatario> searchDetalleBy(long idSignatario) throws Exception {
        List<DetalleSignatario> pruebas = new ArrayList<>();

        String query = "SELECT * FROM DetalleSignatarios WHERE idSignatario = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            DetalleSignatario d = new DetalleSignatario(
                    Conector.resSet.getLong(1), 
                    Conector.resSet.getLong(2), 
                    Conector.resSet.getLong(3));
            pruebas.add(d);
        }

        return pruebas;
    }

    public boolean signatarioContienePrueba(long idSignatario, long idPrueba) throws Exception {
        String query = "SELECT * FROM DetalleSignatarios WHERE idSignatario = ? AND idPrueba = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idPrueba);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next();
    }

    public void agregarPruebaASignatario(long idSignatario, long idPrueba) throws Exception{
        if(signatarioContienePrueba(idSignatario, idPrueba)) return;
        String query = "INSERT INTO DetalleSignatarios VALUES (0,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idPrueba);
        Conector.pStmt.executeUpdate();
    }

    public void quitarPruebaASignatario(long idSignatario, long idPrueba) throws Exception{
        if(!signatarioContienePrueba(idSignatario, idPrueba)) return;
        String query = "DELETE FROM DetalleSignatarios WHERE idSignatario = ? AND idPrueba = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idPrueba);
        Conector.pStmt.executeUpdate();
    }

    public void updatePruebasModify(long idDetalle, long idSignatario) throws Exception{
        String query = "UPDATE DetalleSignatarios SET idSignatario = ? WHERE idDetalle = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.pStmt.setLong(2, idDetalle);
        Conector.pStmt.executeUpdate();
    }
}

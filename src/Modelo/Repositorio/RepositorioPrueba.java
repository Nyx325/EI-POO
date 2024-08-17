package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.DetalleSignatario;
import Modelo.Entidad.Prueba;
import java.util.HashMap;
import java.util.Map;

public class RepositorioPrueba {
    protected Prueba fromResSet() throws Exception {
        return new Prueba(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2),
                Conector.resSet.getLong(3));
    }

    public List<Prueba> getAllPrueba() throws Exception {
        List<Prueba> res = new ArrayList<>();

        String query = "SELECT * FROM Prueba ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
    
    public List<Prueba> searchByName(String nombre) throws Exception {
        List<Prueba> res = new ArrayList<>();

        String query = "SELECT * FROM Prueba WHERE nombre LIKE LikeFmt(?) ORDER BY nombre";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
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

    public Prueba getByNombre(String nombre) throws Exception {
        String query = "SELECT * FROM Prueba WHERE nombre = ? ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next() ? fromResSet() : null;
    }

    public Prueba getById(long idPrueba) throws Exception {
        String query = "SELECT * FROM Prueba WHERE idPrueba = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idPrueba);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next() ? fromResSet() : null;
    }

    public List<Prueba> searchBy(long idParametro) throws Exception {
        List<Prueba> res = new ArrayList<>();
        
        String query = "SELECT * FROM Prueba WHERE idParametro = ? ORDER BY nombre";
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
    
    public Map<String, Long> pruebasRealizadasPorAnio(long anio, long top) throws Exception {
        Map<String, Long> res = new HashMap<>();
        String query = "CALL TopPruebasMasRealizadasPorAnio(?, ?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, anio);
        Conector.pStmt.setLong(2, top);
        Conector.resSet = Conector.pStmt.executeQuery();
        
        while(Conector.resSet.next()){
            res.put(
                Conector.resSet.getString(1), 
                Conector.resSet.getLong(2)
            );
        }
        
        return res;
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

    public void addAI(Prueba p) throws Exception {
        String query = "INSERT INTO Prueba VALUES (0,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, p.nombre);
        Conector.pStmt.setLong(2, p.idParametro);
        Conector.pStmt.executeUpdate();
    }

    protected void add(Prueba p) throws Exception {
        String query = "INSERT INTO Prueba VALUES (?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, p.idPrueba);
        Conector.pStmt.setString(2, p.nombre);
        Conector.pStmt.setLong(3, p.idParametro);
        Conector.pStmt.executeUpdate();
    }

    protected void update(Prueba p) throws Exception {
        String query = "UPDATE Prueba SET nombre = ?, idParametro = ? WHERE idPrueba = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, p.nombre);
        Conector.pStmt.setLong(2, p.idParametro);
        Conector.pStmt.setLong(3, p.idPrueba);
        Conector.pStmt.executeUpdate();
    }

    public void remove(Prueba p) throws Exception {
        String query = "DELETE FROM Prueba WHERE idPrueba = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, p.idPrueba);
        Conector.pStmt.executeUpdate();
    }
}

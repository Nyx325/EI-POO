package Modelo.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import Modelo.Entidad.Parametro;

public class RepositorioParametro {

    protected Parametro fromResSet() throws Exception {
        return new Parametro(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2));
    }
    
    public List<Parametro> getAllParams() throws Exception {
        List<Parametro> res = new ArrayList<>();
        
        String query = "SELECT * FROM Parametro ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }
        
        return res;
    }

    public List<Parametro> searchParametroBySignatario(long idSignatario) throws Exception {
        List<Parametro> res = new ArrayList<>();
        String query = "CALL ParametrosPorSignatario(?)";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
    
    public Parametro getById(long id) throws Exception{
        String query = "SELECT * FROM Parametro WHERE idParametro = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, id);
        Conector.resSet = Conector.pStmt.executeQuery();
        
        return Conector.resSet.next() ? fromResSet() : null;
    }
    
    public Parametro getByName(String nombre) throws Exception {
        String query = "SELECT * FROM Parametro WHERE nombre = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();
        
        return Conector.resSet.next() ? fromResSet() : null;
    }
    
    public List<Parametro> searchByName(String nombre) throws Exception {
        List<Parametro> res = new ArrayList<>();
        String query = "SELECT * FROM Parametro WHERE nombre LIKE LikeFmt(?) ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();
        
        while(Conector.resSet.next()){
            res.add(fromResSet());
        }
        
        return res;
    }
    
    protected void addAI(Parametro p) throws Exception{
        String query = "INSERT INTO Parametro VALUES (0,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, p.nombre);
        Conector.pStmt.executeUpdate();
    }
    
    protected void modify(Parametro p) throws Exception {
        Parametro original = getById(p.idParametro);
        if(original == null) throw new SQLException("No existe el parámetro");
        if(p.equals(original)) return;
        
        String query = "UPDATE Parametro SET nombre = ? WHERE idParametro = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, p.nombre);
        Conector.pStmt.setLong(2, p.idParametro);
        Conector.pStmt.executeUpdate();
    }
    
    public void remove(Parametro p) throws Exception {
        String query = "DELETE FROM Parametro WHERE idParametro = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, p.idParametro);
        Conector.pStmt.executeUpdate();
    }
}

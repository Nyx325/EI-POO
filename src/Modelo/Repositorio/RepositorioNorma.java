package Modelo.Repositorio;

import java.sql.SQLException;
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
    
    public List<Norma> getAllNormas() throws Exception {
        List<Norma> res = new ArrayList<>();

        String query = "SELECT * FROM Norma ORDER BY norma";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public Norma getNormaById(long id) throws Exception {
        String query = "SELECT * FROM Norma WHERE idNorma = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, id);
        Conector.resSet = Conector.pStmt.executeQuery();

        if(!Conector.resSet.next()) throw new SQLException("No existe la norma");

        return fromResSet();
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

    public Norma getNormaByNombre(String nombre) throws Exception {
        String query = "SELECT * FROM Norma WHERE norma = ? ORDER BY norma";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();

        return Conector.resSet.next() ? fromResSet() : null;
    }
    
    public List<Norma> searchNormaByNombre(String nombre) throws Exception {
        List<Norma> list = new ArrayList<>();

        String query = "SELECT * FROM Norma WHERE norma LIKE LikeFmt(?) ORDER BY norma";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, nombre);
        Conector.resSet = Conector.pStmt.executeQuery();

        while(Conector.resSet.next()){
            list.add(fromResSet());
        }

        return list;
    }

    protected void addAI(Norma n) throws Exception {
        String query = "INSERT INTO Norma VALUES (0,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, n.norma);
        Conector.pStmt.setString(2, n.unidades);
        Conector.pStmt.setLong(3, n.tipoVentana);
        Conector.pStmt.executeUpdate();
    }

    protected void add(Norma n) throws Exception {
        String query = "INSERT INTO Norma VALUES (?,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, n.idNorma);
        Conector.pStmt.setString(2, n.norma);
        Conector.pStmt.setString(3, n.unidades);
        Conector.pStmt.setLong(4, n.tipoVentana);
        Conector.pStmt.executeUpdate();
    }

    public void remove(Norma n) throws Exception {
        String query = "DELETE FROM Norma WHERE idNorma = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, n.idNorma);
        Conector.pStmt.executeUpdate();
    }

    protected void modify(Norma n) throws Exception {
        Norma original = getNormaById(n.idNorma);
        if(original.equals(n)) return;

        String query = "UPDATE Norma SET norma = ?, unidades = ?, tipoVentana = ? WHERE idNorma = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, n.norma);
        Conector.pStmt.setString(2, n.unidades);
        Conector.pStmt.setLong(3, n.tipoVentana);
        Conector.pStmt.setLong(4, n.idNorma);
        Conector.pStmt.executeUpdate();
    }
}

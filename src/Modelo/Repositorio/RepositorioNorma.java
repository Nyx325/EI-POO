package Modelo.Repositorio;

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

    protected void remove(Norma n) throws Exception {

    }
}

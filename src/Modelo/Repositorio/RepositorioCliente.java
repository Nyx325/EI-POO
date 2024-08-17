package Modelo.Repositorio;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import Modelo.Entidad.Cliente;

public class RepositorioCliente {
    protected Cliente fromResSet() throws Exception {
        return new Cliente(
                Conector.resSet.getLong(1), 
                Conector.resSet.getString(2));
    }

    public List<Cliente> getAllClientes() throws Exception {
        List<Cliente> res = new ArrayList<>();
        String query = "SELECT * FROM Cliente ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public List<Cliente> searchByName(String name) throws Exception {
        List<Cliente> res = new ArrayList<>();
        String query = "SELECT * FROM Cliente WHERE nombre LIKE LikeFmt(?) ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, name);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public Cliente getById(long idCliente) throws Exception {
        String query = "SELECT * FROM Cliente WHERE idCliente = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idCliente);
        Conector.resSet = Conector.pStmt.executeQuery();

        return Conector.resSet.next() ? fromResSet() : null;
    }

    public Cliente getByName(String name) throws Exception {
        String query = "SELECT * FROM Cliente WHERE nombre = ? ORDER BY nombre";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, name);
        Conector.resSet = Conector.pStmt.executeQuery();

        return Conector.resSet.next() ? fromResSet() : null;
    }

    protected void addAI(Cliente c) throws Exception {
        String query = "INSERT INTO Cliente VALUES (0,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, c.nombre);
        Conector.pStmt.executeUpdate();
    }

    public void remove(Cliente c) throws Exception {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, c.idCliente);
        Conector.pStmt.executeUpdate();
    }

    public void modify(Cliente c) throws Exception {
        String query = "UPDATE Cliente SET nombre = ? WHERE idCliente = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, c.nombre);
        Conector.pStmt.setLong(2, c.idCliente);
        Conector.pStmt.executeUpdate();
    }

    public Map<String, Long> clientesFrecuentes() throws Exception {
        String query ="SELECT Cliente.nombre, COUNT(Cliente.nombre) FROM Resultados ";
        query+="INNER JOIN Muestra ON Resultados.numControl = Muestra.numControl ";
        query+="INNER JOIN Sitio ON Muestra.idSitio = Sitio.idSitio ";
        query+="INNER JOIN Cliente ON Sitio.idCliente = Cliente.idCliente ";
        query+="GROUP BY Cliente.nombre";

        Map<String,Long> res = new HashMap<>();

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.put(
                Conector.resSet.getString(1), 
                Conector.resSet.getLong(2)
            );
        }

        return res;
    }
}

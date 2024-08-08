package Modelo.Repositorio;

import java.util.List;
import Modelo.Entidad.Signatario;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioSignatarios {
    protected Signatario fromResSet() throws Exception {
        String segNombre = Conector.resSet.getString(3);
        segNombre = segNombre == null ? "" : segNombre;
        String fIngresoStr = Conector.resSet.getString(8);
        String fNacimientoStr = Conector.resSet.getString(9);
        fIngresoStr = fIngresoStr == null ? "1800-01-01" : fIngresoStr;
        fNacimientoStr = fNacimientoStr == null ? "1800-01-01" : fNacimientoStr;

        return new Signatario(
                Conector.resSet.getLong(1),
                Conector.resSet.getString(2),
                segNombre,
                Conector.resSet.getString(4),
                Conector.resSet.getString(5),
                Conector.resSet.getFloat(6),
                Conector.resSet.getFloat(7),
                LocalDate.parse(fIngresoStr),
                LocalDate.parse(fNacimientoStr),
                Conector.resSet.getString(10),
                Conector.resSet.getString(11),
                Conector.resSet.getString(12));
    }

    protected void add(Signatario s) throws Exception {
        String query = "INSERT INTO Signatario VALUES (0,?,?,?,?,?,?,?,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, s.primNombre);
        Conector.pStmt.setString(2, s.segNombre);
        Conector.pStmt.setString(3, s.apellidoP);
        Conector.pStmt.setString(4, s.apellidoM);
        Conector.pStmt.setFloat(5, s.sueldo);
        Conector.pStmt.setFloat(6, s.bono);
        Conector.pStmt.setString(7, s.fIngreso.toString());
        Conector.pStmt.setString(8, s.fNacimiento.toString());
        Conector.pStmt.setString(9, s.posicion);
        Conector.pStmt.setString(10, s.usuario);
        Conector.pStmt.executeUpdate();
    }

    public void remove(Signatario s) throws Exception {
        String query = "DELETE FROM Signatario WHERE idSignatario = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, s.idSignatario);
        Conector.pStmt.executeUpdate();
    }

    public List<Signatario> getAllSignatarios() throws Exception {
        List<Signatario> res = new ArrayList<>();

        String query = "SELECT *, SiglasSignatario(idSignatario) FROM Signatario";

        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();
        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public Signatario searchBy(long idSignatario) throws Exception {
        String query = "SELECT *, SiglasSignatario(idSignatario) FROM Signatario where idSignatario = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next() ? fromResSet() : null;
    }

    public Signatario searchBy(String usr) throws Exception {
        String query = "SELECT *, SiglasSignatario(idSignatario) FROM Signatario WHERE usuario = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, usr);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next() ? fromResSet() : null;
    }
    
    public boolean usuarioEnUso(Signatario s) throws Exception {
        String query = "SELECT *, SiglasSignatario(idSignatario) FROM Signatario WHERE usuario = ? AND idSignatario != ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, s.usuario);
        Conector.pStmt.setLong(2, s.idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();
        return Conector.resSet.next();
    }

    public String getSiglas(long id) throws Exception {
        String query = "SELECT SiglasSignatario(?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, id);
        Conector.resSet = Conector.pStmt.executeQuery();
        Conector.resSet.next();
        return Conector.resSet.getString(1);
    }
}

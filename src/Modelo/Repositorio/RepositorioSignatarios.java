package Modelo.Repositorio;

import java.util.List;
import Modelo.Entidad.Signatario;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioSignatarios {
    private Signatario fromResSet() throws Exception {
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
                Conector.resSet.getString(11));
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
        return fromResSet();
    }
}

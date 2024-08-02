package Modelo.Repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Muestra;
import java.time.LocalTime;

public class RepositorioMuestra {

    private Muestra fromResSet() throws Exception {
        return new Muestra(
                Conector.resSet.getString(1),
                Conector.resSet.getString(2),
                LocalDate.parse(Conector.resSet.getString(3)),
                LocalTime.parse(Conector.resSet.getString(4)),
                LocalDate.parse(Conector.resSet.getString(5)),
                Conector.resSet.getLong(6),
                Conector.resSet.getLong(7));
    }

    public Muestra searchByNumControl(String numControl) throws Exception {
        String query = "SELECT * FROM Muestra WHERE numControl = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, numControl);
        Conector.resSet = Conector.pStmt.executeQuery();

        return fromResSet();
    }

    public List<Muestra> getAllMuestras() throws Exception {
        List<Muestra> res = new ArrayList<>();
        String query = "SELECT * FROM Muestra";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public List<Muestra> searchBy(String numC, String proj, LocalDate fM, LocalTime hM, LocalDate fR) throws Exception {
        List<Muestra> res = new ArrayList<>();
        boolean validFM = false, validFR = false;

        StringBuilder query = new StringBuilder(); // LocalDateTime.parse()
        query.append("SELECT * FROM Muestra");
        query.append(" WHERE numControl LIKE \"%" + numC + "%\" ");
        query.append("AND proyecto LIKE \"%" + proj + "%\" ");
        if (fM != null) {
            validFM = true;
            query.append(" AND fMuestreo LIKE \"%" + fM + "%\"");
        }

        if (hM != null) {
            validFM = true;
            query.append(" AND hMuestreo LIKE \"%" + hM + "%\"");
        }

        if (fR != null) {
            validFR = true;
            query.append(" AND fRecepcion LIKE \"%" + fR + "%\"");
        }

        System.out.println(query);
        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

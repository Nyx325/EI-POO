package Modelo.Repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Modelo.Entidad.Muestra;

public class RepositorioMuestra {

    private Muestra fromResSet() throws Exception {
        return new Muestra(
                Conector.resSet.getString(1),
                Conector.resSet.getString(2),
                LocalDateTime.parse(Conector.resSet.getString(3)),
                LocalDate.parse(Conector.resSet.getString(4)),
                Conector.resSet.getLong(5),
                Conector.resSet.getLong(6));
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
        String fLocalDate = "CONCAT(DATE(fMuestreo),\"T\", LEFT(TIME(fMuestreo),5))"; // Formato legible para
                                                                                      // LocalDateTime.parse()
        String query = "SELECT numControl, proyecto, " + fLocalDate + ", fRecepcion, muestreador, idSitio FROM Muestra";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }

    public List<Muestra> searchBy(String numC, String proj, LocalDateTime fM, LocalDate fR) throws Exception {
        List<Muestra> res = new ArrayList<>();
        boolean validFM = false, validFR = false;
        String fMuestreo = "";

        if (fM != null)
            fMuestreo = fM.getDayOfYear() + "-" + fM.getMonthValue() + "-" + fM.getDayOfMonth() + " " + fM.getHour()
                    + ":" + fM.getMinute() + ":00";

        StringBuilder query = new StringBuilder();
        String fLocalDate = "CONCAT(DATE(fMuestreo),\"T\", LEFT(TIME(fMuestreo),5))"; // Formato legible para
                                                                                      // LocalDateTime.parse()
        query.append("SELECT numControl, proyecto," + fLocalDate + ", fRecepcion, muestreador, idSitio FROM Muestra");
        query.append(" WHERE numControl LIKE \"%" + numC + "%\" ");
        query.append("AND proyecto LIKE \"%" + proj + "%\" ");
        if (fM != null) {
            validFM = true;
            query.append(" AND fMuestreo LIKE \"%" + fMuestreo + "%\"");
        }

        if (fR != null) {
            validFR = true;
            query.append(" AND fRecepcion LIKE \"%" + fR + "%\"");
        }

        System.out.println("Query: " + query.toString());
        Conector.pStmt = Conector.getConnection().prepareStatement(query.toString());
        Conector.resSet = Conector.pStmt.executeQuery();

        while (Conector.resSet.next()) {
            res.add(fromResSet());
        }

        return res;
    }
}

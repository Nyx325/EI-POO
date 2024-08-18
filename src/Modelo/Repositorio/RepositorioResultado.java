package Modelo.Repositorio;

import Modelo.Entidad.Resultado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioResultado {
    public Resultado fromResSet() throws Exception {
        return new Resultado(
                Conector.resSet.getLong(1), 
                Conector.resSet.getString(2), 
                LocalDate.parse(Conector.resSet.getString(3)), 
                Conector.resSet.getLong(4), 
                Conector.resSet.getLong(5), 
                Conector.resSet.getLong(6), 
                Conector.resSet.getString(7));
    }
    
    public List<Resultado> listFromResSet() throws Exception {
        List<Resultado> res = new ArrayList<>();
        
        while(Conector.resSet.next()){
            res.add(fromResSet());
        }
        
        return res;
    }
    
    public void addAI(Resultado r) throws Exception {
        String query = "INSERT INTO Resultados VALUES (0,?,?,?,?,?,?)";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setString(1, r.resultado);
        Conector.pStmt.setString(2, r.fAnalisis.toString());
        Conector.pStmt.setLong(3, r.idSignatario);
        Conector.pStmt.setLong(4, r.idPrueba);
        Conector.pStmt.setLong(5, r.idNorma);
        Conector.pStmt.setString(6, r.numControl);
        Conector.pStmt.executeUpdate();
    }
    
    public List<Resultado> getResSit(long idSignatario) throws Exception {
        String query = "SELECT * FROM Resultados WHERE idSignatario = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, idSignatario);
        Conector.resSet = Conector.pStmt.executeQuery();
        return listFromResSet();
    }
    
    public void delete(Resultado r) throws Exception {
        String query = "DELETE FROM Resultados WHERE folio = ?";
        Conector.pStmt = Conector.getConnection().prepareStatement(query);
        Conector.pStmt.setLong(1, r.folio);
        Conector.pStmt.executeUpdate();
    }
}

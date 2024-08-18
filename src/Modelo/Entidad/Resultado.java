package Modelo.Entidad;

import java.time.LocalDate;

public class Resultado {
    public long folio;
    public String resultado;
    public LocalDate fAnalisis;
    public long idSignatario;
    public long idPrueba;
    public long idNorma;
    public String numControl;

    public Resultado() {
    }

    public Resultado(long folio, String resultado, LocalDate fAnalisis, long idSignatario, long idPrueba, long idNorma,
        String numControl) {
        this.folio = folio;
        this.resultado = resultado;
        this.fAnalisis = fAnalisis;
        this.idSignatario = idSignatario;
        this.idPrueba = idPrueba;
        this.idNorma = idNorma;
        this.numControl = numControl;
    }

}

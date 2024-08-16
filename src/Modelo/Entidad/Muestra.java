package Modelo.Entidad;

import java.time.LocalDate;
import java.time.LocalTime;

public class Muestra {
    public String numControl;
    public String proyecto;
    public LocalDate fMuestreo;
    public LocalTime hMuestreo;
    public LocalDate fRecepcion;
    public Long muestreador;
    public Long idSitio;

    public Muestra() {
    }

    public Muestra(String numControl, String proyecto, LocalDate fMuestreo, LocalTime hMuestreo, LocalDate fRecepcion,
            long muestreador,
            long idSitio) {
        this.numControl = numControl;
        this.proyecto = proyecto;
        this.fMuestreo = fMuestreo;
        this.hMuestreo = hMuestreo;
        this.fRecepcion = fRecepcion;
        this.muestreador = muestreador;
        this.idSitio = idSitio;
    }

    @Override
    public String toString() {
        return "[" + numControl + "] " + proyecto + " " + fMuestreo;
    }
}

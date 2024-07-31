package Modelo.Entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Muestra {
    public String numControl;
    public String proyecto;
    public LocalDateTime fMuestreo;
    public LocalDate fRecepcion;
    public long muestreador;
    public long idSitio;

    public Muestra(){}

    public Muestra(String numControl, String proyecto, LocalDateTime fMuestreo, LocalDate fRecepcion, long muestreador,
            long idSitio) {
        this.numControl = numControl;
        this.proyecto = proyecto;
        this.fMuestreo = fMuestreo;
        this.fRecepcion = fRecepcion;
        this.muestreador = muestreador;
        this.idSitio = idSitio;
    }

    @Override
    public String toString() {
        return "[" + numControl + "] " + proyecto + " " + fMuestreo;
    }
}

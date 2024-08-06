package Modelo.Entidad;

import java.time.LocalDate;

public class Signatario {
    public long idSignatario;
    public String primNombre;
    public String segNombre;
    public String apellidoP;
    public String apellidoM;
    public float sueldo;
    public float bono;
    public LocalDate fIngreso;
    public LocalDate fNacimiento;
    public String posicion;
    public String siglas;

    public static final String POSICION_DIRECCION = "Dirección";
    public static final String POSICION_MUESTREO = "Muestreo";
    public static final String POSICION_PRUEBAS = "Pruebas";
    public static final String POSICION_SINDICALIZADO = "Sindicalizado";

    public Signatario() {
    }

    public Signatario(long idSignatario, String primNombre, String segNombre, String apellidoP, String apellidoM,
            float sueldo, float bono, LocalDate fIngreso, LocalDate fNacimiento, String posicion, String siglas) {
        this.idSignatario = idSignatario;
        this.primNombre = primNombre;
        this.segNombre = segNombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.sueldo = sueldo;
        this.bono = bono;
        this.fIngreso = fIngreso;
        this.fNacimiento = fNacimiento;
        this.posicion = posicion;
        this.siglas = siglas;
    }



    @Override
    public String toString() {
        return "[" + siglas + "]" + " " + primNombre + " " + segNombre + " " + apellidoP + " " + apellidoM;
    }
}

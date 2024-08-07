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
    public String usuario;
    public String siglas;

    public static final String POSICION_DIRECCION = "Dirección";
    public static final String POSICION_MUESTREO = "Muestreo";
    public static final String POSICION_PRUEBAS = "Pruebas";
    public static final String POSICION_SINDICALIZADO = "Sindicalizado";

    public Signatario() {
        this.idSignatario = -1;
        this.primNombre = "";
        this.segNombre = "";
        this.apellidoP = "";
        this.apellidoM = "";
        this.sueldo = 0;
        this.bono = 0;
        this.fIngreso = null;
        this.fNacimiento = null;
        this.posicion = "";
        this.usuario = "";
        this.siglas = "";
    }

    public Signatario(long idSignatario, String primNombre, String segNombre, String apellidoP, String apellidoM,
            float sueldo, float bono, LocalDate fIngreso, LocalDate fNacimiento, String posicion, String usuario,
            String siglas) {
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
        this.usuario = usuario;
        this.siglas = siglas;
    }

    public boolean equalsExceptId(Signatario s){
        return  this.primNombre == s.primNombre &&
                this.segNombre == s.segNombre &&
                this.apellidoP == s.apellidoP &&
                this.apellidoM == s.apellidoM &&
                this.sueldo == s.sueldo &&
                this.bono == s.bono &&
                this.fIngreso == s.fIngreso &&
                this.fNacimiento == s.fNacimiento &&
                this.posicion == s.posicion &&
                this.usuario == s.usuario &&
                this.siglas == s.siglas;
    }

    @Override
    public String toString() {
        return "[" + siglas + "]" + " " + primNombre + " " + segNombre + " " + apellidoP + " " + apellidoM;
    }
}

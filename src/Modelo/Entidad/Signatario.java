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
        return  primNombre.equals(s.primNombre) &&
                segNombre.equals(s.segNombre) &&
                apellidoP.equals(s.apellidoP) &&
                apellidoM.equals(s.apellidoM) &&
                sueldo == s.sueldo &&
                bono == s.bono &&
                fIngreso.equals(s.fIngreso) &&
                fNacimiento.equals(s.fNacimiento) &&
                posicion.equals(s.posicion) &&
                usuario.equals(s.usuario) &&
                siglas.equals(s.siglas);
    }

    public boolean equals(Signatario s){
            return  idSignatario == s.idSignatario &&
                    primNombre.equals(s.primNombre) &&
                    segNombre.equals(s.segNombre) &&
                    apellidoP.equals(s.apellidoP) &&
                    apellidoM.equals(s.apellidoM) &&
                    sueldo == s.sueldo &&
                    bono == s.bono &&
                    fIngreso.equals(s.fIngreso) &&
                    fNacimiento.equals(s.fNacimiento) &&
                    posicion.equals(s.posicion) &&
                    usuario.equals(s.usuario) &&
                    siglas.equals(s.siglas);
    }

    @Override
    public String toString() {
        return "[" + siglas + "]" + " " + primNombre + " " + segNombre + " " + apellidoP + " " + apellidoM;
    }
    
    public String getNombreCompleto(){
        return primNombre+" "+segNombre+" "+apellidoP+" "+apellidoM;
    }
    
    public void printData(){
        System.out.println("idSignatario: "+this.idSignatario);
        System.out.println("primNombre: "+this.primNombre);
        System.out.println("segNombre: "+this.segNombre);
        System.out.println("apellidoP: "+this.apellidoP);
        System.out.println("apellidoM: "+this.apellidoM);
        System.out.println("sueldo: "+this.sueldo);
        System.out.println("bono: "+this.bono);
        System.out.println("fIngreso: "+this.fIngreso);
        System.out.println("fNacimiento: "+this.fNacimiento);
        System.out.println("posicion: "+this.posicion);
        System.out.println("usuario: "+this.usuario);
        System.out.println("siglas: "+this.siglas);
    }
}

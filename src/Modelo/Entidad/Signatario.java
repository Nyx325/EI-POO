package Modelo.Entidad;

public class Signatario {
    public long idSignatario;
    public String primNombre;
    public String segNombre;
    public String apellidoP;
    public String apellidoM;
    public String siglas;

    public Signatario() {
    }

    public Signatario(long idSignatario, String primNombre, String segNombre, String apellidoP, String apellidoM,
            String siglas) {
        this.idSignatario = idSignatario;
        this.primNombre = primNombre;
        this.segNombre = segNombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
    }

    @Override
    public String toString() {
        return "[" + siglas + "]" + " " + primNombre + " " + segNombre + " " + apellidoP + " " + apellidoM;
    }
}

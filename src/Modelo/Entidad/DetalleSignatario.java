package Modelo.Entidad;

public class DetalleSignatario {
    public long idDetalle;
    public long idSignatario;
    public long idPrueba;

    public DetalleSignatario(){
        this.idSignatario = -1;
        this.idDetalle = -1;
        this.idPrueba = -1;
    }

    public DetalleSignatario(long idDetalle, long idSignatario, long idPrueba){
        this.idSignatario = idSignatario;
        this.idDetalle = idDetalle;
        this.idPrueba = idPrueba;
    }
}

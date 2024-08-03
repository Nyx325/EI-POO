package Modelo.Entidad;

public class Norma {
    public long idNorma;
    public String norma;
    public String unidades;
    public long tipoVentana;

    public Norma(){}

    public Norma(long idNorma, String norma, String unidades, long tipoVentana) {
        this.idNorma = idNorma;
        this.norma = norma;
        this.unidades = unidades;
        this.tipoVentana = tipoVentana;
    }

    @Override
    public String toString(){
        return idNorma + " " + norma + " " + unidades;
    }
}
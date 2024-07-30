package Modelo.Entidad;

public class Sitio {
    public long idLugar;
    public String clave;
    public String nombre;
    public String cuenca;
    public String cAcuifero;
    public String acuifero;
    public String organismo;
    public String dirLocal;
    public String edo;
    public String municipio;
    public String cAgua;
    public String tipoC;
    public String subtipoC;
    public String latitud;
    public String longitud;
    public String uso;
    public String lugarT;
    public long idCliente;

    public Sitio(){}

    public Sitio(long idLugar, String clave, String nombre, String cuenca, String cAcuifero, String acuifero,
            String organismo, String dirLocal, String edo, String municipio, String cAgua, String tipoC,
            String subtipoC, String latitud, String longitud, String uso, String lugarT, long idCliente) {
        this.idLugar = idLugar;
        this.clave = clave;
        this.nombre = nombre;
        this.cuenca = cuenca;
        this.cAcuifero = cAcuifero;
        this.acuifero = acuifero;
        this.organismo = organismo;
        this.dirLocal = dirLocal;
        this.edo = edo;
        this.municipio = municipio;
        this.cAgua = cAgua;
        this.tipoC = tipoC;
        this.subtipoC = subtipoC;
        this.latitud = latitud;
        this.longitud = longitud;
        this.uso = uso;
        this.lugarT = lugarT;
        this.idCliente = idCliente;
    }

    public String toString() {
        return clave + " " + nombre + " " + latitud + " " + longitud + " " + municipio + " " + edo;
    }
}

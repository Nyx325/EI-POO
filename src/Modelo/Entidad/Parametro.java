package Modelo.Entidad;

public class Parametro {
    public long idParametro;
    public String nombre;

    public Parametro() {
        this.idParametro = -1;
        this.nombre = "";
    }

    public Parametro(long idParametro, String nombre) {
        this.idParametro = idParametro;
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parametro == false) return false;
        Parametro o = (Parametro)obj;
        
        return  idParametro == o.idParametro &&
                nombre.equals(o.nombre);
    }
}

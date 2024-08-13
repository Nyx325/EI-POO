package Modelo.Entidad;

public class Prueba {
    public long idPrueba;
    public String nombre;
    public long idParametro;

    public Prueba() {
    }

    public Prueba(long idPrueba, String nombre, long idParametro) {
        this.idPrueba = idPrueba;
        this.nombre = nombre;
        this.idParametro = idParametro;
    }
    
    @Override
    public String toString(){
        return idPrueba + " " + nombre + " " + idParametro;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Prueba == false) 
            return false;

        Prueba p = (Prueba)obj;

        return  idPrueba == p.idPrueba &&
                nombre.equals(p.nombre) &&
                idParametro == p.idParametro;
    }
}

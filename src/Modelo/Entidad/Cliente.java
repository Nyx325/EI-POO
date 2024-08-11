package Modelo.Entidad;

public class Cliente {
    public long idCliente;
    public String nombre;

    public Cliente(){
        this.idCliente = -1;
        this.nombre = "";
    }

    public Cliente(long idCliente, String nombre){
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Cliente)) 
            return false;

        Cliente o = (Cliente) obj;
        return  idCliente == o.idCliente &&
                nombre.equals(o.nombre);
    }
}

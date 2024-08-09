package Controlador;

import java.util.List;

import Modelo.Entidad.Norma;
import Modelo.Repositorio.RepositorioNorma;

public class ControladorNormas extends RepositorioNorma {
    public ControladorNormas(){
        super();
    }

    public void isValid(Norma n) throws Exception {
        String msg = "";
        if(n.norma.equals("")) msg += "El nombre de la norma no puede estar vacío, ";
        if(n.unidades.equals("")) msg += "Las unidades no pueden estar vacías, ";
        if(n.tipoVentana < 1 || n.tipoVentana > 2) msg += "Tipo de ventana no válida";

        Norma norma = super.getNormaByNombre(n.norma);
        if(norma != null && norma.idNorma != n.idNorma) 
            msg += "Ya existe una norma con ese nombre";
        

        if(msg.length() != 0) throw new Exception(msg);
    }

    public void add(Norma n) throws Exception {
        isValid(n);
        super.addAI(n);
    }

    public void modify(Norma n) throws Exception {
        isValid(n);
        super.modify(n);
    }
}

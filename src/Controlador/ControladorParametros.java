package Controlador;

import Modelo.Entidad.Parametro;
import Modelo.Repositorio.RepositorioParametro;

public class ControladorParametros extends RepositorioParametro {
    
    public ControladorParametros(){
        super();
    }
    
    public void isValid(Parametro p) throws Exception {
        String msg = "";
        if(p.nombre.equals("")) msg += "El nombre no puede estar vacío";
        Parametro p2 = getByName(p.nombre);
        if(p2 != null && p2.idParametro != p.idParametro) msg += "El nombre ya está en uso";
        
        if(msg.length() != 0) throw new Exception(msg);
    }
    
    public void add(Parametro p) throws Exception {
        isValid(p);
        super.addAI(p);
    }
    
    public void modify(Parametro p) throws Exception {
        isValid(p);
        super.modify(p);
    }
}

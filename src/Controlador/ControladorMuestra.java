package Controlador;

import Modelo.Entidad.Muestra;
import Modelo.Repositorio.RepositorioMuestra;

public class ControladorMuestra extends RepositorioMuestra {
    public ControladorMuestra(){
        super();
    }
    
    public void isValid(Muestra m) throws Exception {
        String msg = "";
        if(m.proyecto.equals("")) 
           msg+="El nombre del proyecto no puede estar vacío\n";
        
        if(m.fMuestreo == null)
            msg+="La fecha de muestreo no puede estar vacía\n";
        
        if(m.hMuestreo == null)
            msg+="La hora de muestreo no puede estar vacía\n";
        
        if(m.fRecepcion == null)
            msg+="La fecha de recepción no puede estar vacía\n";
        
        if(m.numControl.equals(""))
            msg+="El numero de control no puede estar vacío\n";
        
        if(msg.length() != 0)
            throw new Exception(msg);
    }
    
    public void add(Muestra m) throws Exception{
        Muestra m2 = super.searchByNumControl(m.numControl);
        if(m2 != null)
            throw new Exception("Ya existe una muestra con ese número de control");
        isValid(m);
        super.add(m);
    }
    
    public void modify(Muestra m) throws Exception {
        isValid(m);
        super.update(m);
    }
}

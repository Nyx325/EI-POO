package Controlador;

import java.util.List;

import Modelo.Entidad.Sitio;
import Modelo.Repositorio.RepositorioSitio;

public class ControladorSitios extends RepositorioSitio {
    public ControladorSitios(){
        super();
    }

    public void isValid(Sitio s) throws Exception {
        String msg = "";
        if(s.clave.equals("")) msg += "La clave no puede estar vacía, ";
        if(s.longitud.equals("")) msg += "La longitud es obligatoria, ";
        if(s.latitud.equals("")) msg += "La latitud es obligatoria, ";

        List<Sitio> clavesiguales = this.searchByClave(s.clave);
        for (Sitio sitio : clavesiguales) {
            if(sitio.idLugar != s.idLugar){
                msg+="Ya existe otro sitio con esta clave";
                break;
            }
        }

        
    }

    public void add(Sitio s) throws Exception {
        isValid(s);
        super.addAI(s);
    }

    public void modify(Sitio s) throws Exception {
        isValid(s);
        // TODO actualizar las muestras y clientes
        // o aplicar modificacion apropiadamente
        super.remove(s);
        super.add(s);
    }
}

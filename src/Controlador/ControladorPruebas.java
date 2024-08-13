package Controlador;

import Modelo.Repositorio.RepositorioPrueba;
import Modelo.Entidad.Prueba;

public class ControladorPruebas extends RepositorioPrueba {
    public void isValid(Prueba p) throws Exception{
        String msg = "";
        if(getByNombre(p.nombre) != null)
            msg+="Ya existe un parámetro con este nombre";
        if(p.nombre.equals(""))
            msg+="El nombre no puede estar vacío";

        if(msg.length() != 0)
            throw new Exception(msg);
    }

    public void add(Prueba p) throws Exception {
        isValid(p);
        super.addAI(p);
    }

    public void modify(Prueba p) throws Exception {
        Prueba original = this.getById(p.idPrueba);
        if(p.equals(original)) return;
        super.update(p);
    }
}

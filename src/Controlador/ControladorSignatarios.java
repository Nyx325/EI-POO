package Controlador;

import java.sql.SQLException;
import java.util.List;

import Modelo.Entidad.DetalleSignatario;
import Modelo.Entidad.Signatario;
import Modelo.Repositorio.RepositorioPrueba;
import Modelo.Repositorio.RepositorioSignatarios;

public class ControladorSignatarios extends RepositorioSignatarios{
    RepositorioPrueba repoPrueb = new RepositorioPrueba(); 
    public ControladorSignatarios(){
        super();
    }

    public void isValid(Signatario s) throws Exception {
        String err = "";
        if(s.primNombre.equals("")) err = err + "El primer nombre es obligatorio, ";
        if(s.apellidoP.equals("")) err = err + "El apellido paterno es obligatorio, ";
        if(s.sueldo <= 0) err = err + "El sueldo debe ser un real positivo, ";
        if(s.bono < 0) err = err + "El bono debe ser un real positivo o 0, ";
        if(s.fIngreso == null) err = err + "La fecha de ingreso no puede estar vacía, ";
        if(s.fNacimiento == null) err = err + "La fecha de nacimiento no puede estar vacía, ";
        if(super.usuarioEnUso(s)) err = err + "El usuario ya está en uso, ";
        if(s.posicion.equals("")) err = err + "La posición no puede estar vacía, ";

        if(err.length() != 0) throw new Exception(err);
    }

    @Override
    public void add(Signatario s) throws Exception{
        isValid(s);
        super.addAI(s);
    }

    public void modify(Signatario s) throws Exception {
        Signatario original = searchBy(s.idSignatario);
        if(original == null) throw new SQLException("El signatario no existe");
        if(original.equalsExceptId(s)) return;
        isValid(s);
        List<DetalleSignatario> pruebas = repoPrueb.searchDetalleBy(s.idSignatario);
        super.remove(original);
        super.add(s);
        for(DetalleSignatario d : pruebas) {
            repoPrueb.updatePruebasModify(d.idDetalle, s.idSignatario);
        }
    }
}

package Controlador;

import java.sql.SQLException;

import Modelo.Entidad.Cliente;
import Modelo.Repositorio.RepositorioCliente;

public class ControladorClientes extends RepositorioCliente {
    private void isValid(Cliente c) throws Exception {
        if(c.nombre.equals("")) 
            throw new Exception("El nombre no puede estar vacío");

        if(getByName(c.nombre) != null)
            throw new Exception("Ya existe un cliente con ese nombre");
    }

    public void add(Cliente c) throws Exception {
        isValid(c);
        super.addAI(c);
    }

    public void modify(Cliente c) throws Exception {
        Cliente original = super.getById(c.idCliente);
        if(original == null) 
            throw new SQLException("No existe el cliente");
        if(original.equals(c)) return;
        isValid(c);
        super.modify(c);
    }
}

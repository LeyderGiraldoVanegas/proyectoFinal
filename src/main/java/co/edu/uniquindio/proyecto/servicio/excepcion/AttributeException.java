package co.edu.uniquindio.proyecto.servicio.excepcion;

import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;

public class AttributeException  extends  Exception{
    private UsuarioRepo usuarioRepo;
    public AttributeException(String mensaje) {
        super(mensaje);
    }
}

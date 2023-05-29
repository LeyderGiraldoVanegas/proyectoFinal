package co.edu.uniquindio.proyecto.servicio.interfaces;

import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {

    /**
     * Metodo obtener crear Usuario
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    int crearUsuario(UsuarioDTO usuarioDTO)  throws Exception;

    /**
     * Metodo obtener actualizar Usuario
     * @param codigoUsuario
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception;

    /**
     * Metodo obtener eliminiar Usuario
     * @param codigoUsuario
     * @return
     * @throws Exception
     */
    int eliminiarUsuario(int codigoUsuario) throws Exception;

    /**
     * Metodo obtener  UsuarioGetDTO obtenerUsuario
     *
     * @param codigoUsuario
     * @return
     * @throws Exception
     */
    UsuarioGetDTO obtenerUsuario(int codigoUsuario) throws Exception;

    List<Categoria> obtenerListadoCategoria(int codigoCompra) throws Exception;

    UsuarioGetDTO obtenerUsuarioGetDTO(int codigoUsuario) throws Exception;

    Usuario obtener(int codigoVendedor) throws Exception;

    Usuario obtenerCupon(int codigoCupon)throws Exception;

    Usuario obtenerCompra(int codigoCompra)throws Exception;

    //Usuario obtenerUsuarioComentario(int codigoUsuarioComentario);

    Usuario obtenerComentario(int codigoUsuario)throws Exception;

    UsuarioGetDTO obtenerUsuarioInt(int codigoUsuario) throws Exception;

    List<Usuario> listarUsuarios ();

     UsuarioGetDTO convertir(Usuario usuario);

    UsuarioGetDTO cambiarContrasena(int codigoUsuario, String pass ) throws Exception;


    UsuarioGetDTO obtenerUsuarioEmail(String gmail);
}

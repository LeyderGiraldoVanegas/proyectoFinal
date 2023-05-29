package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {


    /**
     * Declaración de atributos
     */
    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;

    private final ComentarioRepo comentarioRepo;
    private final CompraRepo compraRepo;

    private final CuponRepo cuponRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    @Override
    public int crearUsuario(UsuarioDTO usuarioDTO) throws Exception{

        Usuario buscado = usuarioRepo.buscarUsuario(usuarioDTO.getEmail());

        if(buscado!=null){
            throw new Exception("El correo "+usuarioDTO.getEmail()+" ya está en uso");
        }

        Usuario usuario = convertir(usuarioDTO);
        return usuarioRepo.save( usuario ).getCodigo();
    }

    /**
     *
     * @param codigoUsuario
     * @param usuarioDTO
     * @return
     * @throws Exception
     */
    @Override
    public UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception{

        /**
         * TODO Validar que el correo no se repita
         */

        validarExiste(codigoUsuario);

        Usuario usuario = convertir(usuarioDTO);
        usuario.setCodigo(codigoUsuario);

        return convertir( usuarioRepo.save(usuario) );
    }

    /**
     *
     * @param codigoUsuario
     * @return
     * @throws Exception
     */
    @Override
    public int eliminiarUsuario(int codigoUsuario) throws Exception{
        validarExiste(codigoUsuario);
        Usuario usuario = obtener(codigoUsuario);
        usuario.setEstado("Eliminado");
        usuarioRepo.save(usuario);
        return codigoUsuario;
    }

    @Override
    public UsuarioGetDTO obtenerUsuario(int codigoUsuario) throws Exception {
        return convertir( obtener(codigoUsuario) );
    }

    /**
     * @param codigoUsuario
     * @return
     * @throws Exception
     */
    @Override
    public UsuarioGetDTO obtenerUsuarioGetDTO(int codigoUsuario) throws Exception{
        return convertir( obtener(codigoUsuario) );
    }

    /**
     *
     * @param codigoUsuario
     * @return
     * @throws Exception
     */
    public Usuario obtener(int codigoUsuario) throws Exception{
        Optional<Usuario> usuario = usuarioRepo.findById(codigoUsuario);

        if(usuario.isEmpty() ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

        return usuario.get();
    }

    @Override
    public Usuario obtenerCupon(int codigoCupon) throws Exception {

        Optional<Cupon> cupon = cuponRepo.findById(codigoCupon);

        if (cupon.isEmpty()){
            throw new Exception("El código "+codigoCupon+" no está asociado a ningún cupon");
        }

        return cupon.get().getUsuarioCupon();
    }

    @Override
    public Usuario obtenerCompra(int codigoCompra) throws Exception {
        Optional<Compra> compra= compraRepo.findById(codigoCompra);

        if (compra.isEmpty()){
            throw new Exception("El código "+codigoCompra+" no está asociado a ningún compra");
        }
        return compra.get().getUsuarioCompra();
    }


    @Override
    public Usuario obtenerComentario(int codigoComentario) throws Exception {
        Optional<Comentario> comentario = comentarioRepo.findById(codigoComentario);

        if (comentario.isEmpty()){
            throw new Exception("El código "+codigoComentario+" no está asociado a ningún comentario");
        }

        return comentario.get().getUsuarioComentario();
    }

    @Override
    public List<Categoria> obtenerListadoCategoria(int codigoCompra) throws Exception {
        return null;
    }

    @Override
    public UsuarioGetDTO obtenerUsuarioInt(int codigoUsuario) throws Exception {
        return convertir( obtener(codigoUsuario) );
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public List<UsuarioGetDTO> listarClientes(){
        return convertirListaUsuariosGetDTO( usuarioRepo.findAll() );
    }

    private List<UsuarioGetDTO> convertirListaUsuariosGetDTO(List<Usuario> lista) {

        List<UsuarioGetDTO> respuesta = new ArrayList<>();
        for(Usuario u : lista){
            respuesta.add( convertir(u) );
        }
        return respuesta;
    }

    private UsuarioDTO convertirDTO(Usuario usuario){

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getDireccion(),
                usuario.getTelefono()
        );

        return usuarioDTO;
    }



    /**
     *
     * @param codigoUsuario
     * @throws Exception
     */
    public void validarExiste(int codigoUsuario) throws Exception{
        boolean existe = usuarioRepo.existsById(codigoUsuario);

        if( !existe ){
            throw new Exception("El código "+codigoUsuario+" no está asociado a ningún usuario");
        }

    }

    /**
     *
     * @param usuario
     * @return
     */
    @Override
    public UsuarioGetDTO convertir(Usuario usuario){

        UsuarioGetDTO usuarioDTO = new UsuarioGetDTO(
                usuario.getCodigo(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getPassword(),
                usuario.getEstado());

        return usuarioDTO;
    }

    @Override
    public UsuarioGetDTO cambiarContrasena(int codigoUsuario, String pass ) throws Exception {
        validarExiste(codigoUsuario);

        Usuario usuario = obtener(codigoUsuario);
        usuario.setPassword(pass);
        usuario.setNombre(usuario.getNombre());
        usuario.setEmail(usuario.getEmail());
        usuario.setDireccion(usuario.getDireccion());
        usuario.setTelefono(usuario.getTelefono());

        return convertir( usuarioRepo.save(usuario) );
    }

    @Override
    public UsuarioGetDTO obtenerUsuarioEmail(String gmail) {
        Usuario usuario = usuarioRepo.buscarUsuario(gmail);
        return convertir(usuario);
    }

    /**
     *
     * @param usuarioDTO
     * @return
     */
    private Usuario convertir(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario( );
        usuario.setNombre( usuarioDTO.getNombre() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())   );
        usuario.setTelefono( usuarioDTO.getTelefono() );
        usuario.setDireccion( usuarioDTO.getDireccion() );


        return usuario;
    }

}

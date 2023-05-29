package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.dto.CategoriaContador;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {
    private final ProductoRepo productoRepo;
    private final UsuarioServicio usuarioServicio;
    private final UsuarioRepo usuarioRepo;

    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {

        /**
         * validar usuario
         * validar code producto
         * validar estado producto = siempre comience inactivo
         * validar que las unidades sean mayor a 1
         * validar datos obligatorios
         */

        Usuario codigoVendedor = usuarioServicio.obtener(productoDTO.getCodigoVendedor());


    if (codigoVendedor != null){

        Producto producto = convertirDTOaProducto(productoDTO);

        return productoRepo.save( producto ).getCodigo();
    }else {
        throw new Exception("El Usuario  "+productoDTO.getCodigoVendedor()+" ya está en uso");
    }
    }

    private Producto convertirDTOaProducto(ProductoDTO productoDTO) throws Exception {

        Usuario codigoVendedor = usuarioServicio.obtener(productoDTO.getCodigoVendedor());
         Producto producto = new Producto();
       //  producto.setCodigo(10);
         producto.setNombre( productoDTO.getNombre() );
         producto.setDescripcion( productoDTO.getDescripcion() );
         producto.setUnidades( productoDTO.getUnidades() );
         producto.setPrecio( productoDTO.getPrecio() );
         producto.setUsuario( codigoVendedor );
         producto.setImagenesList( productoDTO.getImagenList() );
         producto.setCategoriaList( productoDTO.getCategoriaList() );
         producto.setActivo( "INACTIVO" );
         producto.setFechaCreacion( LocalDateTime.now() );
         producto.setFechaLimite(LocalDateTime.now());
         //producto.setFechaLimite( LocalDateTime.now().plusDays(60) );
         producto.setModerador(null);

         return  producto;
    }


    @Override
    public ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception{

        /**
         * TODO Validar que el correo no se repita
         */

        validarExiste(codigoProducto);

        Producto producto = convertirDTOaProducto(productoDTO);
        producto.setCodigo(codigoProducto);

        return convertir( productoRepo.save(producto) );


    }

    @Override
    public int actualizarUnidades(int codigoProducto, int unidades) throws Exception {
        return 0;
    }

    public void validarExiste(int codigoProducto) throws Exception {

        boolean existe = productoRepo.existsById(codigoProducto);

        if( !existe ){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún usuario");
        }

    }

    @Override
    public int eliminarProducto(int codigoProducto) throws Exception{

        validarExiste(codigoProducto);
        Producto producto =  obtener(codigoProducto);
        producto.setActivo("Eliminado");
        productoRepo.save(producto);
        return codigoProducto;

    }



    @Override
    public ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception {

        return convertir( obtener(codigoProducto) );
    }

    @Override
    public Producto obtener(int codigoProducto) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        System.out.println(codigoProducto);
        if( producto.isEmpty() ){
            throw new Exception("El código "+codigoProducto+" no está asociado a ningún usuario");
        }

        return producto.get();
    }

    @Override
    public int addProductoFavorito(int codigoProducto, int codigoVendedor) throws Exception {
        Producto producto = obtener(codigoProducto);
        Usuario usuario = producto.getUsuario();

        List<Usuario> usuarioFavoritoList = producto.getUsuarioFavoritoList();
        List<Producto> productoList = usuario.getProductoList();

        if(productoList == null){
            productoList = new ArrayList<>();
            productoList.add(producto);
            usuario.setProductoFavoritoList( productoList );
        }else {
            productoList.add(producto);
            usuario.setProductoFavoritoList( productoList );
        }
        if (usuarioFavoritoList == null ){
            usuarioFavoritoList = new ArrayList<>();
            usuarioFavoritoList.add(usuario);
            producto.setFavoritosList(usuarioFavoritoList);
           // usuario.setProductoFavoritoList(  );
        }else {
            usuarioFavoritoList.add(usuario);
            producto.setFavoritosList(usuarioFavoritoList);
        }

        usuarioRepo.save(usuario);

        //usuario.setProductoFavoritoList((List<Producto>) producto);
       // producto.setFavoritosList();

        return usuarioFavoritoList.size();
    }

    @Override
    public List<ProductoGetDTO> listarProductos() {
        List<Producto> lista = productoRepo.listarProductos();
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosEstado(String estado) {
        List<Producto> lista = productoRepo.listarProductosEstado(estado);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> buscarProductonombre(String nombre) throws Exception {
        List<Producto> lista = productoRepo.obtenerProductoNombre(nombre);
        //Optional<Producto> producto = productoRepo.obtenerProductoNombre(nombre);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;

    }

    @Override
    public List<ProductoGetDTO> listarProductosTodos() {
        List<Producto> lista = productoRepo.listarProductosTodos();
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }


    @Override
    public List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario) {

        List<Producto> lista = productoRepo.listarProductosUsuario(codigoUsuario);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosCompradoUsuarioSinRepetir(int codigoUsuario) {
        return null;
    }


    @Override
    public List<ProductoGetDTO> obtenerProductoCaroBarato(Categoria categoria) throws Exception {
        List<Producto> listabarato = productoRepo.obtenerProductoBarato(categoria);
        List<Producto> listacaro = productoRepo.obtenerProductoCaro(categoria);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        if (listacaro != null && listabarato != null){
            respuesta.add(convertir(listabarato.get(0)));
            respuesta.add(convertir(listacaro.get(0)));
        }else{
            throw new Exception("los valores estan nill ");
        }


        return respuesta;
    }

    @Override
    public List<CategoriaContador> contarProductosCadaCategoria( ) throws Exception {

        List<Object[]> listaNumeroCategoria = productoRepo.contarProductosCadaCategoria();
        List<CategoriaContador> listaR = new ArrayList<>();

        for(Object[] ob : listaNumeroCategoria){
            listaR.add( new CategoriaContador( (Categoria) ob[0], (Integer) ob[1] )  );
        }

        return listaR;
    }

    @Override
    public ProductoGetDTO convertir(Producto producto){

        ProductoGetDTO productoGetDTO = new ProductoGetDTO(
                producto.getCodigo(),
                producto.getActivo(),
                producto.getFechaLimite(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getUnidades(),
                producto.getPrecio(),
                producto.getUsuario().getCodigo(),
                producto.getImagenesList(),
                producto.getCategoriaList()
        );

        return productoGetDTO;
    }

    @Override
    public List<ProductoGetDTO> listarProductosCategoria(Categoria categoria) {

        List<Producto> lista = productoRepo.listarProductosCategoria(categoria);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;

    }

    @Override
    public List<ProductoGetDTO> listarProductosPorEstado(Estado estado) {
        return null;
    }

    @Override
    public List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario)  {

        List<Producto> lista = productoRepo.listarProductoFavoritos(codigoUsuario);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista)
        {
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosNombre(String nombre) {

        List<Producto> lista = productoRepo.listarProductosUsuario(nombre);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) {

        List<Producto> lista = productoRepo.listarProductosPrecio(precioMinimo,precioMaximo);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for(Producto p : lista){
            respuesta.add( convertir(p) );
        }

        return respuesta;

    }


    public void guardarRepo(Producto producto) throws Exception {
        productoRepo.save( producto );
    }


    public void crearFavorito(int codigoProducto, int codigoUsuario) throws Exception{

        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        Producto producto = obtener(codigoProducto);

        //Validar que el producto no esté en la lista de favoritos, que no esté vencido y que esté activo

        usuario.getProductoFavoritoList().add(producto);
        usuarioRepo.save(usuario);
    }

    public void eliminarFavorito(int codigoProducto, int codigoUsuario) throws Exception{

        Usuario usuario = usuarioServicio.obtener(codigoUsuario);
        Producto producto = obtener(codigoProducto);

        usuario.getProductoFavoritoList().remove(producto);
        usuarioRepo.save(usuario);
    }
}

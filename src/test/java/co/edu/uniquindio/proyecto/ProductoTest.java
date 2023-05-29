package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ProductoTest {


    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Test
   // @Sql("classpath:dataset.sql")
    public void listarProductosFavoritoTest() throws Exception //No pasa el test
    {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        // imagenes.add("http://www.google.com/images/imagenasus.png");
        // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);
        int codigoProducto1 = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);
        int codigoFavorito = productoServicio.addProductoFavorito(codigoProducto,codigoVendedor);
        int codigoFavorito1 = productoServicio.addProductoFavorito(codigoProducto,codigoVendedor);

        Producto producto = productoServicio.obtener(codigoProducto);
        Assertions.assertEquals(codigoFavorito, codigoFavorito1);

        //List<ProductoGetDTO> productosFavorito = productoServicio.listarProductosFavoritos(codigoVendedor);
        //Assertions.assertEquals(1, productosFavorito.size());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void addProductoFavoritoTest() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        // imagenes.add("http://www.google.com/images/imagenasus.png");
        // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);
        int codigoFavorito = productoServicio.addProductoFavorito(codigoProducto,codigoVendedor);
        Assertions.assertNotEquals(0, codigoFavorito);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosPrecioTest() throws Exception {
        //Para obtener el Producto primero se debe crear

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        ProductoDTO productoDTO1 = new ProductoDTO(
                "Computador Asus 2",
                "Es el segundo mejor computador portatil que el dinero pueda comprar",
                1,
                6000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto1 = productoServicio.crearProducto(productoDTO1);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);

        //
        List<ProductoGetDTO> listarProductosUsuario =
                productoServicio.listarProductosPrecio(0,7000001);

        for (ProductoGetDTO p: listarProductosUsuario) {

            System.out.println(p.getDescripcion() + " Listado terminado");
            /**
             * //Comprobamos que la precio que está en la base de datos coincide con la que esperamos
             *         Assertions.assertNotEquals(0, listarProductosUsuario.get(0).getCodigo());
             *         Assertions.assertNotEquals(0, listarProductosUsuario.get(1).getCodigo());
             */

        }


    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosCategoriaTest() throws Exception {
    //Para obtener el Producto primero se debe crear

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        ProductoDTO productoDTO1 = new ProductoDTO(
                "Computador Asus 2",
                "Es el segundo mejor computador portatil que el dinero pueda comprar",
                1,
                6000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto1 = productoServicio.crearProducto(productoDTO1);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);
        List<ProductoGetDTO> list = productoServicio.listarProductosCategoria(Categoria.TECNOLOGIA);

        for (ProductoGetDTO p: list) {

            System.out.println(p.getDescripcion() + " Listado terminado");

        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosUsuarioTest() throws Exception {
        //Para obtener el Producto primero se debe crear

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        ProductoDTO productoDTO1 = new ProductoDTO(
                "Computador Asus 2",
                "Es el segundo mejor computador portatil que el dinero pueda comprar",
                1,
                6000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto1 = productoServicio.crearProducto(productoDTO1);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);

        //
        List<ProductoGetDTO> listarProductosUsuario = productoServicio.listarProductosUsuario(codigoVendedor);

        //Comprobamos que la precio que está en la base de datos coincide con la que esperamos
        Assertions.assertNotEquals(0, listarProductosUsuario.get(0).getCodigo());
        Assertions.assertNotEquals(0, listarProductosUsuario.get(1).getCodigo());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerProductoTest()throws Exception{
        //Para obtener el Producto primero se debe crear

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, codigoProducto);

        //Se llama el servicio para obtener el producto completo dado su código
        ProductoGetDTO productoGetDTO= productoServicio.obtenerProducto(codigoProducto);

        //Comprobamos que la precio que está en la base de datos coincide con la que esperamos
        Assertions.assertEquals(7000000, productoGetDTO.getPrecio());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProductoTest() throws Exception {

        //Para eliminar el Producto primero se debe crear
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "eliminar",
                "eliminar@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, codigoProducto);

        //Una vez creado, lo borramos
        int codigoBorrado = productoServicio.eliminarProducto(codigoProducto);

        //Si intentamos buscar un usuario con el codigo del usuario borrado debemos obtener una excepción
        // indicando que ya no existe
        Assertions.assertEquals(codigoBorrado,codigoProducto);


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarProductoTest() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "nuevo@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
       // imagenes.add("http://www.google.com/images/imagenasus.png");
       // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, codigoProducto);
    }

    @Test
    public void actualizarProductoTest() throws Exception  {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        List<Categoria> categoriaList = new ArrayList<>();
        categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        imagenes.add("http://www.google.com/images/imagenasus.png");
        imagenes.add("http://www.google.com/images/imagenasus_original.png");

        //Se crea el producto y se usa el código dado por el servicio de registro de usuario para asignar el vendedor
        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                7000000,
                codigoVendedor,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        //Se espera que el servicio retorne el código del nuevo producto
        Assertions.assertNotEquals(0, codigoProducto);

    //El servicio de actualizar nos retorna el producto
        ProductoGetDTO actualizarProducto = productoServicio.actualizarProducto(codigoProducto,
                new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                1,
                8000000,
                codigoVendedor,
                imagenes,
                categoriaList
                ));
        //Se comprueba que ahora el teléfono del usuario no es el que se usó cuando se creó inicialmente
        Assertions.assertNotEquals(7000000, actualizarProducto.getPrecio());

    }


}

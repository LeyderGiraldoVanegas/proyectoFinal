package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertFalse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CompraTest {

    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CompraServicio compraServicio;

    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCompraTest() throws Exception {

        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigoUsuario = usuarioServicio.crearUsuario(usuarioDTO);

        //Validamos que el usuario se cree correctamente
        //Assertions.assertNotEquals(0, codigoUsuario);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        // imagenes.add("http://www.google.com/images/imagenasus.png");
        // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                6,
                7000000,
                codigoUsuario,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        moderadorServicio.aprobarProducto(codigoProducto,1);


        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        DetalleCompra detalle = new DetalleCompra();
        Producto producto = productoServicio.obtener(codigoProducto);

        detalle.setProductoDetalleCompra(producto);
        detalle.setUnidades(5);
        detalle.setPrecioProducto(5 * producto.getPrecio());

        lista.add(detalle);

        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigoUsuario,
                12000,
                "Efectivo",
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
        Assertions.assertNotEquals(0, codigoCompra);
    }



    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCompraTest() throws Exception {

        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigoUsuario = usuarioServicio.crearUsuario(usuarioDTO);

        //Validamos que el usuario se cree correctamente
        Assertions.assertNotEquals(0, codigoUsuario);

        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        lista.add(new DetalleCompra()) ;

        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigoUsuario,
                12000,
                "juege",
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
        Assertions.assertNotEquals(0, codigoCompra);

        CompraGetDTO compraGetDTOActualizar = compraServicio.actualizarCompra( codigoCompra,new CompraDTO(
                codigoUsuario,
                13000,
                "juege",
                lista,
                listaCupon));
        //Llamamos el metodo crar compra
     //   int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
        Assertions.assertNotEquals(12000, compraGetDTOActualizar.getValorTotal());


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void  eliminiarCompraTest() throws Exception {
        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigo = usuarioServicio.crearUsuario(usuarioDTO);

        //Validamos que el usuario se cree correctamente
        Assertions.assertNotEquals(0, codigo);

        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        lista.add(new DetalleCompra()) ;

        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigo,
                12000,
                "juege",
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
        Assertions.assertNotEquals(0, codigoCompra);

        //Una vez creado, lo borramos
        int codigoBorrado = compraServicio.eliminiarCompra(codigoCompra);

        Assertions.assertEquals(codigoBorrado,codigoCompra);


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void  obtenerCompraTest() throws Exception {
        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigoUsuario = usuarioServicio.crearUsuario(usuarioDTO);

        //Validamos que el usuario se cree correctamente
        //Assertions.assertNotEquals(0, codigoUsuario);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        // imagenes.add("http://www.google.com/images/imagenasus.png");
        // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                6,
                7000000,
                codigoUsuario,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        moderadorServicio.aprobarProducto(codigoProducto,1);


        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        DetalleCompra detalle = new DetalleCompra();
        Producto producto = productoServicio.obtener(codigoProducto);

        detalle.setProductoDetalleCompra(producto);
        detalle.setUnidades(1);
        detalle.setPrecioProducto(1 * producto.getPrecio());

        lista.add(detalle);

        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigoUsuario,
                12000,
                "Efectivo",
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
        Assertions.assertNotEquals(0, codigoCompra);

        //Una vez creado, lo borramos
        Compra obtenerCompra = compraServicio.obtenerCompra(codigoCompra);

        Assertions.assertEquals(obtenerCompra.getCodigo(),codigoCompra);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasTestUsuario() throws Exception {

        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigoUsuario = usuarioServicio.crearUsuario(usuarioDTO);

        //Validamos que el usuario se cree correctamente
        //Assertions.assertNotEquals(0, codigoUsuario);

        List<Categoria> categoriaList = new ArrayList<>();
        //categoriaList.add(Categoria.TECNOLOGIA);
        //Se crea la colección de imágenes para el producto.
        List<String> imagenes = new ArrayList<String>();
        // imagenes.add("http://www.google.com/images/imagenasus.png");
        // imagenes.add("http://www.google.com/images/imagenasus_original.png");

        ProductoDTO productoDTO = new ProductoDTO(
                "Computador Asus 1",
                "Es el mejor computador portatil que el dinero pueda comprar",
                6,
                7000000,
                codigoUsuario,
                imagenes,
                categoriaList);
        //Se llama el servicio para crear el producto
        int codigoProducto = productoServicio.crearProducto(productoDTO);

        moderadorServicio.aprobarProducto(codigoProducto,1);


        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        DetalleCompra detalle = new DetalleCompra();
        Producto producto = productoServicio.obtener(codigoProducto);

        detalle.setProductoDetalleCompra(producto);
        detalle.setUnidades(1);
        detalle.setPrecioProducto(1 * producto.getPrecio());

        lista.add(detalle);

        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigoUsuario,
                12000,
                "Efectivo",
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);
        int codigoCompra1 = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
       // Assertions.assertNotEquals(0, codigoCompra);
       // Assertions.assertNotEquals(0, codigoCompra1);

        List<CompraGetDTO> listaCompra =  compraServicio.listarComprasUsuario(codigoUsuario);


    }

}

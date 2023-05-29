package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.CompraDTO;
import co.edu.uniquindio.proyecto.dto.DetalleCompraGetDTO;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Cupon;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.CompraServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;
    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosUsuario() throws Exception {

        List<DetalleCompraGetDTO> list = detalleCompraServicio.listarProductosUsuario(1);

        System.out.println(list);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearDetalleCompraTest() throws Exception {


        //Primero debemos crear el usuario
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "343");
        int codigoVendedor = usuarioServicio.crearUsuario(usuarioDTO);

        //segundo creamos un producto
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


        //tercero creamos Compra
        //Creamos el detalle producto en este caso es null pero no deberia
        List<DetalleCompra> lista = new ArrayList<>();
        DetalleCompra detalleCompra= new DetalleCompra();
        detalleCompra.setProductoDetalleCompra(productoServicio.obtener(codigoProducto));
        detalleCompra.setUnidades(7);
        float valor = 7 * productoDTO.getPrecio();
        detalleCompra.setPrecioProducto(valor);

        //le añadimos detalle compra
        lista.add(detalleCompra) ;



        //Creamos la lista de cupones en este caso es null pero no deberia
        List<Cupon> listaCupon = new ArrayList<>();
        listaCupon.add(new Cupon());


    int codigoDetalleCompra = 0;

        //Creamos la compra DTO
        CompraDTO compraDTO = new CompraDTO(
                codigoVendedor,
                12000,
                "juege",
                //codigoDetalleCompra = detalleCompraServicio.ob lista,
                lista,
                listaCupon);
        //Llamamos el metodo crar compra
        int codigoCompra = compraServicio.crearCompra(compraDTO);

        //validamos si se creo
       // Assertions.assertEquals(detalleCompra, compraDTO.getDetalleCompraList().get(0));
        Assertions.assertNotEquals(0,detalleCompra.getCodigo());

       // List<DetalleCompra> listaDetalle = compraServicio.obtenerCompra(codigoCompra).getDetalleCompraList();
       // int codigoDetalleCompra = detalleCompraServicio.obtenerDetallesCodigo(listaDetalle).get(0);

        //Assertions.assertNotEquals(0, codigoDetalleCompra);


    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDetalleCompraTest()throws Exception{

    }

    }

package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.servicio.interfaces.ModeradorServicio;
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
public class ModeradorTest {
    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private  UsuarioServicio usuarioServicio;
    @Autowired
    private ProductoServicio productoServicio;
    @Test
    //@Sql("classpath:dataset.sql")
    public void aprobarProducto() throws Exception {

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                "Pepito 1",
                "pepe1@email.com",
                "1234",
                "Calle 123",
                "341");

        //El servicio del usuario nos retorna el código con el que quedó en la base de datos
        int codigoVendedor = 7;//usuarioServicio.crearUsuario(usuarioDTO);

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
        Producto producto = productoServicio.obtener(codigoProducto);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);

        int codigoModerador = moderadorServicio.obtenerModerador(1).getCodigo();
        moderadorServicio.aprobarProducto(codigoProducto,codigoModerador);
        Assertions.assertNotEquals("INACTIVO", producto.getActivo());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void rechazarProducto() throws Exception {
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
        Producto producto = productoServicio.obtener(codigoProducto);

        //Se espera que el servicio retorne el código del nuevo producto
        //Assertions.assertNotEquals(0, codigoProducto);

        moderadorServicio.rechazarProducto(codigoProducto);
        Assertions.assertNotEquals("INACTIVO", producto.getActivo());
    }
}

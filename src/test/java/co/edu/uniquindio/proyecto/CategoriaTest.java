package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.CategoriaContador;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CategoriaTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Test
    @Sql("classpath:dataset.sql")
    public void listarProductosCategoriaTest() throws Exception {
        List<CategoriaContador> categorias = productoServicio.contarProductosCadaCategoria();
        List<Categoria> respuesta = new ArrayList<>();

        for(CategoriaContador c : categorias){
            respuesta.add( c.getCategoria());
        }
    }
}

package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
//@Controller
@RequestMapping("/api/producto")
@AllArgsConstructor
public class ProductoControlador {
    private final ProductoServicio productoServicio;

    @PostMapping("/crear")
    ResponseEntity<MensajeDTO>  crearProducto(@RequestBody ProductoDTO productoDTO) throws Exception{

       // return productoServicio.crearProducto(productoDTO) ;
        return ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK,
                false,"Producto creado exitosamente! Código: " +productoServicio.crearProducto(productoDTO) ) );
    }

    @PutMapping("/actualizarProductos/{codigoProducto}")
    ProductoGetDTO actualizarProducto(@PathVariable int codigoProducto,@RequestBody ProductoDTO productoDTO) throws Exception{
        return productoServicio.actualizarProducto(codigoProducto,productoDTO);
    }
/*
    @PutMapping("/actualizarUnidades/{codigoProducto}")
    int actualizarUnidades(@PathVariable int codigoProducto,@PathVariable int unidades) throws Exception{
        return productoServicio.actualizarUnidades(codigoProducto,unidades);
    }*/


    @GetMapping("/obtenerProducto/{codigoProducto}")
    public  ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigoProducto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.obtenerProducto(codigoProducto)));
    }

    @DeleteMapping("/eliminarProducto/{codigoProducto}")
    public  ResponseEntity<MensajeDTO> eliminarProducto(@PathVariable int codigoProducto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,"El usuario con el codigo: "+
                productoServicio.eliminarProducto(codigoProducto) + " se a borrado exitosamente "));
    }

    @GetMapping("/listar/categoria/{categoria}")
    public  ResponseEntity<MensajeDTO> listarProducto(@PathVariable Categoria categoria){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosCategoria(categoria)));
    }

    @GetMapping("/listar/precio/{min}-{max}")
    public  ResponseEntity<MensajeDTO> listarProductoPrecio(@PathVariable float max,@PathVariable float min){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosPrecio(min,max)));
    }

    @GetMapping("/listar/favorito/{idUsuario}")
    public  ResponseEntity<MensajeDTO> listarProductoFavoritos(@PathVariable int idUsuario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosFavoritos(idUsuario)));
    }

    @PutMapping("/listar/favorito/{idUsuario}/{idProducto}")
    public  ResponseEntity<MensajeDTO> anadirlistarProductoFavoritos(@PathVariable int idUsuario,@PathVariable int idProducto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.addProductoFavorito(idProducto,idUsuario)));
    }

    @GetMapping("/listar/productos")
    public  ResponseEntity<MensajeDTO> listarProducto() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductos() ));
    }
    @GetMapping("/listar/productosTodos")
    public  ResponseEntity<MensajeDTO> listarProductosTodos() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosTodos() ));
    }

    @GetMapping("/listar/productos/{estado}")
    public  ResponseEntity<MensajeDTO> listarProductoEstado(@PathVariable String estado) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosEstado(estado) ));
    }
    @GetMapping("/listar/buscarProcudo/{nombre}")
    public  ResponseEntity<MensajeDTO> buscarProductoNombre(@PathVariable String nombre) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.buscarProductonombre(nombre) ));
    }

    @GetMapping("/listar/ProcudoCaroyBarato/{categoria}")
    public  ResponseEntity<MensajeDTO> buscarProductoNombre(@PathVariable Categoria categoria) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.obtenerProductoCaroBarato(categoria) ));
    }

    @GetMapping("/listar/obtenerCantidadCategorias")
    public  ResponseEntity<MensajeDTO> buscarProductoNombre(  ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.contarProductosCadaCategoria() ) );
    }


    @GetMapping("/listar/MisProductos/{idUsuario}")
    public  ResponseEntity<MensajeDTO> listarPRoductosUsuario(@PathVariable int idUsuario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                productoServicio.listarProductosUsuario(idUsuario)));
    }

}


package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CompraDTO;
import co.edu.uniquindio.proyecto.dto.CompraGetDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.servicio.interfaces.CompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compra")
@AllArgsConstructor
public class CompraControlador {

    private final CompraServicio compraServicio;


    @PostMapping("/crear")
    ResponseEntity<MensajeDTO> crearCompra(@RequestBody CompraDTO compraDTO) throws Exception{
        //return compraServicio.crearCompra(compraDTO);
        return  ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK,
                false,"Compra creado exitosamente! Código: " +compraServicio.crearCompra(compraDTO) ) );
    }


    @GetMapping("/obtenerCompra/{codigoCompra}")
    ResponseEntity<MensajeDTO>  obtenerCompra(@PathVariable int codigoCompra) throws Exception{
       // return compraServicio.obtenerCompra(codigoCompra);

        return  ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK,
                false,"Código de la compra: " +compraServicio.obtenerCompra(codigoCompra).getCodigo()) );
    }

    @GetMapping("/listar/compra/")
    ResponseEntity<MensajeDTO>  listarCompra(  ) throws Exception{
        // return compraServicio.obtenerCompra(codigoCompra);

        return  ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,
                false,compraServicio.listarCompras() ) );


    }

    @GetMapping("/listar/compraSinRepetir/{codigoUsuario}")
    ResponseEntity<MensajeDTO> buscarProductoNombre(@PathVariable int  codigoUsuario ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                compraServicio.listarComprasUsuarioSinRepetir(codigoUsuario) ));
    }

}

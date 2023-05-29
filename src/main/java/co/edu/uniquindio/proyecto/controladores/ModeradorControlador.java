package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.servicio.interfaces.ModeradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/moderador")
@AllArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;

    //@PutMapping("/aprobar/{codigoProducto}")
    @GetMapping("/aprobar/{codigoProducto}")
    ResponseEntity<MensajeDTO> aprobarProducto(@PathVariable int codigoProducto  ) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                moderadorServicio.aprobarProducto(codigoProducto,1)  ));
    }

    //@PutMapping("/rechazar/{codigoProducto}")
    @GetMapping("/rechazar/{codigoProducto}")
    ResponseEntity<MensajeDTO> rechazarProducto(@PathVariable int codigoProducto) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                moderadorServicio.rechazarProducto(codigoProducto)  ));
        //moderadorServicio.rechazarProducto(codigoProducto);
        //return producto.getActivo();
    }

}

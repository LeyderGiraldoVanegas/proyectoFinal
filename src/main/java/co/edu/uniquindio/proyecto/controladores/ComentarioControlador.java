package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ComentarioGetDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentario")
@AllArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear")
    ResponseEntity<MensajeDTO> crearComentario(@RequestBody ComentarioDTO comentarioDTO) throws Exception{
       // int comentario = comentarioServicio.crearComentario(comentarioDTO);
        return  ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK,
                false,"Comentario creado exitosamente! Código: " +comentarioServicio.crearComentario(comentarioDTO) ) );
    }

    @PutMapping("/actualizar/{codigoComentario}")
    ResponseEntity<MensajeDTO> actualizarComentario(@PathVariable int codigoComentario, @RequestBody ComentarioDTO comentarioDTO) throws Exception{
        comentarioServicio.actualizarComentario(codigoComentario,comentarioDTO);
        return  ResponseEntity.status(200).body(new MensajeDTO(HttpStatus.OK,
                false,"El comentario se ah Actualizado exitosamente! Código: " + codigoComentario) );

    }


    @DeleteMapping("/eliminar/{codigoComentario}")
    int eliminarComentario(@PathVariable int codigoComentario) throws Exception{
        return comentarioServicio.eliminiarComentario(codigoComentario);
    }


    @GetMapping("/obtener/{codigoComentario}")
    String obtenerComentario(@PathVariable int codigoComentario) throws Exception{
        return comentarioServicio.obtenerComentario(codigoComentario).getMensaje();
    }

    @GetMapping("/listar/{codigoProducto}")
    public  ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigoProducto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                comentarioServicio.listarComentarios(codigoProducto)));
    }

}

package co.edu.uniquindio.proyecto.controladores;



import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO)  throws Exception{
        return ResponseEntity.status(200).body( new MensajeDTO(HttpStatus.OK,
                false, "Usuario creado exitosamente! Código: "+ usuarioServicio.crearUsuario(usuarioDTO)) );
    }

    @PutMapping("/actualizar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @RequestBody UsuarioDTO usuarioDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO)));
    }

    @DeleteMapping("/eliminar/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> eliminiarUsuario(@PathVariable int codigoUsuario) throws Exception{
        usuarioServicio.eliminiarUsuario(codigoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, "Usuario eliminado correctamente") );
    }

    @GetMapping("/obtener/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.obtenerUsuario(codigoUsuario)));
    }

    @GetMapping("/cambiarContrasena/{codigoUsuario}/{pass}")
    public ResponseEntity<MensajeDTO> cambiarContrasena(@PathVariable int codigoUsuario,String pass ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,
                false, usuarioServicio.cambiarContrasena(codigoUsuario,pass)));
    }


//obtener debe retornar un UsuarioGetDTO (no borren el que ya tiene)
}








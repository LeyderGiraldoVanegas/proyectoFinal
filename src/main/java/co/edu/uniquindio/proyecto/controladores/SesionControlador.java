package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.SesionServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/sesion")
@AllArgsConstructor
public class SesionControlador {

    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    TokenDTO login(@Valid  @RequestBody SesionDTO sesionDTO){

        return sesionServicio.login(sesionDTO);
    }

    void logout(@PathVariable int codigoUsuario){

    }

}


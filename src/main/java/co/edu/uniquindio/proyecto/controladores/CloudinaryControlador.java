package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.servicio.interfaces.CloudinaryServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@AllArgsConstructor
public class CloudinaryControlador {

    private final CloudinaryServicio cloudinaryServicio;

    @PostMapping("/subirImagen")
    public Map subirImagen(@RequestParam MultipartFile mFile) throws Exception{
        File imagen = cloudinaryServicio.convertir(mFile);
        return cloudinaryServicio.subirImagen(imagen,"PA");
    }

    @DeleteMapping("/eliminarImagen/{codigo}")
    public Map eliminarImagen(@RequestBody String idImagen) throws Exception{
        return cloudinaryServicio.eliminarImagen(idImagen);

    }
}

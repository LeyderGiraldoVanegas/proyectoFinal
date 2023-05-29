package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioGetDTO {


    private LocalDateTime fechaCreacion;

    private String mensaje;

    private int productoComentario;
    private int usuarioComentario;
}

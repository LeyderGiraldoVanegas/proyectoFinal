package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioDTO {


    private String menasaje;
    private int codigoUsuario;
    private int codigoProducto;


}

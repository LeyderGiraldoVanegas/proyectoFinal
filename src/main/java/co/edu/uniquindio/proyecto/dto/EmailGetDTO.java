package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class EmailGetDTO {
    private String asunto;
    private String Cuerpo;
    private String destinatario;
    private LocalDateTime fechaCreacion;

}

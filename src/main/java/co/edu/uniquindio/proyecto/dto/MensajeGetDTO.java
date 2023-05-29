package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MensajeGetDTO <T>{
    private HttpStatus estado;
    private boolean error;
    private T respuesta;
}

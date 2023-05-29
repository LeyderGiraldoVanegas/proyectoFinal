package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioGetDTO {

    private Integer codigo;

    private String nombre;

    private String email;

    private String direccion;

    private String telefono;
    private String password;
    private String estado;
}

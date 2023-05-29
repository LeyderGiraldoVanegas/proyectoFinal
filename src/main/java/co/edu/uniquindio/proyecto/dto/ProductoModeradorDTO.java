package co.edu.uniquindio.proyecto.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductoModeradorDTO {
    @NotBlank
    @NotNull
    private int idModerador;

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 50)
    private String motivo;

}

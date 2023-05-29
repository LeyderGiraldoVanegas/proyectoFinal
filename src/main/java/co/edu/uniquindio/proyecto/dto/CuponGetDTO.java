package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CuponGetDTO {

    private Integer codigoCupon;

    private double porcentaje;
    /**
     * ! LocalDate se tiene que cambiar¡
     */
    private LocalDate fechaLimite;

    private Usuario usuarioCuponDTO;

    private Compra compraCuponDTO;
}

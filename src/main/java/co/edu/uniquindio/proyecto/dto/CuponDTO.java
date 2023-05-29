package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
public class CuponDTO {
    private int codigoCupon;
    private int codoigoCompra;

    private double porcentaje;

    private LocalDate fechaLimite;

    //private Usuario usuarioCuponDTO;

    //private Compra compraCuponDTO;

}

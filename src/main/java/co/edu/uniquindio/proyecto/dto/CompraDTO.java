package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Cupon;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraDTO {

    private Integer usuarioCompra;
    private float valorTotal;
    private String metodoPago;
    private List<DetalleCompra> detalleCompraList;
    private List<Cupon>  cuponList;

}

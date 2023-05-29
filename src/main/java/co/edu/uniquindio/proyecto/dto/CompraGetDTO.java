package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Cupon;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class CompraGetDTO {
    private int codigoCompra;
    private LocalDateTime fecha;
    private float valorTotal;
    private String metodoPago;
    private Usuario codigoUsuario;
    private List<DetalleCompra> detalleCompraList;
    private List<Cupon>  cuponList;
}

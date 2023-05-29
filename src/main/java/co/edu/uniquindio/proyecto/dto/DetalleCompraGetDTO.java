package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DetalleCompraGetDTO {
    private int codigoProducto;
    private Integer unidades;
    private double precioProducto;
    private int compra;
    private int productoDetalleCompra;

}

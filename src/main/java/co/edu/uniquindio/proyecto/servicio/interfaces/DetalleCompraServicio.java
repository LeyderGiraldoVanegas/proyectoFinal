package co.edu.uniquindio.proyecto.servicio.interfaces;

import co.edu.uniquindio.proyecto.dto.DetalleCompraGetDTO;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;

import java.util.List;

public interface DetalleCompraServicio {


    DetalleCompraGetDTO obtenerDetalleCompra(int codigoDetalleCompra) throws Exception;


    List<DetalleCompraGetDTO> listarProductosUsuario(int codigoUsuario) throws Exception;


    List<Integer> obtenerDetallesCodigo(List<DetalleCompra> compras);

    DetalleCompraGetDTO obtenerCompra(int codigoCompra) throws Exception;
}


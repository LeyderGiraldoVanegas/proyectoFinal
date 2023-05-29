package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.DetalleCompraGetDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.DetalleCompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detallecompra")
@AllArgsConstructor
public class DetalleCompraControlador {


    private final DetalleCompraServicio detalleCompraServicio;

    @GetMapping("/obtenerDetalleCompra/{codigoCompra}")
    int obtenerDetalleCompra(@PathVariable int codigoCompra) throws Exception {
        return detalleCompraServicio.obtenerCompra(codigoCompra).getCodigoProducto();
    }

}

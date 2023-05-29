package co.edu.uniquindio.proyecto.servicio.interfaces;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoModeradorDTO;
import co.edu.uniquindio.proyecto.modelo.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public interface ModeradorServicio {

  //  void aprobarProducto(int codigoProducto) throws Exception;

    boolean aprobarProducto(int codigoProducto, int codigoModerador) throws Exception;

    boolean rechazarProducto(int codigoProducto) throws Exception;

    Moderador obtenerModerador(int codigoModerador) throws Exception;
}

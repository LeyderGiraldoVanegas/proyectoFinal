package co.edu.uniquindio.proyecto.servicio.interfaces;

import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO login(SesionDTO sesionDTO);

    TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception;

    void logout(int codigoUsuario);
}

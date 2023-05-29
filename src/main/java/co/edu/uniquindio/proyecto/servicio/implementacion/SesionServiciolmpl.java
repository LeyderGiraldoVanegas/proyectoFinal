package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import co.edu.uniquindio.proyecto.seguridad.servicios.UserDetailsServiceImpl;
import co.edu.uniquindio.proyecto.servicio.interfaces.SesionServicio;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SesionServiciolmpl implements SesionServicio {

    @Autowired
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private  final UserDetailsServiceImpl userDetailsService;

    @Override
    public TokenDTO login(SesionDTO sesionDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDTO.getEmail(),
                        sesionDTO.getPassword())

        );
        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new TokenDTO(jwtToken, refreshToken);
    }

    @Override
    public void logout(int codigoUsuario) {

    }

    @Override
    public TokenDTO refreshToken(TokenDTO tokenDTO) throws Exception{
        String email = jwtService.extractUsername(tokenDTO.getRefreshToken());
        UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
        if (jwtService.isTokenValid(tokenDTO.getRefreshToken(), user)) {
            String token = jwtService.generateToken(user);
            return new TokenDTO( token, tokenDTO.getRefreshToken() );
        }
        throw new Exception("Error construyendo el token");
    }
}

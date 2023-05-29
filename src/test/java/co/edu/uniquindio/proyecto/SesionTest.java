package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.SesionServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class SesionTest {
    @Autowired
    private SesionServicio sesionServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest( ) throws Exception{


        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    "Pepito 1",
                    "pepe1@email.com",
                    "1234",
                    "Calle 123",
                    "343");
            int codigo = usuarioServicio.crearUsuario(usuarioDTO);

            //Assertions.assertNotEquals(0, codigo);

        }catch (Exception e){
            e.printStackTrace();
        }

        TokenDTO tokenDTO = sesionServicio.login(new SesionDTO("epe1@email.com","1234"));
        Assertions.assertNotNull(tokenDTO);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void OlvidarContrasena( ) throws Exception{

        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    "Pepito 1",
                    "pepe1@email.com",
                    "1234",
                    "Calle 123",
                    "343");
            int codigo = usuarioServicio.crearUsuario(usuarioDTO);

            //Assertions.assertNotEquals(0, codigo);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}

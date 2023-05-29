package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.EmailServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class EmailTest {

    @Autowired
    private EmailServicio emailServicio;
    @Test
    @Sql("classpath:dataset.sql")
    public void enviarCorreoTest() throws Exception{
        LocalDateTime fecha = LocalDateTime.now();
        String email="<h1>Este es un mensaje de prueba</h1><p>¡Gracias Mail!!</p><p>....." +
                "</p><img src='https://www.uniquindio.edu.co/info/uniquindio/media/bloque2477.png' " +
                "width='400' height='200'>";
        emailServicio.enviarEmail(new EmailDTO(
                "TestMail-Html",
                email,
                "davida.cruzn@uqvirtual.edu.co",
                fecha));

    }
}

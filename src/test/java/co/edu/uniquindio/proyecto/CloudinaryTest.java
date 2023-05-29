package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicio.interfaces.CloudinaryServicio;
import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Map;

@SpringBootTest
@Transactional
public class CloudinaryTest {
    @Autowired
    private CloudinaryServicio cloudinaryServicio;


    @Test
    public void subirImagenTest() throws Exception {
        // C:\Users\Acer\Downloads
        File file = new File("C:\\Users\\Acer\\Downloads\\prueba.jpg");
        cloudinaryServicio.subirImagen(file, "dto");
    }

    @Test
    public void eliminarImagen() throws Exception {
        cloudinaryServicio.eliminarImagen("https://res.cloudinary.com/dzoevuzey/image/upload/v1684810643/PA/zqxxrba1c7jgl9rf81sh.jpg");
    }
}

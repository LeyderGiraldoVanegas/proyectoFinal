package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.servicio.interfaces.CloudinaryServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicioImpl implements CloudinaryServicio {

    private final Cloudinary cloudinary;
    public CloudinaryServicioImpl(){
        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "dzoevuzey");
        config.put("api_key", "421494817896467");
        config.put("api_secret", "vb9gm9tw-bxRNgcB_McokUD25mo");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(File file, String carpeta) throws Exception{

        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", carpeta));
    }
    @Override
    public Map eliminarImagen(String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    @Override
    public File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
/**
    private final Cloudinary cloudinary;
    private Map<String, String> config;


    public void CloudinaryServicio(){
        /**
         * cloud_name: "dzoevuzey",
         *   api_key: "421494817896467",
         *   api_secret: "vb9gm9tw-bxRNgcB_McokUD25mo"

        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "dzoevuzey");
        config.put("api_key", "421494817896467");
        config.put("api_secret", "vb9gm9tw-bxRNgcB_McokUD25mo");
         Cloudinary cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(File file, String carpeta) throws Exception {

        return cloudinary.uploader().upload(file,ObjectUtils.asMap("folder",String.format("co/edu/uniquindio/proyecto/%s", carpeta)));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    @Override
    public File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }*/
}

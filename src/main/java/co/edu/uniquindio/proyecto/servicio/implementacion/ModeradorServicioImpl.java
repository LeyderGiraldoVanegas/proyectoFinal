package co.edu.uniquindio.proyecto.servicio.implementacion;


import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.Moderador;

import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio{

    @Autowired
    EmailServicioImpl emailServicioImpl;
    @Autowired
    ModeradorRepo moderadorRepo;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private ProductoRepo productoRepo;
    @Override
    public boolean aprobarProducto(int codigoProducto, int codigoModerador) throws Exception {

        Producto producto = productoServicio.obtener(codigoProducto);
        if (producto != null){
            if (!producto.getActivo().equals("ACTIVO")){
                producto.setActivo("ACTIVO");
                producto.setFechaLimite(LocalDateTime.now().plusDays(60));
                producto.setModerador(obtenerModerador(codigoModerador));
                productoRepo.save(producto);
                enviarEmail(producto.getUsuario().getEmail() , "ACTIVO");
                return true;
            }else {
                throw new Exception("El producto ya esta activo");

            }

        }else {
            throw new Exception("producto en el codigo: "+ codigoProducto+ "No se a encontrado");
        }
        //return false;
    }

    private void enviarEmail(String email, String estado) {

        LocalDateTime fecha = LocalDateTime.now();
        String emailEnviar="<h1>El producto ha sido "+estado+"</h1><p>¡Gracias Mail!!</p><p>....." +
                "</p><img src='https://www.uniquindio.edu.co/info/uniquindio/media/bloque2477.png' " +
                "width='400' height='200'>";
        emailServicioImpl.enviarEmail(new EmailDTO(
                "TestMail-Html",
                email,
                "davida.cruzn@uqvirtual.edu.co",
                fecha));
    }

    @Override
    public boolean rechazarProducto(int codigoProducto) throws Exception {

        Producto producto = productoServicio.obtener(codigoProducto);
        if (producto!= null){
            if (!producto.getActivo().equals("RECHAZADO")){
                producto.setActivo("RECHAZADO");
                productoRepo.save(producto);
                enviarEmail(producto.getUsuario().getEmail(), "RECHAZADO");
                return true;
            }else {
                throw new Exception("El producto ya esta RECHAZADO");
            }

        }else {
            throw new Exception("producto en el codigo: "+ codigoProducto+ "No se a encontrado");
        }
    }

    @Override
    public Moderador obtenerModerador(int codigoModerador) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findById(codigoModerador);

        if(moderador.isEmpty() ) {
            throw new Exception("El código " + codigoModerador + " no está asociado a ningún moderador");
        }
            return moderador.get();
    }
}

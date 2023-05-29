package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.dto.DetalleCompraDTO;
import co.edu.uniquindio.proyecto.dto.DetalleCompraGetDTO;
import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.CompraServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetalleCompraServicioImpl implements DetalleCompraServicio {
    DetalleCompraRepo detalleCompraRepo;
    CompraServicio compraServicio;
    CompraRepo compraRepo;

    @Override
    public List<DetalleCompraGetDTO> listarProductosUsuario(int codigoUsuario) throws Exception {
        List<DetalleCompra> listaCompra = detalleCompraRepo.listarDetalleComprasUsuario(codigoUsuario);
        List<DetalleCompraGetDTO> respuesta = new ArrayList<>();

        for (DetalleCompra p :listaCompra) {
            respuesta.add(convertir(p));
        }
        return respuesta;
    }


    @Override
    public List<Integer> obtenerDetallesCodigo(List<DetalleCompra> compras) {
        List<Integer> codigoDetalles = new ArrayList<>();
        for (DetalleCompra d: compras) {
            codigoDetalles.add( d.getCodigo() );

        }
        return codigoDetalles;
    }

    @Override
    public DetalleCompraGetDTO obtenerCompra(int codigoCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(codigoCompra);
        if(compra.isEmpty() ){
            throw new Exception("El código "+codigoCompra+" no está asociado a ningúna compra");
        }
        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(compra.get().getDetalleCompraList().get(0).getCodigo());

        if(detalleCompra.isEmpty() ){
            throw new Exception("El código "+compra.get().getDetalleCompraList().get(0).getCodigo()+" no está asociado a ningún producto");
        }

        return convertir(detalleCompra.get());
    }

    @Override
    public DetalleCompraGetDTO obtenerDetalleCompra(int codigoDetalleCompra) throws Exception {

        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(codigoDetalleCompra);

        if(detalleCompra.isEmpty() ){
            throw new Exception("El código "+codigoDetalleCompra+" no está asociado a ningún producto");
        }

        return convertir(detalleCompra.get());
    }


    private DetalleCompraGetDTO convertir(DetalleCompra detalleCompra) throws Exception {
        Compra compra = compraServicio.obtenerCompra( detalleCompra.getCompra().getCodigo());
        DetalleCompraGetDTO detalleCompraGetDTO = new DetalleCompraGetDTO(
                detalleCompra.getCodigo(),
                detalleCompra.getUnidades(),
                detalleCompra.getPrecioProducto(),
                compra.getCodigo(),
                detalleCompra.getProductoDetalleCompra().getCodigo()

        );
        return detalleCompraGetDTO;
    }

    public DetalleCompraDTO convertirDTO(DetalleCompra detalleCompra) {
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO(
                detalleCompra.getUnidades()
               // detalleCompra.getProductoDetalleCompra().getCodigo()
        );
        return detalleCompraDTO;
    }

}

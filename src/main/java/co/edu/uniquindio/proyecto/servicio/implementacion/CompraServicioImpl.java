package co.edu.uniquindio.proyecto.servicio.implementacion;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.servicio.interfaces.CompraServicio;
import co.edu.uniquindio.proyecto.servicio.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioServicio usuarioServicio;
    private final UsuarioServicioImpl usuarioServicioImpl ;
    private final DetalleCompraRepo detalleCompraRepo;
    private final ProductoServicioImpl productoServicio;
    @Override
    public int crearCompra(CompraDTO compraDTO) throws Exception {

       Compra compra = convertirCompraDTOaCompra(compraDTO);
       usuarioServicioImpl.validarExiste(compraDTO.getUsuarioCompra());

        validarProductos(compraDTO);

        return compraRepo.save(compra).getCodigo();
    }

    private void validarProductos(CompraDTO compraDTO) throws Exception {
        //
        LocalDateTime localDateTime = LocalDateTime.now();
        for (DetalleCompra d: compraDTO.getDetalleCompraList()){
            if (d.getProductoDetalleCompra() != null){
                productoServicio.validarExiste(d.getProductoDetalleCompra().getCodigo());
            }
            if (d.getProductoDetalleCompra().getActivo().equals("ACTIVO")){

            }else {
                throw new Exception("el producto no esta Activo");
            }
            if (d.getProductoDetalleCompra().getFechaLimite().isAfter(localDateTime)){

            }else {
                throw new Exception("El producto:" +d.getProductoDetalleCompra().getNombre()
                        +" ya no esta disponible para hacer este procedimiento ");
            }
            if (d.getUnidades() <= d.getProductoDetalleCompra().getUnidades()){

            }else{
                throw new Exception("no hay unidades suficientes para hacer la compra");
            }

            d.getProductoDetalleCompra().setUnidades( d.getProductoDetalleCompra().getUnidades() - d.getUnidades() );

            productoServicio.guardarRepo( d.getProductoDetalleCompra() );
        }


    }

    @Override
    public CompraGetDTO actualizarCompra(int codigoCompra, CompraDTO compraDTO) throws Exception {
        validarExiste(codigoCompra);

       Compra compra = convertirCompraDTOaCompra(compraDTO);
       compra.setCodigo(codigoCompra);

       return convertir( compraRepo.save(compra) );

    }

    private Compra convertirCompraDTOaCompra(CompraDTO compraDTO) throws Exception {

        LocalDateTime fecha = LocalDateTime.now();
        Usuario usuario = usuarioServicio.obtener(compraDTO.getUsuarioCompra());
        Compra compra = new Compra();
        //fecaha_creacion
        compra.setFecahaCreacion(fecha);
        //medio_pago
        compra.setMedioPago(compraDTO.getMetodoPago());
        //valor_total
        compra.setValorTotal(compraDTO.getValorTotal());
        //usuario_compra_codigo
        compra.setUsuarioCompra(usuario);
        //List Detalle compra
        compra.setDetalleCompraList(compraDTO.getDetalleCompraList());
       // List<DetalleCompra> detalleCompraList = compra.getDetalleCompraList();
        List<DetalleCompra> lista = compra.getDetalleCompraList();

        if (lista != null){
            for (DetalleCompra d: lista){
                // compraRepo.save(compra).getCodigo();
                //int valor = d.getUnidades() * compraDTO.g
               // lista.add(d);
                //compra.setCuponList(compraDTO.getCuponList());
                detalleCompraRepo.save(d);
            }
        }


        //List Cupones

        return compra;
    }

    private CompraGetDTO convertir(Compra compra) throws Exception {

        CompraGetDTO compraGetDTO = new CompraGetDTO(
                compra.getCodigo(),
                compra.getFecahaCreacion(),
                compra.getValorTotal(),
                compra.getMedioPago(),
                compra.getUsuarioCompra(),
                compra.getDetalleCompraList(),
                compra.getCuponList()
        );


        return compraGetDTO;
    }

    private void validarExiste(int codigoCompra) throws Exception {
        boolean existe = compraRepo.existsById(codigoCompra);

        if( !existe ){
            throw new Exception("El código "+codigoCompra+" no está asociado a ningún compra");
        }
    }

    @Override
    public int eliminiarCompra(int codigoCompra) throws Exception {
        validarExiste(codigoCompra);
        compraRepo.deleteById(codigoCompra);
        return codigoCompra;
    }

    @Override
    public Compra obtenerCompra(int codigoCompra) throws Exception {
        Optional<Compra> compra = compraRepo.findById(codigoCompra);

        if(compra.isEmpty() ){
            throw new Exception("La compra no existe");
        }

        return compra.get();
    }

    @Override
    public List<CompraGetDTO> listarCompras() throws Exception {

        List<Compra> lista = compraRepo.listarCompras();
        List<CompraGetDTO> respuesta = new ArrayList<>();

        for (Compra c : lista ) {
            respuesta.add(convertir(c));
        }
        return respuesta;
    }

    @Override
    public List<ProductoGetDTO> listarComprasUsuarioSinRepetir(int codigoUsuario) throws Exception {

        List<Producto> lista = compraRepo.listarComprasUsuarioSinRepetir(codigoUsuario);
        List<ProductoGetDTO> respuesta = new ArrayList<>();

        for (Producto p : lista ) {

            respuesta.add( productoServicio.convertir(p));
        }
        return respuesta;
    }

    @Override
    public List<CompraGetDTO> listarComprasUsuario(int codigoUsuario) throws Exception {
        List<Compra> lista = compraRepo.listarComprasUsuario(codigoUsuario);
        List<CompraGetDTO> respuesta = new ArrayList<>();

        for (Compra c : lista ) {

            System.out.println("Compra numero: "+c.getCodigo());
            respuesta.add(convertir(c));
        }
        return respuesta;
    }


}

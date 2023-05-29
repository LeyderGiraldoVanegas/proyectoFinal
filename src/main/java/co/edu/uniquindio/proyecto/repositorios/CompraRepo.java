package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select c from Compra c where c.codigo = :codigoCompra")
    List<Compra> obtenerCompras(int codigoCompra);

    @Query("select c from Compra c  ")
    List<Compra> listarCompras();

    @Query("select p from Compra p where p.codigo = :codigoCompra")
    List<Compra> listarCompras(int codigoCompra);


    @Query("select distinct d.productoDetalleCompra from Compra p join p.detalleCompraList d  where p.usuarioCompra.codigo = :codigoUsuario")
    List<Producto> listarComprasUsuarioSinRepetir(int codigoUsuario);

    @Query("select c from Compra c  where c.usuarioCompra.codigo = :codigoUsuario")
    List<Compra> listarComprasUsuario(int codigoUsuario);
}

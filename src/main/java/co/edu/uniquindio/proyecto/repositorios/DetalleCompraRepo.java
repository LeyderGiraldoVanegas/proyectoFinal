package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Compra;
import co.edu.uniquindio.proyecto.modelo.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {


    @Query("select p from DetalleCompra p where p.compra.usuarioCompra= :codigoUsuario")
    List<DetalleCompra> listarDetalleComprasUsuario(int codigoUsuario);
}

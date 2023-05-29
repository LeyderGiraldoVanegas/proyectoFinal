package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Comentario;
import co.edu.uniquindio.proyecto.modelo.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer> {

   // @Query("select c from Cupon c where c.compraCupon.codigo = :codigoCupon")
   // List<Cupon> obtenerCupones(int codigoCupon);

    /**
     * @Repository
     * public interface CuponRepo extends JpaRepository<Cupon, Integer> {
     * }
     */
}

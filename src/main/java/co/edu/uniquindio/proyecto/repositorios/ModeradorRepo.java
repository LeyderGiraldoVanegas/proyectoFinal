package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, Integer> {

    @Query("select m from Moderador m where m.email = :correo")
    Moderador buscarModerador(String correo);

    @Query("select p from Moderador p where p.email = :email")
    Optional<Moderador> findByEmail(String email);
    /**
     * @Query("select u from Usuario u where u.email = :correo")
     *     Usuario buscarUsuario (String correo);
     */
}

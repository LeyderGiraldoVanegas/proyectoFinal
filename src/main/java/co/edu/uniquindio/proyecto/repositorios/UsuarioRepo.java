package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {

    @Query("select u from Usuario u where u.email = :correo")
    Usuario buscarUsuario (String correo);
    @Query("select u from Usuario u where u.email = :email and u.password = :password")
    Usuario comprobarAutenticacion(String email, String password);


    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndPassword(String email, String password);

}

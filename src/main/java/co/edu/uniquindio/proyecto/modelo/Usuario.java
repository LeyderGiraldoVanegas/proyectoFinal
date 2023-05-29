package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


// Getter and Setter = Obtener y enviar a todos
// los atributos que tenemos declarados en nuestra clase
//
// Entity = Cuando corra se vuelve una tabla en nuestra BD
//
// Equals And Hash Code = Nos ayuda a la hora de comparar objetos
// los compara en la tabla Hash

/**
 * Usuario es una tabla que tiene como atributos:
 * código, teléfono, dirección y los atributos de la clase Persona.
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    /** public Persona_Administrador(String cedula, String nombreCompleto, String email, String contrasena, Ciudad ciudad) {
     super(cedula, nombreCompleto, email, contrasena, ciudad);
     }*/

 /**   public  Persona_Usuario (String cedula, String nombreCompleto, String email, String contrasena){
        super()
    }*/

    /**
     * Column nullable = Validamos que el teléfono sea diferente de null
     */
    @Column(nullable = false,length = 12)
    private String telefono;

    /**
     * Column nullable = Validamos que la dirección sea diferente de null
     */
    @Column(nullable = false)
    private String direccion;
    private String estado;

    /**
     *
     */
    @ManyToMany
    private List<Producto> productoListFavorito;

    /**
     *
     */
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productoList;

    /**
     *
     */
    @OneToMany(mappedBy = "usuarioComentario")
    private List<Comentario> comentarioList;

    /**
     *
     */
    @OneToMany(mappedBy = "usuarioCompra")
    private List<Compra> compraList;

    /**
     *
     */
    @OneToMany(mappedBy = "usuarioCupon")
    private List<Cupon> cuponList;

    /**
     *
     */
    @ManyToMany(mappedBy = "usuarioFavoritoList")
    private List<Producto> productoFavoritoList;

    public Usuario(Integer cedula, String nombre, String email, String password, String telefono , String direccion) {
        super(cedula, nombre, email, password);
        this.telefono = telefono;
        this.direccion = direccion;
    }


}

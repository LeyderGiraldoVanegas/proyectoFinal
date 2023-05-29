package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;


// MappedSuperclass = se utiliza para indicar que una clase es una superclase
// que no debe ser mapeada directamente a una tabla de base de
// datos, pero que sus atributos deben ser heredados por las
// subclases que sí serán mapeadas.
//
// Getter and Setter = Obtener y enviar a todos
// los atributos que tenemos declarados en nuestra clase
//
// Equals And Hash Code = Nos ayuda a la hora de comparar objetos
// los compara en la tabla Hash
/**
 * Clase padre Persona va a extender de usuario y moderador.
 * Tiene como Argumentos: código, nombre, email, password.
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona implements Serializable {
    /**
     * Id = Es el identificador de la clase
     * Column length = limita el tamaño del atributo
     * Equals And Hash Code = A la hora de comparar el objeto lo compara en el HashCode
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es mejor que sea auto incrementable
    private Integer codigo;

    /**
     * Column Nullable = Validamos que el nombre sea
     * diferente de null
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Column Nullable = Validamos que el email sea
     * diferente de null
     */

    @NotBlank
    @Email
    @Column(nullable = false,unique = true,length = 150)
    private String email;

    /**
     * Column Nullable = Validamos que el la contraseña sea diferente de null
     */
    @Column(nullable = false)
    private String password;
}

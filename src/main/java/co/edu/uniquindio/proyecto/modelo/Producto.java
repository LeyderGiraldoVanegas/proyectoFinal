package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

// Getter and Setter = Obtener y enviar a todos
// los atributos que tenemos declarados en nuestra clase
//
// NoArgsConstructor = Un constructor sin argumentos generado
// automáticamente por Lombok.
//
// ToString = Se utiliza para generar automáticamente el método toString() de una clase.
//
// Entity = Se usa para indicar que una clase es una entidad que se
// puede mapear a una tabla de base de datos.
//
// EqualsAndHashCode = Se emplear para comparar objetos y
// almacenarlos en estructuras de datos hash
//
// EqualsAndHashCode(onlyExplicitlyIncluded = true) = Permite incluir solo
// los atributos que se marcan explícitamente con la anotación
// @EqualsAndHashCode.Include.

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
public class Producto implements Serializable {
    /**
     * Id = Hacemos que sea la primari key
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     * EqualsAndHashCode. Include = para incluir explícitamente
     * un atributo en la generación automática de los métodos
     * equals() y hashCode() de una clase.
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   @GeneratedValue(strategy = GenerationType.IDENTITY) //es auto incrementable
    private Integer codigo;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
    @Column(nullable = false)
    private int unidades;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
    @Column(nullable = false)
    private String descripcion;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
    @Column(nullable = false)
    private float precio;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
    @Column(nullable = false)
    private String activo;

    /**
     * Column nullable = Se refiere a que el campo de la tabla
     * de la base de datos no puede ser nulo.
     */
   // @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    /**
     * La fecha límite se asigna automáticamente durante 2
     * meses después de que el moderador lo aprueba
     */
    private LocalDateTime fechaLimite;
    /**
     *
     */
    @ManyToMany(mappedBy = "productoListFavorito")
    private List<Usuario> favoritosList;
    /**
     *
     */
    @ManyToOne
    private Usuario usuario;
    /**
     *
     */
    @ManyToOne
    private Moderador moderador;
    /**
     *
     */
    @ElementCollection
    private List<Categoria> categoriaList;
    /**
     *
     */
    @OneToMany(mappedBy = "productoComentario")
    private List<Comentario> comentarioList;

    /**
     *
     */
    @OneToMany(mappedBy = "productoDetalleCompra")
    private List<DetalleCompra> detalleCompraList;

    /**
     *
     */
    @ManyToMany
    private List<Usuario> usuarioFavoritoList;

    @ElementCollection
    private List<String> imagenesList;

}

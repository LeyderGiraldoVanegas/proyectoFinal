package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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

/**
 * El comentario tiene como atributos
 * código, mensaje, fechaCreación
 */
@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class Comentario implements Serializable {
    /**
     * Cada comentario debe tener por lo menos
     * Un código que lo identifique
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    /**
     * Mensaje está validado con un Column nullable
     * para que no puedan ingresar valores nulos
     */
    @Column(nullable = false)
    private String mensaje;
    /**
     * La fecha de creación está dada por la
     * necesidad de mostrar la hora necesaria
     */
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    /**
     *
     */
    @ManyToOne
    private Producto productoComentario;

    /**
     *
     */
    @ManyToOne
    private Usuario usuarioComentario;

}

package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
 * Los atributos necesarios para la clase de DetalleCompra son:
 * código, unidades y precioProducto
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompra implements Serializable {
    /**
     * El código es la PK de la clase DetalleCompra
     */
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es mejor que sea auto incrementable
    private Integer codigo;

    /**
     * Unidades
     */
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false)
    private float precioProducto;


    @ManyToOne
     private Compra compra;

    @ManyToOne
    private Producto productoDetalleCompra;


}

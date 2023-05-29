package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY) //es mejor que sea auto incrementable
    private Integer codigo;
    @Column(nullable = false)
    private LocalDateTime fecahaCreacion;
    @Column(nullable = false)
    private float valorTotal;
    @Column(nullable = false)
    private String medioPago;

    @ManyToOne
    private Usuario usuarioCompra;
    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detalleCompraList;

    @OneToMany(mappedBy = "compraCupon")
    private List<Cupon> cuponList;

}

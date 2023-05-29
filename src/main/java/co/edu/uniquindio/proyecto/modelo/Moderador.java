package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
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
// EqualsAndHashCode = Se utilizan para comparar objetos y
// almacenarlos en estructuras de datos hash
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class Moderador extends  Persona implements Serializable {

    @Column(nullable = false, unique = true)
    private Integer codigo;
    @OneToMany(mappedBy = "moderador")
    private List<Producto> productoList;

    public Moderador(Integer cedula, String nombre, String email, String password,Integer codigo) {
        super(cedula, nombre, email, password);
        this.codigo=codigo;
    }


}

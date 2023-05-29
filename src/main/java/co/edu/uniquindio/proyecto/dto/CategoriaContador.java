package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoriaContador {

    Categoria categoria;
    int total;

}

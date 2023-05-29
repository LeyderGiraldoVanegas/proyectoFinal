package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CategoriaDTO {

    @NotNull
    private List<Categoria> categoriaList;
}

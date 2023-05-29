package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductoGetDTO {

    private int codigo;

    private String estado;

    private LocalDateTime fechaLimite;

    private String nombre;

    private String descripcion;

    private int unidades;

    private float precio;

    private int codigoVendedor;

    private List<String> imagenList;
    private List<Categoria> categoriaList;
}

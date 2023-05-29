package co.edu.uniquindio.proyecto.servicio.interfaces;

import co.edu.uniquindio.proyecto.dto.CategoriaContador;
import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.modelo.Producto;

import java.util.List;

public interface ProductoServicio {

    int crearProducto(ProductoDTO productoDTO) throws Exception;

    ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception;

    int actualizarUnidades(int codigoProducto, int unidades) throws Exception;


    int eliminarProducto(int codigoProducto) throws Exception;


    ProductoGetDTO obtenerProducto(int codigoProducto) throws Exception;

    List<ProductoGetDTO> listarProductosUsuario(int codigoUsuario);
    List<ProductoGetDTO> listarProductosCompradoUsuarioSinRepetir(int codigoUsuario);
    List<ProductoGetDTO> obtenerProductoCaroBarato(Categoria categoria) throws Exception;
    List<CategoriaContador> contarProductosCadaCategoria() throws Exception;

    ProductoGetDTO convertir(Producto producto);

    List<ProductoGetDTO> listarProductosCategoria(Categoria categoria);

    List<ProductoGetDTO> listarProductosPorEstado(Estado estado);

    List<ProductoGetDTO> listarProductosFavoritos(int codigoUsuario)throws Exception;

    List<ProductoGetDTO> listarProductosNombre(String nombre);

    List<ProductoGetDTO> listarProductosPrecio(float precioMinimo, float precioMaximo);

    Producto obtener(int codigoProducto) throws Exception;

    int addProductoFavorito(int codigoProducto, int codigoVendedor) throws Exception;

    List<ProductoGetDTO> listarProductos();

    List<ProductoGetDTO> listarProductosEstado(String estado);

    List<ProductoGetDTO> buscarProductonombre(String nombre) throws Exception;

    List<ProductoGetDTO> listarProductosTodos();
}

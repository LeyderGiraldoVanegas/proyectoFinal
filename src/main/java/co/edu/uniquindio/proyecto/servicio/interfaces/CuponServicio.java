package co.edu.uniquindio.proyecto.servicio.interfaces;

import co.edu.uniquindio.proyecto.dto.CompraDTO;
import co.edu.uniquindio.proyecto.dto.CuponDTO;
import co.edu.uniquindio.proyecto.dto.CuponGetDTO;
import co.edu.uniquindio.proyecto.modelo.Cupon;

public interface CuponServicio {
    /**
     * Metodo Para Crear Compra
     * @param cuponDTO
     * @return
     * @throws Exception
     */
    int crearCompra(CuponDTO cuponDTO) throws  Exception;
    CuponGetDTO actualizarCupon(int codigoCupon, CuponDTO cuponDTO)throws Exception;

    /**
     * Metodo Para eliminar Compra
     * @param codigoCupon
     * @return
     * @throws Exception
     */
    int eliminiarCupon(int codigoCupon)throws Exception;

    /**
     * Metodo Para obtner CompraDTO
     * @param codigoCupon
     * @return
     * @throws Exception
     */
    CuponGetDTO obtenerCupon(int codigoCupon)throws Exception;

    /**
     * Metodo Para Obtener Compra
     * @param codigoComentario
     * @return
     * @throws Exception
     */
    Cupon obtener(int codigoComentario)throws Exception;



}

package cl.ionix.sistema.exception;

import lombok.Getter;

/**
 *  Excpeción personalizada para identificar cuando una consulta no encuentra resultados
 */
@Getter
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    private final String mensaje;


    public NotFoundException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}

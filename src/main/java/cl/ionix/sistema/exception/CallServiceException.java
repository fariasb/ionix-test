package cl.ionix.sistema.exception;

import lombok.Getter;

/**
 *  Excpeción personalizada para identificar caidas en llamadas a servicios
 */
@Getter
public class CallServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    private final String mensaje;


    public CallServiceException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}

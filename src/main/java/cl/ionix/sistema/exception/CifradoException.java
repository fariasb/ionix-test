package cl.ionix.sistema.exception;

import lombok.Getter;

@Getter
public class CifradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    private final String mensaje;


    public CifradoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}

package cl.ionix.sistema.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    private final String mensaje;


    public NotFoundException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}

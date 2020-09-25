package cl.ionix.sistema.exception;

import cl.ionix.sistema.to.ResponseTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Manejo de expciones personalizadas para emitir distintas respuestas del controller
 */
@Log4j2
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExceptionManager {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ResponseEntity<ResponseTO> manageInternalServerErrorByDefault(Exception exception) {
        log.error("Excepcion tecnica capturado en controller advice: ",exception.getMessage());
        ResponseTO responseTO  = ResponseTO.builder()
                .responseCode(1)
                .description("Error al ejecutar la operación : [" + exception.getMessage() +"]")
                .build();
        return new ResponseEntity<>(responseTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ResponseTO> manageNotFoundException(NotFoundException notFoundException){
        log.error("NotFoundExcepcion capturada en controller advice: ",notFoundException.getMessage());
        ResponseTO responseTO  = ResponseTO.builder()
                .responseCode(2)
                .description("Error en la búsqueda : [" + notFoundException.getMessage() +"]")
                .build();
        return new ResponseEntity<>(responseTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CifradoException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ResponseTO> manageCifradoException(CifradoException cifradoException){
        log.error("CifradoException capturada en controller advice: ",cifradoException.getMessage());
        ResponseTO responseTO  = ResponseTO.builder()
                .responseCode(2)
                .description("Error en la operación : [" + cifradoException.getMessage() +"]")
                .build();
        return new ResponseEntity<>(responseTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CallServiceException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ResponseTO> manageCallServiceException(CallServiceException callServiceException){
        log.error("CallServiceException capturada en controller advice: ",callServiceException.getMessage());
        ResponseTO responseTO  = ResponseTO.builder()
                .responseCode(2)
                .description("Error en la operación : [" + callServiceException.getMessage() +"]")
                .build();
        return new ResponseEntity<>(responseTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

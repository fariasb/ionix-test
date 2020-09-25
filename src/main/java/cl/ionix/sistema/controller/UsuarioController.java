package cl.ionix.sistema.controller;

import cl.ionix.sistema.services.UsuarioServices;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.UserTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "sistema")
@RestController
@RequestMapping("sistema")
@Log4j2
public class UsuarioController {

    @Autowired
    private UsuarioServices services;

    @ApiOperation(value = "Crea usuario", response = ResponseTO.class)
    @PostMapping(value = "/usuario", produces = "application/json")
    public ResponseEntity<ResponseTO> crear(@ApiParam(value = "Usario a crear", required = true) @Valid @RequestBody(required = true) UserTO userTO) {

        log.debug("Datos recibidos:" + userTO);
        return new ResponseEntity<>(services.creaUsuario(userTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Lista todos los usuarios", response = ResponseTO.class)
    @GetMapping(value = "/usuario", produces = "application/json")
    public ResponseEntity<ResponseTO> getUsuarios() {

        return new ResponseEntity<>(services.getUsuarios(), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene usuario mediante email", response = ResponseTO.class)
    @GetMapping(value = "/usuario/email/{email}", produces = "application/json")
    public ResponseEntity<ResponseTO> getUsuarioPorEmail(@PathVariable(required = true) String email) {

        log.debug("Email recibido :" + email);
        return new ResponseEntity<>(services.getUsuarioPorEmail(email), HttpStatus.OK);
    }

}

package cl.ionix.sistema.controller;

import cl.ionix.sistema.services.SandboxService;
import cl.ionix.sistema.to.ResponseTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "sandbox")
@RestController
@RequestMapping("sandbox")
@Log4j2
public class SandboxController {

    @Autowired
    private SandboxService services;

    @ApiOperation(value = "Busca usuario por rut", response = ResponseTO.class)
    @PostMapping(value = "/usuario", produces = "application/json")
    public ResponseEntity<ResponseTO> buscarUsuario(@ApiParam(value = "Parametro de busqueda", required = true) @Valid @RequestParam("param") String param) {

        log.debug("Datos recibidos:" + param);
        return new ResponseEntity<>(services.buscarPorParam(param), HttpStatus.OK);
    }
}

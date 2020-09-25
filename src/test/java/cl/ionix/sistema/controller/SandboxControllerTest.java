package cl.ionix.sistema.controller;

import cl.ionix.sistema.services.SandboxService;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.ResultTO;
import cl.ionix.sistema.to.UserTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SandboxControllerTest {

    @InjectMocks
    private SandboxController controller;

    @Mock
    private SandboxService service;


    ResponseTO responseTO;

    @Before
    public void init() {


        responseTO = ResponseTO.builder()
                .responseCode(0)
                .elapsedTime(10l)
                .result(ResultTO.builder().registerCount(10).build())
                .usuario(UserTO.builder().build())
                .build();

    }

    @Test
    public void buscarUsuarioOk(){
        when( service.buscarPorParam("1-9")).thenReturn(responseTO);

        ResponseEntity<ResponseTO> respuesta = controller.buscarUsuario("1-9");

        assertNotNull(respuesta);
        assertEquals(respuesta.getBody().getResponseCode().toString(), "0");
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }
}

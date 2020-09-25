package cl.ionix.sistema.controller;

import cl.ionix.sistema.services.UsuarioServices;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.UserTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioServices services;

    UserTO userTO;
    ResponseTO responseTO;
    List<UserTO> userTOS;

    @Before
    public void init() {

        userTO = UserTO.builder().build();
        userTOS= new ArrayList<>();
        userTOS.add(UserTO.builder().build());
        responseTO = ResponseTO.builder()
                .idCreated(1l)
                .usuarios(userTOS)
                .usuario(UserTO.builder().build())
                .build();

    }

    @Test
    public void crearOk(){

        when( services.creaUsuario(userTO)).thenReturn(responseTO);

        ResponseEntity<ResponseTO> respuesta = controller.crear(userTO);

        assertNotNull(respuesta);
        assertNotNull(respuesta.getBody().getIdCreated());
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

    }

    @Test
    public void getUsuariosOk(){

        when( services.getUsuarios()).thenReturn(responseTO);

        ResponseEntity<ResponseTO> respuesta = controller.getUsuarios();

        assertNotNull(respuesta);
        assertNotNull(respuesta.getBody().getUsuarios());
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());

    }

    @Test
    public void getUsuariosByEmailOk(){

        when( services.getUsuarioPorEmail("emailxxxx")).thenReturn(responseTO);

        ResponseEntity<ResponseTO> respuesta = controller.getUsuarioPorEmail("emailxxxx");

        assertNotNull(respuesta);
        assertNotNull(respuesta.getBody().getUsuario());
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());

    }

}
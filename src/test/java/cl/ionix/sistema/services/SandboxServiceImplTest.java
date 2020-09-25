package cl.ionix.sistema.services;

import cl.ionix.sistema.exception.CallServiceException;
import cl.ionix.sistema.exception.CifradoException;
import cl.ionix.sistema.services.impl.SandboxServiceImpl;
import cl.ionix.sistema.to.ItemTO;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.RespuestaSandboxTO;
import cl.ionix.sistema.to.ResultSandboxTO;
import cl.ionix.sistema.util.CifradoUtil;
import cl.ionix.sistema.util.Constantes;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SandboxServiceImplTest {

    public static final String HTTP_URL = "http://url";
    public static final String KEY_CIFRADO = "ionix123456";
    public static final String RUT_CIFRADO = "rutCifrado";
    public static final String URLRUT_CIFRADO = "http://urlrutCifrado";
    @InjectMocks
    private SandboxServiceImpl sandboxService;

    @Mock
    RestTemplate restTemplate;

    @Mock
    private Environment env;

    @Mock
    private CifradoUtil cifradoUtil;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    RespuestaSandboxTO respuestaSandboxTO;

    @Before
    public void init() {
        List<ItemTO> itemTOS = new ArrayList<>();
        itemTOS.add(ItemTO.builder().build());
        respuestaSandboxTO = RespuestaSandboxTO.builder()
                .responseCode(0)
                .result(ResultSandboxTO.builder().items(itemTOS).build())
                .build();
    }

    @SneakyThrows
    @Test
    public void buscarPorParamOk(){

        doReturn(HTTP_URL).when(env).getProperty(Constantes.URL_SANDBOX_SERV_SEARCH);
        doReturn(KEY_CIFRADO).when(env).getProperty(Constantes.SANDBOX_CIFRADO_KEY);
        doReturn(RUT_CIFRADO).when(cifradoUtil).cifrar(any(String.class),any(String.class));

        when(restTemplate.getForEntity(
                URLRUT_CIFRADO,  RespuestaSandboxTO.class))
                .thenReturn(new ResponseEntity<RespuestaSandboxTO>(respuestaSandboxTO, HttpStatus.OK));

        ResponseTO responseTO = sandboxService.buscarPorParam("1-9");

        assertNotNull(responseTO);
    }

    @SneakyThrows
    @Test
    public void buscarPorParamNookKeyInvalid(){

        doReturn(HTTP_URL).when(env).getProperty(Constantes.URL_SANDBOX_SERV_SEARCH);
        doReturn(KEY_CIFRADO).when(env).getProperty(Constantes.SANDBOX_CIFRADO_KEY);

        when(cifradoUtil.cifrar(any(String.class),any(String.class))).thenThrow(new CifradoException(""));

        when(restTemplate.getForEntity(
                URLRUT_CIFRADO,  RespuestaSandboxTO.class))
                .thenReturn(new ResponseEntity<RespuestaSandboxTO>(respuestaSandboxTO, HttpStatus.OK));

        exception.expect(CifradoException.class);
        sandboxService.buscarPorParam("1-9");

    }

    @SneakyThrows
    @Test
    public void buscarPorParamNookCallServiceFail(){

        doReturn(HTTP_URL).when(env).getProperty(Constantes.URL_SANDBOX_SERV_SEARCH);
        doReturn(KEY_CIFRADO).when(env).getProperty(Constantes.SANDBOX_CIFRADO_KEY);
        doReturn(RUT_CIFRADO).when(cifradoUtil).cifrar(any(String.class),any(String.class));


        when(restTemplate.getForEntity(
                URLRUT_CIFRADO,  RespuestaSandboxTO.class))
                .thenThrow(new CallServiceException(""));

        exception.expect(CallServiceException.class);
        sandboxService.buscarPorParam("1-9");

    }
}

package cl.ionix.sistema.services.impl;

import cl.ionix.sistema.exception.CallServiceException;
import cl.ionix.sistema.exception.CifradoException;
import cl.ionix.sistema.services.SandboxService;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.RespuestaSandboxTO;
import cl.ionix.sistema.to.ResultTO;
import cl.ionix.sistema.util.CifradoUtil;
import cl.ionix.sistema.util.Constantes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class SandboxServiceImpl implements SandboxService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private CifradoUtil cifradoUtil;


    @Override
    public ResponseTO buscarPorParam(String param) {

        String rutCifrado = null;
        try {
            String key = env.getProperty(Constantes.SANDBOX_CIFRADO_KEY);
            rutCifrado = cifradoUtil.cifrar(param,key);

        }catch (Exception e){
            throw new CifradoException("No se logro cifrar el parametro");
        }

        String urlSandbox = env.getProperty(Constantes.URL_SANDBOX_SERV_SEARCH);
        urlSandbox = urlSandbox.concat(rutCifrado);

        log.info(urlSandbox);

        try {
            long startTime = System.currentTimeMillis();
            RespuestaSandboxTO respuestaSandboxTO = restTemplate.getForEntity(urlSandbox, RespuestaSandboxTO.class).getBody();
            log.info(respuestaSandboxTO);
            long duration = System.currentTimeMillis() - startTime;
            return ResponseTO.builder()
                    .responseCode(respuestaSandboxTO.getResponseCode())
                    .description("OK")
                    .elapsedTime(duration)
                    .result(ResultTO.builder()
                            .registerCount( respuestaSandboxTO.getResult() != null && respuestaSandboxTO.getResult().getItems() != null ? respuestaSandboxTO.getResult().getItems().size() : 0)
                            .build())
                    .build();

        }catch (Exception e){
            throw new CallServiceException("No se logro llamar al servicio " + urlSandbox +" :" + e.getMessage());
        }

    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}

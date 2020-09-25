package cl.ionix.sistema.services;

import cl.ionix.sistema.to.ResponseTO;

public interface SandboxService {
    ResponseTO buscarPorParam(String param);
}

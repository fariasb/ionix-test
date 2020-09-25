package cl.ionix.sistema.services;

import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.UserTO;

public interface UsuarioServices {

    ResponseTO creaUsuario(UserTO userTO);

    ResponseTO getUsuarios();

    ResponseTO getUsuarioPorEmail(String email);
}

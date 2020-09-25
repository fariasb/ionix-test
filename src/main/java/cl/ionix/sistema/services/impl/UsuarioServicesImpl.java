package cl.ionix.sistema.services.impl;

import cl.ionix.sistema.exception.NotFoundException;
import cl.ionix.sistema.model.User;
import cl.ionix.sistema.repository.UsuarioRepository;
import cl.ionix.sistema.services.UsuarioServices;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicesImpl implements UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseTO creaUsuario(UserTO userTO) {

        User user = User.builder()
                .email(userTO.getEmail())
                .name(userTO.getName())
                .username(userTO.getUsername())
                .number(userTO.getNumber())
                .build();

        user = usuarioRepository.save(user);

        return ResponseTO.builder()
                .responseCode(0)
                .description("Usuario creado")
                .idCreated(user.getId())
                .build();

    }

    @Override
    public ResponseTO getUsuarios() {

        List<User> userList = (List<User>) usuarioRepository.findAll();

        if(userList.isEmpty()){
            throw new NotFoundException("No hay usuarios registrados");
        }

        List<UserTO> userTOS = new ArrayList<>();

        userList.stream().forEach( user ->
            userTOS.add(UserTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .username(user.getUsername())
                    .number(user.getNumber())
                    .build())
        );


        return ResponseTO.builder()
                .responseCode(0)
                .description("Usuarios Encontrados")
                .usuarios(userTOS)
                .build();
    }

    @Override
    public ResponseTO getUsuarioPorEmail(String email) {

        User user = usuarioRepository.findByEmail(email);

        if(user == null){
            throw new NotFoundException("No existe un usuario registrado con email : " + email);
        }
        UserTO userTO = UserTO.builder()
                .id(user.getId())
                .number(user.getNumber())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .build();

        return ResponseTO.builder()
                .responseCode(0)
                .description("Usuario Encontrado")
                .usuario(userTO)
                .build();
    }
}

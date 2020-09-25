package cl.ionix.sistema.services;

import cl.ionix.sistema.exception.NotFoundException;
import cl.ionix.sistema.model.User;
import cl.ionix.sistema.repository.UsuarioRepository;
import cl.ionix.sistema.services.impl.UsuarioServicesImpl;
import cl.ionix.sistema.to.ResponseTO;
import cl.ionix.sistema.to.UserTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    public static final String EMAIL = "email";
    @InjectMocks
    private UsuarioServicesImpl usuarioServices;

    @Mock
    private UsuarioRepository repository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    UserTO userTO;
    User user;
    User userCreated;
    User userNull;
    List<User> usuariosNotEmpty;
    List<User> usuariosEmpty;

    @Before
    public void init() {

        userTO = UserTO.builder().build();
        user = User.builder().build();
        userCreated = User.builder().id(2l).build();
        usuariosNotEmpty = new ArrayList<>();
        usuariosNotEmpty.add(userCreated);
        usuariosEmpty = new ArrayList<>();

    }

    @Test
    public void crearOk() {

        doReturn(userCreated).when(repository).save( any(User.class));

        ResponseTO responseTO = usuarioServices.creaUsuario(userTO);

        assertNotNull(responseTO);
        assertNotNull(responseTO.getIdCreated());
    }

    @Test
    public void getUsuariosOk() {

        doReturn(usuariosNotEmpty).when(repository).findAll();

        ResponseTO responseTO = usuarioServices.getUsuarios();

        assertNotNull(responseTO);
        assertNotNull(responseTO.getUsuarios());
    }

    @Test
    public void getUsuariosNook() {

        doReturn(usuariosEmpty).when(repository).findAll();

        exception.expect(NotFoundException.class);
        ResponseTO responseTO = usuarioServices.getUsuarios();

        assertNotNull(responseTO);

    }

    @Test
    public void getUsuarioPorEmailOk() {

        doReturn(userCreated).when(repository).findByEmail(EMAIL);

        ResponseTO responseTO = usuarioServices.getUsuarioPorEmail(EMAIL);

        assertNotNull(responseTO);
        assertNotNull(responseTO.getUsuario());
    }

    @Test
    public void getUsuarioPorEmailNook() {

        doReturn(userNull).when(repository).findByEmail(EMAIL);

        exception.expect(NotFoundException.class);
        ResponseTO responseTO = usuarioServices.getUsuarioPorEmail(EMAIL);

        assertNotNull(responseTO);

    }

}

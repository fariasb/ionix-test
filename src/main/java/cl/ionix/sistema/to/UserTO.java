package cl.ionix.sistema.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserTO {

    private Long id;
    @NotNull( message = "El nombre no puede ser nulo")
    private String name;
    @NotNull( message = "El nombre de usuario no puede ser nulo")
    private String username;
    private String email;
    @Size(min=0, max=10, message = "El numero no puede tener más de 10 digitos")
    private Integer number;
}

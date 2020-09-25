package cl.ionix.sistema.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class ResponseTO {
    private Integer responseCode;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idCreated;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserTO usuario;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserTO> usuarios = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long elapsedTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResultTO result;
}

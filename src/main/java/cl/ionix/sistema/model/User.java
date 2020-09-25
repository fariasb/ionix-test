package cl.ionix.sistema.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name="USUARIO")
@Entity
@Builder
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String username;
    private String email;
    private Integer number;

    public User() {

    }

    public User(Long id,String name, String username, String email, Integer number) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.number = number;
    }
}

package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {

    // Variáveis de Instância
    //================================================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;

    // Construtores
    //================================================================

    public User() {
    }

    public User(String email, String name) {
        if (email == null || name == null) {
            throw new IllegalArgumentException();
        }
        this.email = email;
        this.name = name;
    }

    // Métodos
    //================================================================

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", version=" + version +
            '}';
    }
}

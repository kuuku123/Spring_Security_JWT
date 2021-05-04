package javabrain.spring_security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User
{
    @Id @GeneratedValue
    private Long id;

    private String userName;
    private String password;
    private boolean active;
    private String roles;

    public User(String userName, String password, boolean active, String roles)
    {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User()
    {

    }
}

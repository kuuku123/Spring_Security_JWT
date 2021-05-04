package javabrain.spring_security.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthenticationRequest
{
    private String username;
    private String password;

    public AuthenticationRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}

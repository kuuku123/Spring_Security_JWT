package javabrain.spring_security.controller;

import javabrain.spring_security.SecurityConfigure.JwtUtil;
import javabrain.spring_security.SecurityConfigure.MyUserDetailsService;
import javabrain.spring_security.model.AuthenticationRequest;
import javabrain.spring_security.model.AuthenticationResponse;
import javabrain.spring_security.model.User;
import javabrain.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class HomeController
{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtil jwtUtil;

    @GetMapping("/")
    public String home()
    {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user()
    {
        return ("<h1>Welcome user</h1>");
    }

    @GetMapping("/admin")
    public String admin()
    {
        return ("<h1>Welcome admin</h1>");
    }

    @PostMapping( "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostConstruct
    public void initUser()
    {
        userRepository.save(new User("user","123",true,"ROLE_USER"));
        userRepository.save(new User("admin","123",true,"ROLE_ADMIN"));
    }

}

package javabrain.spring_security.SecurityConfigure;

import javabrain.spring_security.model.User;
import javabrain.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUserName(username).
                orElseThrow(() -> new UsernameNotFoundException("Not found: +" + username));

        MyUserDetails myUserDetails = new MyUserDetails(user);

        return myUserDetails;
    }
}

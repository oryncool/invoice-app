package kz.jusan.invoiceapp.security;

import kz.jusan.invoiceapp.entities.User;
import kz.jusan.invoiceapp.models.UserDetailsImpl;
import kz.jusan.invoiceapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findUserByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user" + username + " is not found");
        return new UserDetailsImpl(user.get().getUsername(), user.get().getPassword());
    }
}

package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.User;
import kg.beaver.warehouse.exceptions.RegistrationException;
import kg.beaver.warehouse.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RegistrationException("User Not Found with username " + username)
        );
        return UserDetailsImpl.build(user);
    }
}

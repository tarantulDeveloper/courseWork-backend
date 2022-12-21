package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.ERole;
import kg.beaver.warehouse.entities.Role;
import kg.beaver.warehouse.entities.User;
import kg.beaver.warehouse.exceptions.RegistrationException;
import kg.beaver.warehouse.payload.request.LoginRequest;
import kg.beaver.warehouse.payload.request.SignUpRequest;
import kg.beaver.warehouse.payload.response.UserInfoResponse;
import kg.beaver.warehouse.repo.RoleRepository;
import kg.beaver.warehouse.repo.UserRepository;
import kg.beaver.warehouse.utis.email.MailSender;
import kg.beaver.warehouse.utis.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceI{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final MailSender mailSender;

    @Override
    public User createUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RegistrationException("User with the same username already exists");

        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RegistrationException("User with the same email already exists");
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_WORKER)
                .orElseThrow(() -> new RegistrationException("There is no worker role"));
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnable(0);
        user.setActivationCode(UUID.randomUUID().toString());

        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to our system. Visit the link to activate your account: http://localhost:5000/api/auth/activation/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(),"Activation code",message);
        return userRepository.save(user);
    }



    @Override
    public ResponseEntity<?> singIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie  = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));

    }

    @Override
    public ResponseEntity<?> makeAdmin(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_WORKER)
                .orElseThrow(() -> new RegistrationException("There is no worker role"));
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RegistrationException("There is no worker role"));
        roles.add(userRole);
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public String activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        user.setEnable(1);
        userRepository.save(user);
        return "<h1 style='text-align: center; font-size: 40px; margin-top: 100px; color: green;'>" + "Hello, " + user.getUsername() +", your account has been activated</h1>";
    }
}

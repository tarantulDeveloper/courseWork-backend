package kg.beaver.warehouse.controllers;

import kg.beaver.warehouse.entities.User;
import kg.beaver.warehouse.payload.request.LoginRequest;
import kg.beaver.warehouse.payload.request.SignUpRequest;
import kg.beaver.warehouse.payload.response.MessageResponse;
import kg.beaver.warehouse.services.UserServiceI;
import kg.beaver.warehouse.utis.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceI userService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userService.singIn(loginRequest);
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody SignUpRequest signUpRequest) {
        return userService.createUser(signUpRequest);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }



    @PostMapping("/make-admin")
    public ResponseEntity<?> makeAdmin(@RequestBody SignUpRequest signUpRequest){
        return userService.makeAdmin(signUpRequest);
    }

    @GetMapping("/activation/{activation}")
    public String activateUser(@PathVariable(value = "activation") String code) {
        return userService.activateUser(code);
    }
}

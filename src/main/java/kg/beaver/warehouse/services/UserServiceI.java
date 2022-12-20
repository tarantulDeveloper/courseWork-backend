package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.User;
import kg.beaver.warehouse.payload.request.LoginRequest;
import kg.beaver.warehouse.payload.request.SignUpRequest;
import kg.beaver.warehouse.payload.response.UserInfoResponse;
import org.springframework.http.ResponseEntity;

public interface UserServiceI {
    User createUser(SignUpRequest signUpRequest);
    ResponseEntity<?> singIn(LoginRequest loginRequest);
    ResponseEntity<?> makeAdmin(SignUpRequest signUpRequest);
}

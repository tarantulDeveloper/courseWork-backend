package kg.beaver.warehouse.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public content";
    }

    @GetMapping("/only-user")
    @PreAuthorize("hasRole('WORKER')")
    public String userAccess() {
        return "User content";
    }

    @GetMapping("/only-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin access";
    }
}

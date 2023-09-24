package br.com.fiap.pizzaria.controller;

import br.com.fiap.pizzaria.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user and generate a JWT token
        String jwt;
        jwt = jwtUtil.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    private static class JwtResponse {
        public JwtResponse(String jwt) {
        }
    }

    private class LoginRequest {
        private String username;

        public String getUsername() {
            return username;
        }
    }

    // Other controller methods using JWT for authentication/authorization
}

package fact.it.angular.controller;

import fact.it.angular.Model.Role;
import fact.it.angular.Model.User;
import fact.it.angular.repository.UserRepository;
import fact.it.angular.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import fact.it.angular.dto.RegistrationRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) { // simpele check (later hash check!)
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Check if user already exists
        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Email already in use");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // Create new user
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setPhone(registrationRequest.getPhone());
        user.setRole(registrationRequest.getRole() != null ? registrationRequest.getRole() : Role.USER);

        // Save user
        user = userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail());

        // Return token and user info
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }
}

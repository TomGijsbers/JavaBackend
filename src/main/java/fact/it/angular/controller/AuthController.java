package fact.it.angular.controller;

import fact.it.angular.Model.User;
import fact.it.angular.repository.UserRepository;
import fact.it.angular.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) { // simpele check (later hash check!)
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

package fact.it.angular.dto;

import fact.it.angular.Model.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Role role;
}
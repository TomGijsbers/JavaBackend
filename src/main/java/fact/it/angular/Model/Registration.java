package fact.it.angular.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)  // één user per registration
    private User user;

    @ManyToOne(optional = false)
    private Event event;
}


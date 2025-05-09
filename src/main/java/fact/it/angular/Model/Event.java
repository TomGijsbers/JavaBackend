package fact.it.angular.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Registration> registrations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;



}

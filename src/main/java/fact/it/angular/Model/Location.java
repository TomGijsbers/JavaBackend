package fact.it.angular.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Event> events = new ArrayList<>();
}



package fact.it.angular.controller;

import fact.it.angular.Model.Location;
import fact.it.angular.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class RestLocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }
}

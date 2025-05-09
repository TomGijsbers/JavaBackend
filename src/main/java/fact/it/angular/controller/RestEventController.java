package fact.it.angular.controller;

import fact.it.angular.Model.Event;
import fact.it.angular.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class RestEventController {
    @Autowired
    private EventRepository eventRepository;


    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

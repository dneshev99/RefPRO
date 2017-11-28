package neshev.dimitar.project.Models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Log {
    @Id
    String ID;

    private List<Event> events = new ArrayList<>();

    public Log(List<Event> events) {
        this.events = events;
    }

    public Log() {};

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getElementByNumber(int index) {
        return events.get(index);
    }

    public List<Event> getEventsByType(String filter) {
        return events.stream().filter(event -> event.getType().equals(filter)).collect(Collectors.toList());
    }

    public List<Event> getEventsByTeam(String filter) {
        return events.stream().filter(event -> event.getTeam().equals(filter)).collect(Collectors.toList());
    }

}

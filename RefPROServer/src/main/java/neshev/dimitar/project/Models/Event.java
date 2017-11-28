package neshev.dimitar.project.Models;

import org.springframework.data.annotation.Id;

public class Event {
    @Id
    String ID;

    private String type;
    private String team;

    public Event(String type, String team) {
        this.type = type;
        this.team = team;
    }

    public Event() {
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }
}

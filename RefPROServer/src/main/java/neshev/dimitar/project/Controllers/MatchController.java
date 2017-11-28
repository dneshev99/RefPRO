package neshev.dimitar.project.Controllers;

import neshev.dimitar.project.Models.Match;
import neshev.dimitar.project.Repositories.MatchRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/Match")
public class MatchController {
    private MatchRepository matchRepository;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create() {
        return "";
    }
}

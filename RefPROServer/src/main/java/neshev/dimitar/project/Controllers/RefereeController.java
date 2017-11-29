package neshev.dimitar.project.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import neshev.dimitar.project.Models.Referee;
import neshev.dimitar.project.Repositories.RefereeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Referee")
public class RefereeController {
    private final GsonBuilder builder = new GsonBuilder();
    private final Gson gson = builder.create();

    @Autowired
    private RefereeRepository refereeRepository;

    @RequestMapping(value = "/getReferees", method = RequestMethod.GET)
    public String getReferees(){
        String result = null;

            List<Referee> referees = refereeRepository.findAll();
            if (!referees.isEmpty()) {
                result = gson.toJson(referees);
            } else {
                result = "sasda";
            }

        return result;
    }
}

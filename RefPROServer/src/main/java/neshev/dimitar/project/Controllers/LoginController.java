package neshev.dimitar.project.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import neshev.dimitar.project.DTOs.UserDTO;
import neshev.dimitar.project.Models.LoginErrorPOJO;
import neshev.dimitar.project.Models.User;
import neshev.dimitar.project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    private final GsonBuilder builder = new GsonBuilder();
    private final Gson gson = builder.create();

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ResponseEntity<String> checkLogin(@RequestBody UserDTO userDTO) {
        LoginErrorPOJO error = new LoginErrorPOJO();

        User check = userRepository.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());

        if (check == null) {
            error.setErrorMessage("Wrong username or password");
            return new ResponseEntity<String>(gson.toJson(error),HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(check),HttpStatus.OK);
        }

    }
}

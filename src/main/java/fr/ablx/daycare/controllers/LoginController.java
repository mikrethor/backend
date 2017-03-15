package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.User;
import fr.ablx.daycare.jpa.UserRepository;
import fr.ablx.daycare.objects.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableWebMvc
public class LoginController extends MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody Credentials credentials) throws DayCareException {
        User user = userRepository.findUserByLogin(credentials.getLogin());
        // TODO check password and send back token
        if (user == null) {
            throw new DayCareException(String.format("Login %s not found", credentials.getLogin()));
        }

        return user;
    }

    @PostMapping("/logout")
    public Boolean logout(@RequestBody String login) throws DayCareException {

        logger.info("Logout : {}", login);

        throw new DayCareException("TEST JSON");
    }

}

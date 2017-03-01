package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController extends MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public Boolean login(@RequestBody String login) throws DayCareException {

        logger.info("Login : {}", login);
        return true;
    }

    @PostMapping("/logout")
    public Boolean logout(@RequestBody String logout) throws DayCareException {

        logger.info("Logout : {}", logout);
//        return true;
        throw new DayCareException("TEST JSON");
    }
}

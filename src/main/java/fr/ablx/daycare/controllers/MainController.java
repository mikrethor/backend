package fr.ablx.daycare.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.objects.DefaultErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler({DayCareException.class})
    public void handleException(HttpServletRequest request, HttpServletResponse response, DayCareException te)
            throws IOException {
        List<String> errors = new ArrayList<String>();
        errors.add(te.getMessage());
        logger.error(te.getMessage(), te);
        DefaultErrorMessage dem = new DefaultErrorMessage("001", "Erreur", errors);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "nope";
        List<DefaultErrorMessage> dems = new ArrayList<DefaultErrorMessage>();
        dems.add(dem);
        try {
            json = ow.writeValueAsString(dems);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        response.getWriter().write(json);
    }
}

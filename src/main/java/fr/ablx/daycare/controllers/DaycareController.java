package fr.ablx.daycare.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.DayCareRepository;
import fr.ablx.daycare.jpa.DaySumup;
import fr.ablx.daycare.jpa.Daycare;
import fr.ablx.daycare.objects.DefaultErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DaycareController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DayCareRepository dayCareRepo;

    @RequestMapping("/daycares/{id}")
    public Daycare daycare(@PathVariable Long id) {
        return dayCareRepo.findOne(id);
    }

    @RequestMapping("/daycares")
    public List<Daycare> daycares() {
        List<Daycare> daycares = new ArrayList<>();
        dayCareRepo.findAll().forEach(entity -> daycares.add(entity));
        return daycares;
    }

    @RequestMapping("/testError")
    public DaySumup testError() throws DayCareException {
        throw new DayCareException("test eerreur");
    }

    @DeleteMapping("/daycares/{id}")
    public void deleteDaycare(@PathVariable Long id) throws DayCareException {
        try {
            dayCareRepo.delete(id);
        } catch (Exception e) {
            throw new DayCareException("Error when deleting daycare with id " + id);
        }
    }

    @DeleteMapping("/daycares")
    public void deleteDaycares() throws DayCareException {
        try {
            for (Daycare daycare : dayCareRepo.findAll()) {
                dayCareRepo.delete(daycare);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DayCareException("Error when deleting daycares");
        }
    }

    @PostMapping("/daycares")
    public ResponseEntity<?> createDaycare(@RequestBody Daycare daycare) throws DayCareException {
        try {
            Daycare daycare2 = dayCareRepo.save(daycare);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(daycare2.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            logger.error("Error when creating daycare!", e);
            return ResponseEntity.noContent().build();
        }
    }

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

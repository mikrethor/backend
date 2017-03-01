package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.DayCareRepository;
import fr.ablx.daycare.jpa.DaySumup;
import fr.ablx.daycare.jpa.Daycare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DaycareController extends AbstractController<Daycare> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DayCareRepository dayCareRepo;


    @RequestMapping("/testError")
    public DaySumup testError() throws DayCareException {
        throw new DayCareException("test eerreur");
    }

    @Override
    public CrudRepository<Daycare, Long> getRepository() {
        return dayCareRepo;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @RequestMapping("/daycares/{id}")
    @Override
    public Daycare getElementById(@PathVariable Long id) {
        return super.getElementById(id);
    }

    @RequestMapping("/daycares")
    @Override
    public List<Daycare> getAllElements() {
        return super.getAllElements();
    }

    @DeleteMapping("/daycares/{id}")
    @Override
    public void delete(@PathVariable Long id) throws DayCareException {
        super.delete(id);
    }

    @DeleteMapping("/daycares")
    @Override
    public void deleteAll() throws DayCareException {
        super.deleteAll();
    }


    @PostMapping("/daycares")
    @Override
    public ResponseEntity<?> create(@RequestBody Daycare daycare) throws DayCareException {
        return super.create(daycare);
    }
}

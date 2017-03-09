package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.Parent;
import fr.ablx.daycare.jpa.ParentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ParentController extends AbstractController<Parent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ParentRepository parentRepo;


    @Override
    public CrudRepository<Parent, Long> getRepository() {
        return parentRepo;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @RequestMapping("/daycares/{idDaycare}/parents/{idParent}")
    public Parent getElementById(@PathVariable Long idParent) {
        return super.getElementById(idParent);
    }

    @RequestMapping("/daycares/{idDaycare}/parents")
    public List<Parent> getAllElements() {
        return super.getAllElements();
    }

    @DeleteMapping("/daycares/{idDaycare}/parents/{idParent}")
    public void delete(@PathVariable Long idParent) throws DayCareException {
        super.delete(idParent);
    }

    @DeleteMapping("/daycares/{idDaycare}/parents/")
    public void deleteAll() throws DayCareException {
        super.deleteAll();
    }


    @PostMapping("/daycares/{idDaycare}/parents")
    public ResponseEntity<?> create(@RequestBody Parent parent) throws DayCareException {
        return super.create(parent);
    }
}

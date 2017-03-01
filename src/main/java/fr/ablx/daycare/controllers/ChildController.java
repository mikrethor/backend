package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.Child;
import fr.ablx.daycare.jpa.ChildRepository;
import fr.ablx.daycare.jpa.Parent;
import fr.ablx.daycare.jpa.ParentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ChildController extends MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ChildRepository childRepo;

    @Autowired
    ParentRepository parentRepo;

    @RequestMapping("/daycares/{id}/childs")
    public List<Child> getChildren(@PathVariable Long id) {
        List<Child> children = new ArrayList<>();

        childRepo.findAll().forEach(entity -> children.add(entity));

        return children;
    }

    @RequestMapping("/daycares/{idDaycare}/parents/{idParent}/childs")
    public List<Child> getChildren(@PathVariable Long idDaycare, @PathVariable Long idParent) {
        Parent parent = parentRepo.findParentByIdAndDaycare(idDaycare, idParent);
        return parent.getChildren();
    }


    @PostMapping("/daycares/{idDaycare}/childs")
    public ResponseEntity<?> createChild(@PathVariable("idDaycare") Long idDaycare, @RequestBody Child child) throws DayCareException {
        try {
            child = childRepo.save(child);
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(child, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error when creating daycare!", e);
            return ResponseEntity.noContent().build();
        }
    }
}

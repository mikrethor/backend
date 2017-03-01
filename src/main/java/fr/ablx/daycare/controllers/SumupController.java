package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SumupController extends MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DaySumupRepository sumupRepo;

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups")
    public List<DaySumup> getSumups(@PathVariable Long idDaycare, @PathVariable Long idChild) {

        List<DaySumup> sumups = sumupRepo.findSumupByChildAndDaycare(idDaycare, idChild);

        return sumups;
    }

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups/{sumupDay}")
    public DaySumup getSumup(@PathVariable Long idDaycare, @PathVariable Long idChild, @PathVariable String sumupDay) {

        DaySumup sumups = sumupRepo.findOne(1l);

        return sumups;
    }

    @RequestMapping("/moods")
    public Mood[] getMoodValues() {
        return Mood.values();
    }

    @RequestMapping("/appetites")
    public Appetite[] getAppetiteValues() {
        return Appetite.values();
    }

    @RequestMapping("/sleeps")
    public Sleep[] getSleepValues() {
        return Sleep.values();
    }

    @PostMapping("/daycares/{idDaycare}/childs/{idChild}/sumups")
    public ResponseEntity<?> createSumups(@PathVariable("idDaycare") Long idDaycare, @PathVariable("idChild") Long idChild, @RequestBody DaySumup sumup) throws DayCareException {
        try {
            sumup = sumupRepo.save(sumup);
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(sumup, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error when creating sumup!", e);
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups/{idSumup}")
    public DaySumup getSumup(@PathVariable("idSumup") Long idSumup) {
        return sumupRepo.findOne(idSumup);
    }

}

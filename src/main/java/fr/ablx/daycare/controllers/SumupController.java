package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SumupController extends AbstractController<DaySumup> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DaySumupRepository sumupRepo;

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups")
    public List<DaySumup> getSumups(@PathVariable Long idDaycare, @PathVariable Long idChild) {



        return sumupRepo.findSumupByChildAndDaycare(idDaycare, idChild);
    }

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups/day/{sumupDay}")
    public DaySumup getSumup(@PathVariable Long idDaycare, @PathVariable Long idChild, @PathVariable String sumupDay) {



        return sumupRepo.findOne(1L);
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
        return super.create(sumup);
    }

    @RequestMapping("/daycares/{idDaycare}/childs/{idChild}/sumups/{idSumup}")
    public DaySumup getSumup(@PathVariable("idSumup") Long idSumup) {
        return sumupRepo.findOne(idSumup);
    }

    @Override
    public CrudRepository<DaySumup, Long> getRepository() {
        return sumupRepo;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

package fr.ablx.daycare.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EducatorRepository extends CrudRepository<Educator, Long> {
    @Query("SELECT e " + "FROM Educator e " + "WHERE e.id=:idEducator AND e.daycare.id=:idDaycare")
    Educator findEducatorByIdAndDaycare(@Param("idDaycare") Long idDaycare, @Param("idEducator") Long idEducator);
}

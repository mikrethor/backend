package fr.ablx.daycare.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParentRepository extends CrudRepository<Parent, Long> {
    @Query("SELECT p" + " FROM Parent p " + "WHERE p.id=:idParent AND p.daycare.id=:idDaycare")
    Parent findParentByIdAndDaycare(@Param("idDaycare") Long idDaycare, @Param("idParent") Long idParent);
}

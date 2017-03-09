package fr.ablx.daycare.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DaySumupRepository extends CrudRepository<DaySumup, Long> {

	@Query("SELECT ds " + "FROM DaySumup ds " + "WHERE ds.child.id=:idChild AND ds.child.daycare.id=:idDaycare")
	List<DaySumup> findSumupByChildAndDaycare(@Param("idDaycare") Long idDaycare, @Param("idChild") Long idChild);

	@Query("SELECT ds " + "FROM DaySumup ds " + "WHERE ds.child.id=:idChild AND ds.child.daycare.id=:idDaycare AND ds.day=:dateSumup")
	List<DaySumup> findSumupByChildAndDaycareAndDate(@Param("idDaycare") Long idDaycare, @Param("idChild") Long idChild, @Param("dateSumup") Date dateSumup);

}

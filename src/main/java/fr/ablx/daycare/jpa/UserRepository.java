package fr.ablx.daycare.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT us FROM User us WHERE us.login=:login")
    User findUserByLogin(@Param("login") String login);
}

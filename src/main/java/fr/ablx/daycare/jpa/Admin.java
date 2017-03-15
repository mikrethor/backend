package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.serializable.AdminSerializer;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ADMIN")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonSerialize(using = AdminSerializer.class)
public class Admin {

    @Id
    @GeneratedValue(generator = "idAdminGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "idAdminGenerator", sequenceName = "SEQ_ID_ADMIN", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 18)
    private Long id;


}

package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.serializable.ChildSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DAY_SUMUP")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class DaySumup implements Serializable, Element {

    /**
     * SerialUID.
     */
    private static final long serialVersionUID = 2014532075056534270L;

    @Id
    @GeneratedValue(generator = "idDaySumupGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "idDaySumupGenerator", sequenceName = "SEQ_ID_DAY_SUMUP", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 18)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CHILD", referencedColumnName = "ID")
    @JsonSerialize(using = ChildSerializer.class)
    private Child child;

    @Enumerated(EnumType.STRING)
    private Mood mood;

    @Enumerated(EnumType.STRING)
    private Sleep sleep;

    @Enumerated(EnumType.STRING)
    private Appetite appetite;

    private String comment;

    private Date day;
}

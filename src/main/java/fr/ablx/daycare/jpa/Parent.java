package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.deserializable.ParentDeserializer;
import fr.ablx.daycare.serializable.DayCareIdSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PARENT")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonDeserialize(using = ParentDeserializer.class)
public class Parent extends Person implements Element {

    @Id
    @GeneratedValue(generator = "idParentGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "idParentGenerator", sequenceName = "SEQ_ID_PARENT", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 18)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DAYCARE", referencedColumnName = "ID")
    @JsonSerialize(using = DayCareIdSerializer.class)
    private Daycare daycare;

    @ManyToMany
    @JoinTable(
            name = "CHILD_PARENT",
            inverseJoinColumns = @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID"),
            joinColumns = @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID"))
    private List<Child> children;

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }
}

package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.deserializable.ChildDeserializer;
import fr.ablx.daycare.serializable.DayCareIdSerializer;
import fr.ablx.daycare.serializable.ParentsSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CHILD")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonDeserialize(using = ChildDeserializer.class)
public class Child extends Person implements Serializable {

    /**
     * SerialUID.
     */
    private static final long serialVersionUID = 8842875141622051229L;

    @Id
    @GeneratedValue(generator = "idChildGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "idChildGenerator", sequenceName = "SEQ_ID_CHILD", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 18)
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DaySumup> daySumups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DAYCARE_ID", referencedColumnName = "ID")
    @JsonSerialize(using = DayCareIdSerializer.class)
    private Daycare daycare;

    @ManyToMany
    @JoinTable(
            name = "CHILD_PARENT",
            joinColumns = @JoinColumn(name = "CHILD_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID"))
    @JsonSerialize(using = ParentsSerializer.class)
    private List<Parent> parents;

    public Child(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("id:%s, firstName:%s, lastName:%s", id, getFirstName(), getLastName());
    }
}

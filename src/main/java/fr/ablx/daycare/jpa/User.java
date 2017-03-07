package fr.ablx.daycare.jpa;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.ablx.daycare.deserializable.ChildDeserializer;
import fr.ablx.daycare.serializable.DayCareIdSerializer;
import fr.ablx.daycare.serializable.UserSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonSerialize(using = UserSerializer.class)
public class User implements Serializable {

    /**
     * SerialUID.
     */
    private static final long serialVersionUID = 5260341139379272302L;

    @Id
    @GeneratedValue(generator = "idUserGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "idUserGenerator", sequenceName = "SEQ_ID_USER", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 18)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DAYCARE", referencedColumnName = "ID")
    @JsonSerialize(using = DayCareIdSerializer.class)
    @NonNull
    private Daycare daycare;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String salt;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EDUCATOR_ID")
    private Educator educator;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_ID")
    private Parent parent;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADMIN_ID")
    private Admin admin;


}

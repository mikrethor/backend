package fr.ablx.daycare.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.serializable.AdminSerializer;
import fr.ablx.daycare.serializable.UserSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

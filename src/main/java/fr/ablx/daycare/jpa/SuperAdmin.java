package fr.ablx.daycare.jpa;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "SUPER_ADMIN")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class SuperAdmin {

	@Id
	@GeneratedValue(generator = "idSuperAdminGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idSuperAdminGenerator", sequenceName = "SEQ_ID_SUPER_ADMIN", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 18)
	private Long id;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_user")
	@NonNull
	private User user;

}

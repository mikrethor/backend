package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.deserializable.UserDeserializer;
import fr.ablx.daycare.serializable.DayCareIdSerializer;
import fr.ablx.daycare.serializable.UserSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonSerialize(using = UserSerializer.class)
@JsonDeserialize(using = UserDeserializer.class)
@SequenceGenerator(name = "idUserGenerator", sequenceName = "SEQ_ID_USER", allocationSize = 1)
public class User implements Serializable {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = 5260341139379272302L;

	@Id
	@GeneratedValue(generator = "idUserGenerator", strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DAYCARE_ID", referencedColumnName = "ID")
	@JsonSerialize(using = DayCareIdSerializer.class)
	@NonNull
	private Daycare daycare;

	@NonNull
	private String login;

	@NonNull
	private String password;

	@NonNull
	private String salt;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "EDUCATOR_ID")
	private Educator educator;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PARENT_ID")
	private Parent parent;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADMIN_ID")
	private Admin admin;

}

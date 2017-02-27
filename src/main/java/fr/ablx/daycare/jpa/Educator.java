package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.deserializable.ChildDeserializer;
import fr.ablx.daycare.deserializable.EducatorDeserializer;
import fr.ablx.daycare.serializable.DayCareIdSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EDUCATOR")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonDeserialize(using = EducatorDeserializer.class)
public class Educator implements Serializable {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = 992392713980195304L;

	@Id
	@GeneratedValue(generator = "idEducatorGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idEducatorGenerator", sequenceName = "SEQ_ID_EDUCATOR", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 18, scale = 0)
	private Long id;

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DAYCARE", referencedColumnName = "ID")
	@JsonSerialize(using = DayCareIdSerializer.class)
	private Daycare daycare;

}

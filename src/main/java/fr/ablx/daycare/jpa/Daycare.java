package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.ablx.daycare.serializable.DayCareSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DAYCARE")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@JsonSerialize(using = DayCareSerializer.class)
public class Daycare extends Element implements Serializable {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = -3357967634404224531L;

	@Id
	@GeneratedValue(generator = "idDayCareGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idDayCareGenerator", sequenceName = "SEQ_ID_DAYCARE", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 18)
	@JsonView(View.Summary.class)
	private Long id;

	@NonNull
	@JsonView(View.Summary.class)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daycare", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Child> children;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daycare", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Educator> educators;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daycare", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parent> parents;
	public static class View {
		interface Summary {}
	}




}

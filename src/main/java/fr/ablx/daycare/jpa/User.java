package fr.ablx.daycare.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.ablx.daycare.serializable.DayCareIdSerializer;
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
public class User implements Serializable {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = 5260341139379272302L;

	@Id
	@GeneratedValue(generator = "idUserGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "idUserGenerator", sequenceName = "SEQ_ID_USER", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 18, scale = 0)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DAYCARE", referencedColumnName = "ID")
	@JsonSerialize(using = DayCareIdSerializer.class)
	@NonNull
	private Daycare daycare;

	//Ajouter courriel

}

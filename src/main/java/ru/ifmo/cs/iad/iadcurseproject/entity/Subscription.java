package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "subscription", schema = "public")
@ToString
@Getter
@Setter
public class Subscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_id_seq")
	@SequenceGenerator(name = "subscription_id_seq", sequenceName = "subscription_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_who", nullable = false)
	@JsonManagedReference
	private User who;

	@ManyToOne
	@JoinColumn(name = "id_on_whom", nullable = false)
	@JsonManagedReference
	private User onWhom;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

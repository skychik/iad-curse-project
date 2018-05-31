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
@Table(name = "joining_performer", schema = "public")
@ToString
@Getter
@Setter
@Deprecated
public class JoiningPerformer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joining_performer_id_seq")
	@SequenceGenerator(name = "joining_performer_id_seq", sequenceName = "joining_performer_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_performer", nullable = false)
	@JsonManagedReference
	private Performer performer;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "joining_date", nullable = false)
	private Timestamp joiningDate;

	@Column(name = "exit_date", nullable = false)
	private Timestamp exitDate;
}

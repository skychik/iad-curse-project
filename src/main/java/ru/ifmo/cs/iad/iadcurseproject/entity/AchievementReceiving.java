package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "achievement_receiving", schema = "public")
@ToString
@Getter
@Setter
public class AchievementReceiving implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@SequenceGenerator(name = "achievement_receiving_id_seq", sequenceName = "achievement_receiving_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Achievement achievement;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

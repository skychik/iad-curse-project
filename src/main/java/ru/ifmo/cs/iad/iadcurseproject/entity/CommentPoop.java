package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.hateoas.Identifiable;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.Poop;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comment_poop", schema = "public")
@ToString
@Getter
@Setter
public class CommentPoop extends Poop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@SequenceGenerator(name = "comment_poop_id_seq", sequenceName = "comment_poop_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Comment comment;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

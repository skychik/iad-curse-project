package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.Set;

@Entity
@Table(name = "comment", schema = "public")
@ToString(of = {"id", "user", "news", "onCommentId", "content", "creationDate", "alteringDate"})
@Getter
@Setter
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
	@SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	@JsonManagedReference
	private News news;

	@Column(name = "id_on_comment")
	private Long onCommentId;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@OneToMany(mappedBy="comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentLoop> commentLoops;

	@OneToMany(mappedBy="comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentPoop> commentPoops;
}

package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "task_comment", schema = "public")
@ToString(of = {"id", "user", "task", "onCommentId", "content", "creationDate", "alteringDate"})
@Getter
@Setter
public class TaskComment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_comment_id_seq")
	@SequenceGenerator(name = "task_comment_id_seq", sequenceName = "task_comment_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_task", nullable = false)
	@JsonManagedReference
	private CourseTask task;

	@Column(name = "id_on_comment")
	private Long onCommentId;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@OneToMany(mappedBy="taskComment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskCommentLoop> commentLoops;

	@OneToMany(mappedBy="taskComment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskCommentPoop> commentPoops;
}

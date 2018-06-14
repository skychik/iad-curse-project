package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "course_task", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString(of = {"id", "course", "taskTitle", "creationDate", "alteringDate", "content", "contentPreview"})
@Getter
@Setter
public class CourseTask implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_task_id_seq")
	@SequenceGenerator(name = "course_task_id_seq", sequenceName = "course_task_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_course", nullable = false)
	@JsonManagedReference
	private Course course;

	@Column(name = "task_title", nullable = false)
	private String taskTitle;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "content_preview", nullable = false)
	private String contentPreview;

	@OneToMany(mappedBy="task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskLoop> taskLoops;

	@OneToMany(mappedBy="task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskPoop> taskPoops;

	@OneToMany(mappedBy="task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskComment> taskComments;

	@OneToMany(mappedBy="task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskCompletion> taskCompletions;
}

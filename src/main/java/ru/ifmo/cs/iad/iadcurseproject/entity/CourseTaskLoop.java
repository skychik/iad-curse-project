package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "course_task_loop", schema = "public")
@ToString(exclude = {"user", "task"})
@Getter
@Setter
public class CourseTaskLoop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_task_loop_id_seq")
	@SequenceGenerator(name = "course_task_loop_id_seq", sequenceName = "course_task_loop_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_task", nullable = false)
	@JsonManagedReference
	private CourseTask task;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

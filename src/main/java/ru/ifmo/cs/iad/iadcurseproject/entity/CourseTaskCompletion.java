package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "course_task_completion", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Getter
@Setter
public class CourseTaskCompletion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@SequenceGenerator(name = "course_task_completion_id_seq", sequenceName = "course_task_completion_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private CourseTask task;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

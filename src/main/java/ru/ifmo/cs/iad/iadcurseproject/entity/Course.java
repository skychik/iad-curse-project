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
@Table(name = "course", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString(exclude = {"author", "tasks", "subscriptions"})
@Getter
@Setter
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
	@SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User author;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "type")
	private String type;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<CourseTask> tasks;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<CourseSubscription> subscriptions;
}


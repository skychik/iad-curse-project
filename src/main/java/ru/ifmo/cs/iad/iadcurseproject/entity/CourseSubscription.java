package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "course_subscription", schema = "public")
@ToString
@Getter
@Setter
public class CourseSubscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_subscription_id_seq")
	@SequenceGenerator(name = "course_subscription_id_seq", sequenceName = "course_subscription_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_course", nullable = false)
	@JsonManagedReference
	private Course course;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

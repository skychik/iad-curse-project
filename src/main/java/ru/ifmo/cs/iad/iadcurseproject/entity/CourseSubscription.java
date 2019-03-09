package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "course_subscription", schema = "public")
@ToString(of = {"id", "date"})
@Getter
@Setter
public class CourseSubscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@SequenceGenerator(name = "course_subscription_id_seq", sequenceName = "course_subscription_id_seq")
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Course course;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

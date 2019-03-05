// TODO: soundcloud
// TODO: facebook or smth
// TODO: telegram
package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user", schema = "public")
@ToString(of = {"id", "username", "password", "firstName", "surname", "patronymic", "birthDate", "sex", "photo"})
@Getter
@Setter
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "login", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "patronymic")
	private String patronymic; // отчество

	@Column(name = "birth_date")
	private Timestamp birthDate;

	@Column(name = "email")
	private String email;

	@Column(name = "sex")
	private Boolean sex; // if false then боевой вертолет Apache

	@Column(name = "photo_addr")
	private String photo;

	@Column(name = "creation_date")
	private Timestamp creationDate;

//	@Column(name = "email")
//	private String email;

//	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<AchievementReceiving> achievementReceivings;
//
//	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<JoiningPerformer> joiningPerformers;

	// ------------------------ NewsSet ------------------------

	@OneToMany(mappedBy="author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<News> newsSet;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<NewsLoop> newsLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<NewsPoop> newsPoops;

	// ------------------------ Comments ------------------------

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<Comment> comments;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentLoop> commentLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentPoop> commentPoops;

	// ------------------------ Reposts ------------------------

//	@OneToMany(mappedBy="author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<Repost> reposts;
//
//	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<RepostLoop> repostLoops;
//
//	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<RepostPoop> repostPoops;

	// ------------------------ Subscription ------------------------

	@OneToMany(mappedBy="who", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<Subscription> subscriptions;

	@OneToMany(mappedBy="onWhom", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<Subscription> subscribers;

	// ------------------------ Course ------------------------

	@OneToMany(mappedBy="author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<Course> courses;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseSubscription> courseSubscriptions;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskLoop> courseTaskLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskPoop> courseTaskPoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskComment> taskComments;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskCommentLoop> taskCommentLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<TaskCommentPoop> taskCommentPoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CourseTaskCompletion> courseTaskCompletions;
}

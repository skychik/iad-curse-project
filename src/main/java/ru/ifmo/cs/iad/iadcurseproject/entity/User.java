// TODO: soundcloud
// TODO: facebook or smth
// TODO: telegram
package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false)
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "first_name", nullable = false)
	private String firstName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "surname", nullable = false)
	private String surname;
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "patronymic")
	private String patronymic; // отчество
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}


	@Column(name = "birth_date")
	private Timestamp birthDate;
	public Timestamp getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "sex")
	private boolean sex;
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}


	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<AchievementReceiving> achievementReceivings;
	public Set<AchievementReceiving> getAchievementReceivings() {
		return achievementReceivings;
	}
	public void setAchievementReceivings(Set<AchievementReceiving> achievementReceivings) {
		this.achievementReceivings = achievementReceivings;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<JoiningPerformer> joiningPerformers;
	public Set<JoiningPerformer> getJoiningPerformers() {
		return joiningPerformers;
	}
	public void setJoiningPerformers(Set<JoiningPerformer> joiningPerformers) {
		this.joiningPerformers = joiningPerformers;
	}

	// ------------------------ NewsSet ------------------------

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<News> newsSet;
	public Set<News> getNewsSet() {
		return newsSet;
	}
	public void setNewsSet(Set<News> newsSet) {
		this.newsSet = newsSet;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsLoop> newsLoops;
	public Set<NewsLoop> getNewsLoops() {
		return newsLoops;
	}
	public void setNewsLoops(Set<NewsLoop> newsLoops) {
		this.newsLoops = newsLoops;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsPoop> newsPoops;
	public Set<NewsPoop> getNewsPoops() {
		return newsPoops;
	}
	public void setNewsPoops(Set<NewsPoop> newsPoops) {
		this.newsPoops = newsPoops;
	}

	// ------------------------ Comments ------------------------

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Comment> comments;
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentLoop> commentLoops;
	public Set<CommentLoop> getCommentLoops() {
		return commentLoops;
	}
	public void setCommentLoops(Set<CommentLoop> commentLoops) {
		this.commentLoops = commentLoops;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentPoop> commentPoops;
	public Set<CommentPoop> getCommentPoops() {
		return commentPoops;
	}
	public void setCommentPoops(Set<CommentPoop> commentPoops) {
		this.commentPoops = commentPoops;
	}

	// ------------------------ Reposts ------------------------

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Repost> reposts;
	public Set<Repost> getReposts() {
		return reposts;
	}
	public void setReposts(Set<Repost> reposts) {
		this.reposts = reposts;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostLoop> repostLoops;
	public Set<RepostLoop> getRepostLoops() {
		return repostLoops;
	}
	public void setRepostLoops(Set<RepostLoop> repostLoops) {
		this.repostLoops = repostLoops;
	}

	@OneToMany(mappedBy="id_user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostPoop> repostPoops;
	public Set<RepostPoop> getRepostPoops() {
		return repostPoops;
	}
	public void setRepostPoops(Set<RepostPoop> repostPoops) {
		this.repostPoops = repostPoops;
	}

	// ------------------------ Subscribition ------------------------

	@OneToMany(mappedBy="id_who", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<User> subscriptions;
	public Set<User> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Set<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@OneToMany(mappedBy="id_on_whom", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<User> subscribers;
	public Set<User> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}
}

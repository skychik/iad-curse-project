// TODO: soundcloud
// TODO: facebook or smth
// TODO: telegram
package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "login", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "patronymic")
	private String patronymic; // отчество

	@Column(name = "birth_date")
	private Timestamp birthDate;

	@Column(name = "sex")
	private boolean sex;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<AchievementReceiving> achievementReceivings;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<JoiningPerformer> joiningPerformers;

	// ------------------------ NewsSet ------------------------

	@OneToMany(mappedBy="author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<News> newsSet;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsLoop> newsLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsPoop> newsPoops;

	// ------------------------ Comments ------------------------

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Comment> comments;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentLoop> commentLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentPoop> commentPoops;

	// ------------------------ Reposts ------------------------

	@OneToMany(mappedBy="author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Repost> reposts;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostLoop> repostLoops;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostPoop> repostPoops;

	// ------------------------ Subscription ------------------------

	@OneToMany(mappedBy="who", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Subscription> subscriptions;

	@OneToMany(mappedBy="onWhom", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Subscription> subscribers;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public Timestamp getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Set<AchievementReceiving> getAchievementReceivings() {
		return achievementReceivings;
	}
	public void setAchievementReceivings(Set<AchievementReceiving> achievementReceivings) {
		this.achievementReceivings = achievementReceivings;
	}
	public Set<JoiningPerformer> getJoiningPerformers() {
		return joiningPerformers;
	}
	public void setJoiningPerformers(Set<JoiningPerformer> joiningPerformers) {
		this.joiningPerformers = joiningPerformers;
	}
	public Set<News> getNewsSet() {
		return newsSet;
	}
	public void setNewsSet(Set<News> newsSet) {
		this.newsSet = newsSet;
	}
	public Set<NewsLoop> getNewsLoops() {
		return newsLoops;
	}
	public void setNewsLoops(Set<NewsLoop> newsLoops) {
		this.newsLoops = newsLoops;
	}
	public Set<NewsPoop> getNewsPoops() {
		return newsPoops;
	}
	public void setNewsPoops(Set<NewsPoop> newsPoops) {
		this.newsPoops = newsPoops;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<CommentLoop> getCommentLoops() {
		return commentLoops;
	}
	public void setCommentLoops(Set<CommentLoop> commentLoops) {
		this.commentLoops = commentLoops;
	}
	public Set<CommentPoop> getCommentPoops() {
		return commentPoops;
	}
	public void setCommentPoops(Set<CommentPoop> commentPoops) {
		this.commentPoops = commentPoops;
	}
	public Set<Repost> getReposts() {
		return reposts;
	}
	public void setReposts(Set<Repost> reposts) {
		this.reposts = reposts;
	}
	public Set<RepostLoop> getRepostLoops() {
		return repostLoops;
	}
	public void setRepostLoops(Set<RepostLoop> repostLoops) {
		this.repostLoops = repostLoops;
	}
	public Set<RepostPoop> getRepostPoops() {
		return repostPoops;
	}
	public void setRepostPoops(Set<RepostPoop> repostPoops) {
		this.repostPoops = repostPoops;
	}
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Set<Subscription> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(Set<Subscription> subscribers) {
		this.subscribers = subscribers;
	}
}

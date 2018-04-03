package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "comment", schema = "public")
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	@JsonManagedReference
	private News news;

	@Column(name = "id_on_comment")
	private Long onCommentId;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@OneToMany(mappedBy="comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentLoop> commentLoops;

	@OneToMany(mappedBy="comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<CommentPoop> commentPoops;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Long getOnCommentId() {
		return onCommentId;
	}
	public void setOnCommentId(Long onCommentId) {
		this.onCommentId = onCommentId;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getAlteringDate() {
		return alteringDate;
	}
	public void setAlteringDate(Timestamp alteringDate) {
		this.alteringDate = alteringDate;
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
}

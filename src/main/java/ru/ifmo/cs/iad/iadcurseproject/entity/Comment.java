package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
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

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	private long newsId;
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	@Column(name = "id_on_comment")
	private long onCommentId;
	public long getOnCommentId() {
		return onCommentId;
	}
	public void setOnCommentId(long onCommentId) {
		this.onCommentId = onCommentId;
	}

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "altering_date")
	private Timestamp alteringDate;
	public Timestamp getAlteringDate() {
		return alteringDate;
	}
	public void setAlteringDate(Timestamp alteringDate) {
		this.alteringDate = alteringDate;
	}

	@OneToMany(mappedBy="id_comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentLoop> commentLoops;
	public Set<CommentLoop> getCommentLoops() {
		return commentLoops;
	}
	public void setCommentLoops(Set<CommentLoop> commentLoops) {
		this.commentLoops = commentLoops;
	}

	@OneToMany(mappedBy="id_comment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CommentPoop> commentPoops;
	public Set<CommentPoop> getCommentPoops() {
		return commentPoops;
	}
	public void setCommentPoops(Set<CommentPoop> commentPoops) {
		this.commentPoops = commentPoops;
	}
}

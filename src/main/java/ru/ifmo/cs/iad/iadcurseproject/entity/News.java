package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
public class News implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic
	@Column(name = "id", nullable = false)
	private Long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "id_user", nullable = false)
	private long authorId;
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	@Basic
	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Basic
	@Column(name = "altering_date", nullable = false)
	private Timestamp alteringDate;
	public Timestamp getAlteringDate() {
		return alteringDate;
	}
	public void setAlteringDate(Timestamp alteringDate) {
		this.alteringDate = alteringDate;
	}

//	@Basic
//	@Column(name = "comment_on", nullable = false)
//	private long commentOn;
//	public long getCommentOn() {
//		return commentOn;
//	}
//	public void setCommentOn(long commentOn) {
//		this.commentOn = commentOn;
//	}
}

package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comment_loop")
public class CommentLoop implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_comment", nullable = false)
	private long commentId;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
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

	@Column(name = "date", nullable = false)
	private Timestamp date;
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}

package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "repost_poop")
public class RepostPoop implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_repost", nullable = false)
	private long repostId;
	public long getRepostId() {
		return repostId;
	}
	public void setRepostId(long repostId) {
		this.repostId = repostId;
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

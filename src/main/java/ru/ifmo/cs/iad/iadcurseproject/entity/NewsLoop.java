package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news_loop")
public class NewsLoop implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	private long newsId;
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
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

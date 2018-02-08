package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "repost")
public class Repost implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

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

	@OneToMany(mappedBy="id_repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostLoop> repostLoops;
	public Set<RepostLoop> getRepostLoops() {
		return repostLoops;
	}
	public void setRepostLoops(Set<RepostLoop> repostLoops) {
		this.repostLoops = repostLoops;
	}

	@OneToMany(mappedBy="id_repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostPoop> repostPoops;
	public Set<RepostPoop> getRepostPoops() {
		return repostPoops;
	}
	public void setRepostPoops(Set<RepostPoop> repostPoops) {
		this.repostPoops = repostPoops;
	}
}

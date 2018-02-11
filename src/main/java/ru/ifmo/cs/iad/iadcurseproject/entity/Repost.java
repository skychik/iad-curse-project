package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "repost", schema = "public")
public class Repost extends Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	private News news;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	@OneToMany(mappedBy="repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostLoop> repostLoops;

	@OneToMany(mappedBy="repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<RepostPoop> repostPoops;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
}

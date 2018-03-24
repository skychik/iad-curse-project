package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "news", schema = "public")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User author;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@Column(nullable = false) // TODO: nullable for columns, fetch type, cascade
	private Set<Comment> comments;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsLoop> newsLoops;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsPoop> newsPoops;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Repost> reposts;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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
	public Set<Repost> getReposts() {
		return reposts;
	}
	public void setReposts(Set<Repost> reposts) {
		this.reposts = reposts;
	}
}

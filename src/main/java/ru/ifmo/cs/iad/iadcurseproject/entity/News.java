package ru.ifmo.cs.iad.iadcurseproject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "news")
public class News implements Serializable {
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
	@Column(name = "id_user", nullable = false)
	private long authorId;
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
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

	@Column(name = "content", nullable = false)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@OneToMany(mappedBy="id_news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@Column(nullable = false) // TODO: nullable for columns, fetch type, cascade
	private Set<Comment> comments;
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy="id_news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsLoop> newsLoops;
	public Set<NewsLoop> getNewsLoops() {
		return newsLoops;
	}
	public void setNewsLoops(Set<NewsLoop> newsLoops) {
		this.newsLoops = newsLoops;
	}

	@OneToMany(mappedBy="id_news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<NewsPoop> newsPoops;
	public Set<NewsPoop> getNewsPoops() {
		return newsPoops;
	}
	public void setNewsPoops(Set<NewsPoop> newsPoops) {
		this.newsPoops = newsPoops;
	}


	@OneToMany(mappedBy="id_news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Repost> reposts;
	public Set<Repost> getReposts() {
		return reposts;
	}
	public void setReposts(Set<Repost> reposts) {
		this.reposts = reposts;
	}
}

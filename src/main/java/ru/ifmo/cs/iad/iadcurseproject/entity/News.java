package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "news", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class News implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
	@SequenceGenerator(name = "news_id_seq", sequenceName = "news_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User author;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "altering_date")
	private Timestamp alteringDate;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "content_preview", nullable = false)
	private String contentPreview;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
//	@Column(nullable = false) // TODO: nullable for columns, fetch type, cascade
	private Set<Comment> comments;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<NewsLoop> newsLoops;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
	private Set<NewsPoop> newsPoops;

	@OneToMany(mappedBy="news", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonBackReference
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentPreview() {
		return contentPreview;
	}
	public void setContentPreview(String contentPreview) {
		this.contentPreview = contentPreview;
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

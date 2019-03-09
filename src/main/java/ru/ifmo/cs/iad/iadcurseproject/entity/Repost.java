//package ru.ifmo.cs.iad.iadcurseproject.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.ToString;
//import org.springframework.hateoas.Identifiable;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//
//@Entity
//@Table(name = "repost", schema = "public")
//@ToString(exclude = {"news", "author", "repostLoops", "repostPoops"})
//@Deprecated
//public class Repost implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	@JsonManagedReference
//	private News news;
//
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	@JsonManagedReference
//	private User author;
//
//	@OneToMany(mappedBy="repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<RepostLoop> repostLoops;
//
//	@OneToMany(mappedBy="repost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//	@JsonBackReference
//	private Set<RepostPoop> repostPoops;
//
//
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public News getNews() {
//		return news;
//	}
//	public void setNews(News news) {
//		this.news = news;
//	}
//	public User getAuthor() {
//		return author;
//	}
//	public void setAuthor(User author) {
//		this.author = author;
//	}
//	public Set<RepostLoop> getRepostLoops() {
//		return repostLoops;
//	}
//	public void setRepostLoops(Set<RepostLoop> repostLoops) {
//		this.repostLoops = repostLoops;
//	}
//	public Set<RepostPoop> getRepostPoops() {
//		return repostPoops;
//	}
//	public void setRepostPoops(Set<RepostPoop> repostPoops) {
//		this.repostPoops = repostPoops;
//	}
//}

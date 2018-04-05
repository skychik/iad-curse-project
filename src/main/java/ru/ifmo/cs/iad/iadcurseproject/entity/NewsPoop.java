package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.Poop;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news_poop", schema = "public")
@ToString(exclude = {"user", "news"})
public class NewsPoop extends Poop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_news", nullable = false)
	@JsonManagedReference
	private News news;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "date", nullable = false)
	private Timestamp date;


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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}

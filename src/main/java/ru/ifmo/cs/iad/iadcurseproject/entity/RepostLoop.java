//package ru.ifmo.cs.iad.iadcurseproject.entity;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import lombok.ToString;
//import org.springframework.hateoas.Identifiable;
//import ru.ifmo.cs.iad.iadcurseproject.entity.custom.Loop;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "repost_loop", schema = "public")
//@ToString
//@Deprecated
//public class RepostLoop extends Loop implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	private Repost repost;
//
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	private User user;
//
//	@Column(name = "date", nullable = false)
//	private Timestamp date;
//
//
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public Repost getRepost() {
//		return repost;
//	}
//	public void setRepost(Repost repost) {
//		this.repost = repost;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public Timestamp getDate() {
//		return date;
//	}
//	public void setDate(Timestamp date) {
//		this.date = date;
//	}
//}

package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.Poop;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "news_poop", schema = "public")
@ToString(exclude = {"user", "news"})
@Getter
@Setter
public class NewsPoop extends Poop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_poop_id_seq")
	@SequenceGenerator(name = "news_poop_id_seq", sequenceName = "news_poop_id_seq", allocationSize = 1)
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
}

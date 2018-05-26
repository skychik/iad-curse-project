package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;

import java.sql.Timestamp;

@Getter
@Relation(value = "news", collectionRelation = "news")
@ToString
public class NewsDTO {
	private Long id;
	private UserDTO author;
	private String title;
	private String content;
	private Timestamp creationDate;
	private Long commentsNumber;
	private Long loopsNumber;
	private Boolean loopWasPut;
	private Long poopsNumber;
	private Boolean poopWasPut;

	public NewsDTO(){}

	public NewsDTO(News news, Long commentsNumber, Long loopsNumber, boolean loopWasPut, Long poopsNumber, boolean poopWasPut) {
		this.id = news.getId();
		this.author = new UserDTO(news.getAuthor());
		this.title = news.getTitle();
		this.content = news.getContent();
		this.creationDate = news.getCreationDate();
		this.commentsNumber = commentsNumber;
		this.loopsNumber = loopsNumber;
		this.loopWasPut = loopWasPut;
		this.poopsNumber = poopsNumber;
		this.poopWasPut = poopWasPut;
	}
}

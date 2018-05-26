package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "newsForFeed", collectionRelation = "newsForFeed")
@ToString
public class NewsForFeedDTO {
	private Long id;
	private UserDTO author;
	private String title;
	private String contentPreview;
	private Timestamp creationDate;
	private Timestamp alteringDate;
	private Long commentsNumber;
	private Long loopsNumber;
	private Boolean loopWasPut;
	private Long poopsNumber;
	private Boolean poopWasPut;

	public NewsForFeedDTO(News news, Long commentsNumber, Long loopsNumber, boolean loopWasPut, Long poopsNumber, boolean poopWasPut) {
		this.id = news.getId();
		this.author = new UserDTO(news.getAuthor());
		this.title = news.getTitle();
		this.contentPreview = news.getContentPreview();
		this.creationDate = news.getCreationDate();
		this.alteringDate = news.getAlteringDate();
		this.commentsNumber = commentsNumber;
		this.loopsNumber = loopsNumber;
		this.loopWasPut = loopWasPut;
		this.poopWasPut = poopWasPut;
		this.poopsNumber = poopsNumber;
	}
}

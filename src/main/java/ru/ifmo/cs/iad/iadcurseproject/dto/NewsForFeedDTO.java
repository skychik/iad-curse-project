package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;

@Getter
@Relation(value = "news", collectionRelation = "news")
@ToString
public class NewsForFeedDTO {
	private Long id;
	private Long authorId;
	private String authorUsername;
	private String title;
	private String contentPreview;
	private Timestamp creationDate;
	private Timestamp alteringDate;
	private Long commentsNumber;
	private Long loopsNumber;
	private Long poopsNumber;
	private Long repostsNumber;

	public NewsForFeedDTO(News news, Long commentsNumber, Long loopsNumber, Long poopsNumber, Long repostsNumber) {
		this.id = news.getId();
		this.authorId = news.getAuthor().getId();
		this.authorUsername = news.getAuthor().getUsername();
		this.title = news.getTitle();
		this.contentPreview = news.getContentPreview();
		this.creationDate = news.getCreationDate();
		this.alteringDate = news.getAlteringDate();
		this.commentsNumber = commentsNumber;
		this.loopsNumber = loopsNumber;
		this.poopsNumber = poopsNumber;
		this.repostsNumber = repostsNumber;
	}
}
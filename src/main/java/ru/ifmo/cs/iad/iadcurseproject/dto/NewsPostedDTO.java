package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Relation(value = "newsPosted", collectionRelation = "newsPosted")
@ToString
public class NewsPostedDTO {
	private long authorId;
	private String title;
	private String content;

	public NewsPostedDTO() {}

	public News makeNews(User author) {
		News news = new News();
		news.setAuthor(author);
		news.setTitle(title);
		news.setContent(content);
		news.setContentPreview(content);
		news.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return news;
	}
}

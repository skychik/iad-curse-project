package ru.ifmo.cs.iad.iadcurseproject.dto.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

@Projection(name = "newsForFeed", types = News.class)
public interface NewsForFeedProjection /*extends NewsPreviewProjection*/ {
	User getAuthor();
	Long getId();
	String getTitle();
	String getCreationDate();
	String getAlteringDate();
	String getContentPreview();
//	Long getCommentsNumber();
//	Long getLoopsNumber();
//	Long getPoopsNumber();
//	Long getRepostsNumber();
}

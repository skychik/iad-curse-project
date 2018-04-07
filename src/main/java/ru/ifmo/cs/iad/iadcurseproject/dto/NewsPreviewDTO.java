//package ru.ifmo.cs.iad.iadcurseproject.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import org.springframework.hateoas.core.Relation;
//import ru.ifmo.cs.iad.iadcurseproject.dto.projection.NewsPreviewProjection;
//import ru.ifmo.cs.iad.iadcurseproject.entity.User;
//
//@Relation(value = "news", collectionRelation = "news")
//public class NewsPreviewDTO implements NewsPreviewProjection {
//	private User author;
//	private String title;
//	private String creationDate;
//	private String alteringDate;
//	private String contentPreview;
//
//	@Override
//	public User getAuthor() {
//		return author;
//	}
//	public void setAuthor(User author) {
//		this.author = author;
//	}
//
//	@Override
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	@Override
//	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
//	public String getCreationDate() {
//		return creationDate;
//	}
//	public void setCreationDate(String creationDate) {
//		this.creationDate = creationDate;
//	}
//
//	@Override
//	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
//	public String getAlteringDate() {
//		return alteringDate;
//	}
//	public void setAlteringDate(String alteringDate) {
//		this.alteringDate = alteringDate;
//	}
//
//	@Override
//	public String getContent() {
//		return contentPreview;
//	}
//	public void setContentPreview(String contentPreview) {
//		this.contentPreview = contentPreview;
//	}
//}

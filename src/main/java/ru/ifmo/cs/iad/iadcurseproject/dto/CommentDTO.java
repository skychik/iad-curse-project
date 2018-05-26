package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Relation(value = "comment", collectionRelation = "comment")
@ToString
public class CommentDTO {
	private Long id;
	private Long newsId;
	private UserDTO user;
	private List<CommentDTO> comments;
	private String content;
	private Timestamp creationDate;
	private Timestamp alteringDate;
	private Long loopsNumber;
	private Boolean loopWasPut;
	private Long poopsNumber;
	private Boolean poopWasPut;

	public CommentDTO(){}

	public CommentDTO(Comment comment, Long loopsNumber, boolean loopWasPut, Long poopsNumber, boolean poopWasPut) {
		this.id = comment.getId();
		this.newsId = comment.getNews().getId();
		this.user = new UserDTO(comment.getUser());
		this.comments = new ArrayList<>();
		this.content = comment.getContent();
		this.creationDate = comment.getCreationDate();
		this.alteringDate = comment.getAlteringDate();
		this.loopsNumber = loopsNumber;
		this.loopWasPut = loopWasPut;
		this.poopsNumber = poopsNumber;
		this.poopWasPut = poopWasPut;
	}

	public void addComment(CommentDTO comment) {
		comments.add(comment);
	}

	public void sortComments() {
		comments.sort((CommentDTO c1, CommentDTO c2) -> c1.creationDate.before(c2.creationDate) ? 1 : 0);
	}
}

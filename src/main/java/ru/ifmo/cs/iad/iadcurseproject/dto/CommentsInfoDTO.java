package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;

import java.util.List;

@Getter
@Relation(value = "comments_info", collectionRelation = "comments_info")
@ToString
public class CommentsInfoDTO {
	private List<CommentDTO> comments;
	private Long total;

	public CommentsInfoDTO(List<CommentDTO> comments, Long total) {
		this.comments = comments;
		this.total = total;
	}
}

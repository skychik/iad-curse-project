package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;

@Getter
@Relation(value = "id_value", collectionRelation = "id_value")
@ToString
public class IdValueSucceedDTP {
	private Long id;
	private Object value;
	private Boolean succeed;

	public IdValueSucceedDTP(){}

	public IdValueSucceedDTP(Long id, Object value, Boolean succeed) {
		this.id = id;
		this.value = value;
		this.succeed = succeed;
	}
}

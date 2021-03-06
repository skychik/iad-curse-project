package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;

@Getter
@Relation(value = "id_value", collectionRelation = "id_value")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class IdValueSucceedDTO {
	private Long id;
	private Object value;
	private Boolean succeed;
}

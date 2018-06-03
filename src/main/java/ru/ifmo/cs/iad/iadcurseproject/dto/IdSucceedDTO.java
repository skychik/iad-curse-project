package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;

@Getter
@Relation(value = "id_value", collectionRelation = "id_value")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IdSucceedDTO {
	private Long id;
	private Boolean succeed;
}

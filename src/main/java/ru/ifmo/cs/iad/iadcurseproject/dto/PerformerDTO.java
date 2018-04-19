package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Relation(value = "performer", collectionRelation = "performer")
@ToString
public class PerformerDTO {
	private String id;
	private String name;
	private String status;

	public PerformerDTO(Performer performer) {
		this.id = performer.getPerformerId();
		this.name = performer.getName();
		this.status = performer.getStatus();
	}
}

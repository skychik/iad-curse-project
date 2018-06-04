package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;

import java.sql.Timestamp;

@Getter
@Relation(value = "course", collectionRelation = "course")
@ToString
public class CourseInfoDTO {
	private Long id;
	private String title;
	private String type;
	private Timestamp creationDate;

	public CourseInfoDTO (Course course) {
		id = course.getId();
		title = course.getTitle();
		type = course.getType();
		creationDate = course.getCreationDate();
	}
}

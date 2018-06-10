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
	private Long loopsNumber;
	private Long poopsNumber;
	private Double completionRate;
	private Boolean subscribed;

	public CourseInfoDTO (Course course, Long loopsNumber, Long poopsNumber, Double completionRate, Boolean subscribed) {
		id = course.getId();
		title = course.getTitle();
		type = course.getType();
		creationDate = course.getCreationDate();
		this.loopsNumber = loopsNumber;
		this.poopsNumber = poopsNumber;
		this.completionRate = completionRate;
		this.subscribed = subscribed;
	}
}

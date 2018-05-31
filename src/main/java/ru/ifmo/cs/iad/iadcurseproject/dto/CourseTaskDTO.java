package ru.ifmo.cs.iad.iadcurseproject.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTask;

import java.sql.Timestamp;

@Getter
@Relation(value = "course_task", collectionRelation = "course_task")
@ToString
public class CourseTaskDTO {
	private Long id;
	private CourseDTO course;
	private String taskTitle;
	private String content;
	private Timestamp creationDate;
	private Long commentsNumber;
	private Long loopsNumber;
	private Boolean loopWasPut;
	private Long poopsNumber;
	private Boolean poopWasPut;
	private Boolean wasCompleted;

	public CourseTaskDTO(){}

	public CourseTaskDTO(CourseTask courseTask, Long commentsNumber, Long loopsNumber, boolean loopWasPut,
	                     Long poopsNumber, boolean poopWasPut, boolean wasCompleted) {
		this.id = courseTask.getId();
		this.course = new CourseDTO(courseTask.getCourse());
		this.taskTitle = courseTask.getTaskTitle();
		this.content = courseTask.getContent();
		this.creationDate = courseTask.getCreationDate();
		this.commentsNumber = commentsNumber;
		this.loopsNumber = loopsNumber;
		this.loopWasPut = loopWasPut;
		this.poopsNumber = poopsNumber;
		this.poopWasPut = poopWasPut;
		this.wasCompleted = wasCompleted;
	}

}

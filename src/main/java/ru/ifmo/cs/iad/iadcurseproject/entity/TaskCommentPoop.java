package ru.ifmo.cs.iad.iadcurseproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.Loop;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "task_comment_poop", schema = "public")
@ToString(exclude = {"user", "taskComment"})
@Getter
@Setter
public class TaskCommentPoop extends Loop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_comment_poop_id_seq")
	@SequenceGenerator(name = "task_comment_poop_id_seq", sequenceName = "task_comment_poop_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_task_comment", nullable = false)
	@JsonManagedReference
	private TaskComment taskComment;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonManagedReference
	private User user;

	@Column(name = "date", nullable = false)
	private Timestamp date;
}

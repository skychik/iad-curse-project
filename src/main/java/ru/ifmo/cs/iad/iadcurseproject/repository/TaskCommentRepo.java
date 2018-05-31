package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.TaskComment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskCommentRepo extends JpaRepository<TaskComment, Long> {
	@Query("select count(c) from TaskComment c where c.task.id = :id")
	long countAllByTaskId(@Param("id") Long id);
}

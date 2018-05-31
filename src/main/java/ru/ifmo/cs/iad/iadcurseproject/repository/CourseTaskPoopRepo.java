package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTaskLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTaskPoop;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseTaskPoopRepo extends JpaRepository<CourseTaskPoop, Long> {
	CourseTaskPoop getByTaskIdAndUserId(long taskId, long userId);

	@Query("select count(p) from CourseTaskPoop p where p.task.id = :id")
	long countAllByTaskId(@Param("id") Long id);

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("delete from CourseTaskPoop p where p.id = :id")
	void removeById(@Param("id") Long id);
}

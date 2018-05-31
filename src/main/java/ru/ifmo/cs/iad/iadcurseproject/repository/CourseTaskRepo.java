package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTask;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseTaskRepo extends JpaRepository<CourseTask, Long> {
	@Query("select t from Course c, CourseTask t, CourseSubscription s where s.user.id = :userId and s.course = t.course")
	List<CourseTask> getAllForUserId(@Param("userId") long userId, Pageable pagination);

	@Query("select t from CourseTask t where t.id = :taskId")
	CourseTask getOneByTaskId(@Param("taskId") long taskId);
}

package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTaskCompletion;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseTaskLoop;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseTaskCompletionRepo extends JpaRepository<CourseTaskCompletion, Long> {
	CourseTaskCompletion getByTaskIdAndUserId(long taskId, long userId);

	@Modifying
	@Transactional
	@Query("delete from CourseTaskCompletion c where c.id = :id")
	void removeById(@Param("id") Long id);
}

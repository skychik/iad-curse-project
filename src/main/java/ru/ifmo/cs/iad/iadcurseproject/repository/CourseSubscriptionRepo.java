package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseSubscription;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseSubscriptionRepo extends JpaRepository<CourseSubscription, Long> {
	CourseSubscription getByCourseIdAndUserId(long courseId, long userId);

	@Modifying
	@Transactional
	@Query("delete from CourseSubscription s where s.id = :id")
	void removeById(@Param("id") Long id);
}

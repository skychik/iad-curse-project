package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseRepo extends JpaRepository<Course, Long> {
	Course findByAuthorIdAndTitle(long authorId, String title);
}

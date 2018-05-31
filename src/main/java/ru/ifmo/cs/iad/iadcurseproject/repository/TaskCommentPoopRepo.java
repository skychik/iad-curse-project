package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Course;
import ru.ifmo.cs.iad.iadcurseproject.entity.TaskCommentPoop;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskCommentPoopRepo extends JpaRepository<TaskCommentPoop, Long> {
}

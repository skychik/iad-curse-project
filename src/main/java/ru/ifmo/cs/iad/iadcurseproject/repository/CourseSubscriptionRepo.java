package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.CourseSubscription;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseSubscriptionRepo extends JpaRepository<CourseSubscription, Long> {
}

package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
}

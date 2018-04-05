package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepo extends JpaRepository<User, Long> {
	//@RestResource(exported = false)
	List<User> findAllById(Long id);
}

package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "performer", path = "performer")
public interface PerformerRepo extends JpaRepository<Performer, Long> {
	//@RestResource(exported = false)
	@Query("select p from Performer p, JoiningPerformer j where j.user.id = :userId and j.performer = p")
	List<Performer> getAllByUserId(@Param("userId") long userId, Pageable pagination);
}

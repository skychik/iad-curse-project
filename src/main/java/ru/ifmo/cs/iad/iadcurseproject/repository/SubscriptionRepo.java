package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Subscription;

import javax.transaction.Transactional;

//@RepositoryRestResource(collectionResourceRel = "subscription", path = "subscription")
@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
	Subscription findByWhoIdAndOnWhomId(Long whoId, Long onWhomId);

	@Modifying
	@Transactional
	@Query("delete from Subscription s where s.id = :id")
	void removeById(@Param("id") Long id);
}

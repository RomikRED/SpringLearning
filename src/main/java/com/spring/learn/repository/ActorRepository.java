package com.spring.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.learn.domain.Actor;
import com.spring.learn.domain.Film;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long>{
	
	@Transactional
	@Modifying
	@Query("update Actor a set a.deleted=1 where a.id= :actorId")
	void removeActorById(@Param("actorId") long id);
	
	@Transactional
	List<Actor> findByFirstNameLike(String firstName);
	
	@Transactional
	List<Actor> findByDeletedEquals(int deleted);
	
	@Transactional
	List<Film> 	findByFilms(Actor actor);

}

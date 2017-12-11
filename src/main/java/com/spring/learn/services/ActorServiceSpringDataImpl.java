package com.spring.learn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learn.domain.Actor;
import com.spring.learn.domain.Film;
import com.spring.learn.repository.ActorRepository;
import com.spring.learn.repository.DaoExeption;

@Service
public class ActorServiceSpringDataImpl implements ActorService {

	@Autowired
	private ActorRepository actorDao;
	
	@Override
	public Actor getActorById(Long id) throws DaoExeption {
		Actor actor = this.actorDao.findOne(id);
		if(actor==null){
			throw new DaoExeption("No such actor with id="+id);
		}
		return  actor;
	}

	@Override
	public void addActor(Actor actor) throws DaoExeption {
		this.actorDao.save(actor);
	}

	@Override
	public void removeActorById(long id) throws DaoExeption {
		this.actorDao.removeActorById(id);
	}

	@Override
	public void update(Actor actor) throws DaoExeption {
		this.actorDao.save(actor);
	}

	@Override
	public List<Actor> getActorsByName(String name) throws DaoExeption {
		List<Actor> actors = this.actorDao.findByFirstNameLike(name.toUpperCase()+"%");
		return actors;
	}

	@Override
	public List<Actor> getRemovedActors() throws DaoExeption {
		return this.actorDao.findByDeletedEquals(1);
	}

	@Override
	public List<Film> getFilmsByActor(Long actorId) {
		Actor actor = this.actorDao.findOne(actorId);
		return this.actorDao.findByFilms(actor);
	}

}

package com.spring.learn.services;


import java.util.List;

import com.spring.learn.domain.Actor;
import com.spring.learn.domain.Film;
import com.spring.learn.repository.DaoExeption;


public interface ActorService {

	public Actor getActorById(Long id) throws DaoExeption;

	public void addActor(Actor actor) throws DaoExeption;

	public void removeActorById(long id) throws DaoExeption;

	public void update(Actor actor) throws DaoExeption;

	public List<Actor> getActorsByName(String name) throws DaoExeption;
	public List<Actor> getRemovedActors() throws DaoExeption;

//	public List<Actor> getActorsByFilm(Film film);
//
	public List<Film> getFilmsByActor(Long actorId);
}

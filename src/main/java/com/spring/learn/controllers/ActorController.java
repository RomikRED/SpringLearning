package com.spring.learn.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.learn.domain.Actor;
import com.spring.learn.domain.Film;
import com.spring.learn.repository.DaoExeption;
import com.spring.learn.services.ActorService;

@RestController
@RequestMapping("/actors")
public class ActorController {

	private Logger logger = LoggerFactory.getLogger(DaoExeption.class);
	
	@Autowired
	private ActorService actorService;

	@GetMapping(value="/{id}")
	public ResponseEntity<Actor> getActor(@PathVariable("id") Long id) {
		Actor actor;
		try {
			actor = this.actorService.getActorById(id);
		} catch (DaoExeption e) {
			logger.info(e.getMessage());
			return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Actor>(actor, HttpStatus.OK);
	}

	@GetMapping(value = "/all/{name}")
	public ResponseEntity<List<Actor>> getActors(@PathVariable("name") String name) {
		List<Actor> list = new ArrayList<>();
		try {
			list = this.actorService.getActorsByName(name);
		} catch (DaoExeption e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Actor>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Actor>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/films/{id}")
	public ResponseEntity<List<Film>> getFilmsByActor(@PathVariable("id") Long id) {
		List<Film> list = new ArrayList<>();
		try {
			list = this.actorService.getFilmsByActor(id);
		} catch (DaoExeption e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Film>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Film>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/removed")
	public ResponseEntity<List<Actor>> getRemovedActors() {
		List<Actor> list = new ArrayList<>();
		try {
			list = this.actorService.getRemovedActors();
		} catch (DaoExeption e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Actor>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Actor>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/remove/{id}")
	public ResponseEntity<Actor> removeActor(@PathVariable("id") Long id) {
		try {
			this.actorService.removeActorById(id);
		} catch (DaoExeption e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Actor>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<Actor>(HttpStatus.OK);
	}
	
	
//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<String> errorHandler(Exception exc) {
//        logger.error(exc.getMessage(), exc);
//        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
//    }
	
	
	
//	GetMapping(value = "/actor/all/{name}")
}

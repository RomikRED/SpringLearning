package com.spring.learn.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.learn.domain.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {

}

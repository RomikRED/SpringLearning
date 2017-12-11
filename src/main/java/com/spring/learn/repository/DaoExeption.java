package com.spring.learn.repository;

import org.springframework.dao.DataAccessException;

public class DaoExeption extends DataAccessException {

	private static final long serialVersionUID = 1L;

	public DaoExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoExeption(String message) {
		super(message);
	}



}

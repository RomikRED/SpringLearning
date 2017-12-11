package com.spring.learn.domain;

public enum Rating {
	/**
	 * G — General audiences
	 * PG — Parental guidance suggested
	 * PG-13 — Parents strongly cautioned
	 * Restricted
	 * NC-17 only older than 17y
	 */
	G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");

    private String rating;
    Rating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return rating;
    }
}

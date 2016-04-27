package com.flickhunter.web.service;

import java.util.List;

import com.flickhunter.model.Movie;

public interface SearchService {

	List<Movie> searchPhrase(String query);

	Movie getMovie(String movieId);

}

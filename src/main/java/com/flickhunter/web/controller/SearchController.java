package com.flickhunter.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flickhunter.model.Movie;
import com.flickhunter.web.service.SearchService;

@RestController
@RequestMapping("/controller")
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "Hello World!!";
	}
	
	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Movie> searchQuery(@PathVariable(value="term") String query){
		return searchService.searchPhrase(query);
	}
	
	@RequestMapping(value = "/get/{movieId}", method = RequestMethod.GET, produces = "application/json")
	public Movie sayHello(@PathVariable String movieId){
		return searchService.getMovie(movieId);
	}
}

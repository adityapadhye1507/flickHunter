package com.flickhunter.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flickhunter.beans.VectorSpace;
import com.flickhunter.helper.SearchHelper;
import com.flickhunter.model.Movie;
import com.flickhunter.web.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	VectorSpace vectorSpaceModel;

	@Autowired
	SearchHelper searchHelper;

	@Override
	public List<Movie> searchPhrase(String query) {
		String[] terms = query.toLowerCase().split("\\W+");
		List<String> queryTermList = new ArrayList<String>();
		for (String string : terms) {
			if (vectorSpaceModel.searchStopWord(string, vectorSpaceModel.getStopWords()) == -1) {
				queryTermList.add(searchHelper.stemSearchTerm(string));
			}
		}
		List<Movie> movieList = searchHelper.rankSearch(queryTermList);
		return movieList;
	}

	@Override
	public Movie getMovie(String movieId) {
		return vectorSpaceModel.getMoviesMap().get(movieId);
	}

}

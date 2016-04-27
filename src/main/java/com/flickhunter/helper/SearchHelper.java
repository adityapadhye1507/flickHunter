package com.flickhunter.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flickhunter.beans.VectorSpace;
import com.flickhunter.model.Document;
import com.flickhunter.model.Movie;

/**
 * Knowledge Representation Technology Checkpoint 3
 * 
 * @description Search Class Helper: To search different types of queries in
 *              Vector Spcae
 */
@Component
public class SearchHelper {

	@Autowired
	VectorSpace vectorSpaceModel;

	/**
	 * Stem the search words
	 * 
	 * @param String
	 *            token
	 * @return String stemmedWord
	 */
	public String stemSearchTerm(String token) {
		Stemmer st = new Stemmer();
		st.add(token.toCharArray(), token.length());
		st.stem();
		return st.toString();
	}

	public List<Movie> rankSearch(List<String> query) {
		HashMap<Integer, Double> docs = new HashMap<Integer, Double>();

		ArrayList<Document> docList;

		for (String term : query) {
			// stem the word
			Stemmer st = new Stemmer();
			st.add(term.toCharArray(), term.length());
			st.stem();
			term = st.toString();

			int index = vectorSpaceModel.getTermList().indexOf(term);
			if (index < 0)
				continue;
			docList = vectorSpaceModel.getDocLists().get(index);
			System.out.println("Found term:" + term + " Doclist:" + docList);
			double qtfidf = (1 + Math.log10(1)) * Math.log10(vectorSpaceModel.getN() * 1.0 / docList.size());

			Document doc;
			for (int i = 0; i < docList.size(); i++) {
				doc = docList.get(i);
				double score = doc.tw * qtfidf;

				if (!docs.containsKey(doc.docId)) {
					docs.put(doc.docId, score);
				} else {
					score += docs.get(doc.docId);
					docs.put(doc.docId, score);
				}
			}
		}
		docs = (HashMap<Integer, Double>) sortByValue(docs);
		System.out.println(docs);
		List<Movie> movieList = new ArrayList<Movie>();
		for (int movieId : docs.keySet()) {
			Movie movie = vectorSpaceModel.getMoviesMap().get(String.valueOf(movieId));
			if (!movie.getTitle().trim().isEmpty() && !movie.getPlot().trim().isEmpty()) {
				movieList.add(movie);
			}
		}
		return movieList;
	}

	static Map sortByValue(Map map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o2, Object o1) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}

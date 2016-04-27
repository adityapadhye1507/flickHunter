package com.flickhunter.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.flickhunter.helper.Stemmer;
import com.flickhunter.model.Document;
import com.flickhunter.model.Movie;
import com.flickhunter.model.Movies;

/**
 * Knowledge Representation Technology
 * 
 * @description Class to create a vector space model
 * 
 */

@Component
public class VectorSpace {
	// attributes
	private int N;
	private ArrayList<String> termList;
	private ArrayList<ArrayList<Document>> docLists;
	private Movies movies;
	private Map<String, Movie> moviesMap;
	private List<String> stopWords;

	// constructor
	public VectorSpace() {
		System.out.println("Constructor for MovieBean!!!");
		String inputFilePath = Thread.currentThread().getContextClassLoader().getResource("/movies.json")
				.getPath();

		// read all the document into buffers
		File input = new File(inputFilePath);
		System.out.println("Reading Movies!!");

		ObjectMapper mapper = new ObjectMapper();
		try {
			this.movies = (Movies) mapper.readValue(input, Movies.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Creating Vector Space Model!!!");
		// Instantiate the attributes
		String stopWordPath = Thread.currentThread().getContextClassLoader().getResource("/stopwords.txt").getPath();
		stopWords = readStopWords(stopWordPath);
		termList = new ArrayList<String>();

		List<Movie> movieList = movies.getMovies();//.subList(0, 10000);
		moviesMap = new HashMap<String, Movie>();

		docLists = new ArrayList<ArrayList<Document>>();
		ArrayList<Document> docList;

		for (Movie movie : movieList) {
			moviesMap.put(movie.getId(), movie);
			String regex = "\\W+";
			String[] words = movie.toString().toLowerCase().split(regex);
			String word;

			for (int j = 0; j < words.length; j++) {
				boolean match = false;
				word = words[j];
				// stem the word
				Stemmer st = new Stemmer();
				st.add(word.toCharArray(), word.length());
				st.stem();
				word = st.toString();

				// search for stop words
				if (searchStopWord(word, stopWords) == -1) {

					if (!termList.contains(word)) {
						termList.add(word);
						docList = new ArrayList<Document>();
						Document doc = new Document(Integer.valueOf(movie.getId()), 1);
						docList.add(doc);
						docLists.add(docList);
					} else {
						int index = termList.indexOf(word);
						docList = docLists.get(index);

						for (Document did : docList) {
							if (did.docId == Integer.valueOf(movie.getId())) {
								did.tw++;
								match = true;
								break;
							}
						}
						if (!match) {
							Document doc = new Document(Integer.valueOf(movie.getId()), 1);
							docList.add(doc);
						}
					}
				}
			}
		}

		// compute the tf.idf
		N = movieList.size();
		for (int i = 0; i < termList.size(); i++) {
			docList = docLists.get(i);
			int df = docList.size();
			Document document;
			for (int j = 0; j < docList.size(); j++) {
				document = docList.get(j);
				double tfidf = (1 + Math.log10(document.tw)) * Math.log10(N / df * 1.0);
				document.tw = tfidf;
				docList.set(j, document);
			}
		}
		System.gc();
		System.out.println("Vector space model created!!!");
	}

	/**
	 * Returns stop words in a list
	 * 
	 * @param filePath
	 * @return List<String> stopWords
	 */
	static List<String> readStopWords(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		String line = new String();
		try {
			reader = new BufferedReader(new FileReader(file));

			while ((line = reader.readLine()) != null) {
				buffer.append(line.toLowerCase() + " ");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		String[] strings = buffer.toString().split("\\s+");
		List<String> stopWords = Arrays.asList(strings);
		return stopWords;
	}

	public int searchStopWord(String key, List<String> stopWords) {
		int lo = 0;
		int hi = stopWords.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int result = key.compareTo(stopWords.get(mid));
			if (result < 0)
				hi = mid - 1;
			else if (result > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public String toString() {
		String outString = new String();
		ArrayList<Document> docList;
		for (int i = 0; i < 100; i++) {
			outString += String.format("%-15s", termList.get(i));
			docList = docLists.get(i);
			for (int j = 0; j < docList.size(); j++) {
				// outString += docList.get(j) + "\t";
			}
			outString += "\n";
		}
		return outString;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return N;
	}

	/**
	 * @param n
	 *            the n to set
	 */
	public void setN(int n) {
		N = n;
	}

	/**
	 * @return the termList
	 */
	public ArrayList<String> getTermList() {
		return termList;
	}

	/**
	 * @param termList
	 *            the termList to set
	 */
	public void setTermList(ArrayList<String> termList) {
		this.termList = termList;
	}

	/**
	 * @return the docLists
	 */
	public ArrayList<ArrayList<Document>> getDocLists() {
		return docLists;
	}

	/**
	 * @param docLists
	 *            the docLists to set
	 */
	public void setDocLists(ArrayList<ArrayList<Document>> docLists) {
		this.docLists = docLists;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public Map<String, Movie> getMoviesMap() {
		return moviesMap;
	}

	public void setMoviesMap(Map<String, Movie> moviesMap) {
		this.moviesMap = moviesMap;
	}

	public List<String> getStopWords() {
		return stopWords;
	}

	public void setStopWords(List<String> stopWords) {
		this.stopWords = stopWords;
	}
}
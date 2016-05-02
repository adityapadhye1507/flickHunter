package com.flickhunter.model;

import java.util.List;

public class Movie {
	private String id;
	private String title;
	private String plot;
	private List<String> genre;
	private List<String> keywords;
	private List<String> actors;
	private List<String> actoress;
	private List<String> directors;
	private List<String> producers;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the plot
	 */
	public String getPlot() {
		return plot;
	}

	/**
	 * @param plot
	 *            the plot to set
	 */
	public void setPlot(String plot) {
		this.plot = plot;
	}

	/**
	 * @return the genre
	 */
	public List<String> getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	/**
	 * @return the keywords
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the actors
	 */
	public List<String> getActors() {
		return actors;
	}

	/**
	 * @param actors
	 *            the actors to set
	 */
	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	/**
	 * @return the directors
	 */
	public List<String> getDirectors() {
		return directors;
	}

	/**
	 * @param directors
	 *            the directors to set
	 */
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	/**
	 * @return the producers
	 */
	public List<String> getProducers() {
		return producers;
	}

	/**
	 * @param producers
	 *            the producers to set
	 */
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public List<String> getActoress() {
		return actoress;
	}

	public void setActoress(List<String> actoress) {
		this.actoress = actoress;
	}

	@Override
	public String toString() {
		StringBuffer content = new StringBuffer();
		content.append(this.id);
		content.append(" ");
		content.append(this.title);
		content.append(" ");
		content.append(this.plot);
		content.append(" ");
		content.append(this.keywords);
		content.append(" ");
		content.append(this.genre);
		content.append(" ");
		content.append(this.actors);
		content.append(" ");
		content.append(this.actoress);
		content.append(" ");
		content.append(this.directors);
		content.append(" ");
		content.append(this.producers);
		return content.toString();
	}

	
	/*public List<String> getBiWords() {
		List<String> biWords = new ArrayList<String>();
		biWords.addAll(getNames(this.actors));
		biWords.addAll(getNames(this.actoress));
		biWords.addAll(getNames(this.directors));
		biWords.addAll(getNames(this.producers));

		return biWords;
	}*/

	/*public List<String> getNames(List<String> list) {
		List<String> names = new ArrayList<String>();
		StringBuffer name = null;
		if (list.size() != 0) {
			for (String fullName : list) {
				String temp[] = fullName.split("\\W+");
				name = new StringBuffer();
				for (String partName : temp) {
					name.append(partName.toLowerCase() + " ");
				}
			names.add(name.toString().trim());
			}
			return names;
		} else
			return new ArrayList<String>();

	}*/

}

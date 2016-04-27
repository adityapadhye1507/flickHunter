package com.flickhunter.model;

public class Document {
	public int docId;
	public double tw;

	public Document(int did, double tw) {
		this.docId = did;
		this.tw = tw;
	}

	public String toString() {
		String docIdString = docId + ":" + tw;
		return docIdString;

	}
}
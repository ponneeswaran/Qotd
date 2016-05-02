package com.awqotd.dao;

public class RecommenderDetailsVO 
{
	private String url;
	private float score;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
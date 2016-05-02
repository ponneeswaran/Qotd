package com.awqotd.vo;

public class UserKnowledgeVO 
{
	private String tag;
	private double weight;
	private double last_adj_wgt;
	public UserKnowledgeVO(String tag, double weight, double last_adj_wgt)
	{
		this.tag = tag;
		this.weight = weight;
		this.last_adj_wgt = last_adj_wgt;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getLast_adj_wgt() {
		return last_adj_wgt;
	}
	public void setLast_adj_wgt(double last_adj_wgt) {
		this.last_adj_wgt = last_adj_wgt;
	}
}
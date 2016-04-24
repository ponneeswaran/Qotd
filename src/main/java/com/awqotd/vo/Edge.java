/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class Edge {
  private Node source;
	private Node target;
	private double weight;
	
	public Node getSource()
	{
		return this.source;
	}
	public void setSource(Node source)
	{
		this.source = source;
	}
	
	public Node getTarget()
	{
		return this.target;
	}
	public void setTarget(Node target)
	{
		this.target = target;
	}
	
	public double getWeight()
	{
		return this.weight;
	}
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
}

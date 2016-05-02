/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class Edge 
{
	private int source_id;
	private int target_id;
	private Node source;
	private Node target;
	private double weight;

	public Edge(Node source, Node target, double weight)
	{
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	public Edge(int source_id, int target_id, double weight)
	{
		this.source_id = source_id;
		this.target_id = target_id;
		this.weight = weight;
	}
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
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getTarget_id() {
		return target_id;
	}
	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}
}

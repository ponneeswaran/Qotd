/**
 * 
 */
package com.awqotd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.awqotd.vo.Node;
import com.awqotd.vo.Edge;
/**
 * @author Ponneeswaran
 *
 */
public class DAG {
  private static DAG instance;
	
	//private static String _path = "//DAGTags.xml";
	
	private List<Node> _nodes;
	private List<Edge> _edges;
	
	private DAG()
	{
		_nodes = new ArrayList<Node>();
		_edges = new ArrayList<Edge>();
		//TODO: update the above lists by reading the xml file.
	}
	
	public DAG getInstance()
	{
		if(instance == null)
			instance = new DAG();
		return instance;
	}
	
	public List<com.awqotd.vo.Node> getNodes()
	{
		return _nodes;
	}
	
	public List<Edge> getEdges()
	{
		return _edges;
	}
	
	public List<Edge> getOutgoingEdges(Node node)
	{
		List<Edge> outgoingEdges = new ArrayList<Edge>();
		for(Edge edge : _edges)
		{
			if(edge.getSource().getId() == node.getId())
				outgoingEdges.add(edge);
		}
		return outgoingEdges;
	}
	
	public List<Edge> getIncomingEdges(Node node)
	{
		List<Edge> incomingEdges = new ArrayList<Edge>();
		for(Edge edge : _edges)
		{
			if(edge.getTarget().getId() == node.getId())
				incomingEdges.add(edge);
		}
		return incomingEdges;
	}
	
	public List<Node> getChildNodes(Node node)
	{
		List<Node> childNodes = new ArrayList<Node>();
		for(Edge edge : getOutgoingEdges(node))
		{
			childNodes.add(edge.getTarget());
		}
		return childNodes;
	}
	
	public List<Node> getParentNodes(Node node)
	{
		List<Node> parentNodes = new ArrayList<Node>();
		for(Edge edge : getIncomingEdges(node))
		{
			parentNodes.add(edge.getSource());
		}
		return parentNodes;
	}
	
	public Node addNode(Node newNode)
	{
		newNode.setId(_nodes.size());
		_nodes.add(newNode);
		return newNode;
	}
	
	public boolean addEdge(Edge newEdge)
	{
		boolean cycleExists = edgeFormsCycle(newEdge);
		if(!cycleExists)
		{
			_edges.add(newEdge);
			return true;
		}
		else
			return false;
	}
	
	private boolean edgeFormsCycle(Edge newEdge)
	{
		boolean formsCycle = false;
		boolean[] visitedArray = new boolean[_nodes.size()];
		Stack<Node> nodeStack = new Stack<Node>();
		
		//checking if the target of the new edge reaches the source.
		nodeStack.push(newEdge.getTarget());
		while(!nodeStack.empty())
		{
			Node currentNode = nodeStack.pop();
			visitedArray[currentNode.getId()] = true; // set the node visited.
			List<Node> targetNodes = new ArrayList<Node>();
			targetNodes = getChildNodes(currentNode);
			for(Node node : targetNodes)
			{
				if(node.getId() == newEdge.getSource().getId())
				{
					formsCycle = true;
					return formsCycle;
				}
				if(!visitedArray[node.getId()])
					nodeStack.push(node);
			}
		}
		return formsCycle;
	}
}

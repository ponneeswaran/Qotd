/**
 * 
 */
package com.awqotd.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.awqotd.vo.Node;
import com.awqotd.vo.Edge;
/**
 * @author Ponneeswaran
 *
 */
@Component
public class DAG 
{
	@Autowired
	DataAccessor dataAccessor;
	private Map<Integer, Node> mappings_id;
	private Map<String, Node> mappings_name;
	//private static String _path = "//DAGTags.xml";
	private List<Node> nodes;
	private List<Edge> edges;	
	@PostConstruct
	public void init()
	{
		mappings_id = new HashMap<Integer, Node>();
		mappings_name = new HashMap<String, Node>();
		updateNodeAndEdgesFromDB();
	}
	private void updateNodeAndEdgesFromDB()
	{
		nodes = dataAccessor.getDAGNodes();
		for(Node temp: nodes)
		{
			mappings_id.put(temp.getId(), temp);
			mappings_name.put(temp.getTagName(), temp);
		}
		edges = dataAccessor.getDAGEdges();
		for(Edge edge: edges)
		{
			edge.setSource(mappings_id.get(edge.getSource_id()));
			edge.setTarget(mappings_id.get(edge.getTarget_id()));
		}
	}
	public Node getNodeFromTagName(String tagName)
	{
		return mappings_name.get(tagName);
	}
	public List<Node> getNodes()
	{
		return nodes;
	}
	public List<Edge> getEdges()
	{
		return edges;
	}
	
	public List<Edge> getOutgoingEdges(Node node)
	{
		List<Edge> outgoingEdges = new ArrayList<Edge>();
		for(Edge edge : edges)
		{
			if(edge.getSource().getId() == node.getId())
				outgoingEdges.add(edge);
		}
		return outgoingEdges;
	}
	
	public List<Edge> getIncomingEdges(Node node)
	{
		List<Edge> incomingEdges = new ArrayList<Edge>();
		for(Edge edge : edges)
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
		newNode.setId(nodes.size());
		nodes.add(newNode);
		return newNode;
	}
	
	public boolean addEdge(Edge newEdge)
	{
		boolean cycleExists = edgeFormsCycle(newEdge);
		if(!cycleExists)
		{
			edges.add(newEdge);
			return true;
		}
		else
			return false;
	}
	private boolean edgeFormsCycle(Edge newEdge)
	{
		boolean formsCycle = false;
		boolean[] visitedArray = new boolean[nodes.size()];
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
	/*public static void main(String[] args) 
	{
		Node node1 = new Node("java","",0);
		Node node2 = new Node("object","",1);
		Node node3 = new Node("inheritance","",2);
		Node node4 = new Node("class","",3);
		Node node5 = new Node("extends","",4);
		DAG.getInstance().addNode(node1);
		DAG.getInstance().addNode(node2);
		DAG.getInstance().addNode(node3);
		DAG.getInstance().addNode(node4);
		DAG.getInstance().addNode(node5);
		System.out.println(DAG.getInstance().addEdge(new Edge(node1, node2, 0))?"True":"false");
		System.out.println(DAG.getInstance().addEdge(new Edge(node1, node4, 0))?"True":"false");
		System.out.println(DAG.getInstance().addEdge(new Edge(node2, node3, 0))?"True":"false");
		System.out.println(DAG.getInstance().addEdge(new Edge(node4, node3, 0))?"True":"false");
		System.out.println(DAG.getInstance().addEdge(new Edge(node3, node5, 0))?"True":"false");
		System.out.println(DAG.getInstance().addEdge(new Edge(node5, node1, 0))?"True":"false");
	}*/
}
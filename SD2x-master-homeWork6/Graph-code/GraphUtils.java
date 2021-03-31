

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {
	
	public static int minDistance(Graph graph, String src, String dest) {
		
		if (graph != null && src!= null && dest!= null && src != "" && dest !="") {
						
			if (graph.containsElement(src) && graph.containsElement(dest)) {					
				return new BreadthFirstSearch(graph).bfsDistance(graph.getNode(src), dest); 
			}			
		}		
		return -1;
	}


	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		Set<String> nodes = new HashSet<String>();
		
		if (graph != null && src!= null && distance >= 1 && src != "" && graph.containsElement(src)) {
			if (graph.getAllNodes().size()==1 && graph.containsElement(src)) {
				nodes.clear();
				return nodes;	}
			
			else{
				for (Node node : graph.getAllNodes()) {
					int i = new BreadthFirstSearch(graph).bfsDistance(graph.getNode(src), node.toString()) ;
					if (i <= distance && i >= 1) {
						nodes.add(node.toString());
					}				
				}				
			}		
			return nodes;		
		}
		return null; 
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if (g == null || values == null || values.isEmpty()) {
			return false;
		}
		
		String src = values.get(0);
		Set<String> marked = new HashSet<>();
		marked.add(src);
		
		if (!src.equals(values.get(values.size() - 1))) // not a cycle - start and end should be the same
			return false;
		
		for (int i = 1; i < values.size(); i++) {
			if (!g.containsElement(values.get(i)))  // not a node
				return false;
			
			if (marked.contains(values.get(i)) // already visited
					&& i != (values.size() - 1))  // except for the last node
				return false;
			
			if (!g.getNodeNeighbors(g.getNode(values.get(i-1))).contains(g.getNode(values.get(i)))) { // not connected
				return false;
			}
			marked.add(values.get(i));
		}
				
		
		return marked.size() == g.getNumNodes(); 
	}

}

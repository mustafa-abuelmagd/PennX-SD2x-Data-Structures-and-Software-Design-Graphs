

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Depth First Search (DFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class DepthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public DepthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}

	public boolean dfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
			return false;
		}	

		if (start.getElement().equals(elementToFind)) {
			return true;
		} else {
			marked.add(start);
			for (Node neighbor : graph.getNodeNeighbors(start)) {
				if (!marked.contains(neighbor) && 
						dfs(neighbor, elementToFind))
					return true;
			}
			return false;
		}
	}




	public boolean dfsACircle(Node start, String elementToFind, List<String> values) {   //Graph g, List<String> values
		if (!graph.containsNode(start)) {
			return false;
		}	

		if (start.getElement().equals(elementToFind) && values.contains(start.getElement())) {
			System.out.println("where did it stop"  );
			return true;
		} else {
			
			marked.add(start);
			
			for (Node neighbor : graph.getNodeNeighbors(start)) {
				System.out.println("marked.contains(neighbor)   " + marked.contains(neighbor) + "  dfsACircle(neighbor, elementToFind, values)     " + dfsACircle(neighbor, elementToFind, values) + "values.contains(neighbor)       " +values.contains(neighbor.getElement()));
				if (!marked.contains(neighbor) && 
						dfsACircle(neighbor, elementToFind, values)  && values.contains(neighbor.getElement())) {
					
					values.remove(neighbor.getElement());
					return true;
				}
			}
			return false;
		}
	}


}

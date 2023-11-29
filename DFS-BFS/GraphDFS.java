import java.util.*;

public class GraphDFS 
{
	static class Graph 
	{
        	int V;
	        LinkedList<Integer> adj[];

        	Graph(int v) 
		{
            		this.V = v;
            		adj = new LinkedList[v];

            		for (int i = 0; i < v; i++) 
			{
                		adj[i] = new LinkedList<>();
            		}
        	}

        	void addEdge(int source, int destination) 
		{
            		adj[source].add(destination);
        	}

	        void DFS(int startNode) 
		{
            		boolean[] visited = new boolean[V];
            		graphDFS(startNode, visited);
        	}

	        void graphDFS(int currentNode, boolean[] visited) 
		{
           		visited[currentNode] = true;
            		System.out.print(currentNode + " ");

            		for (int neighbor : adj[currentNode]) 
			{
                		if (!visited[neighbor]) 
				{
                    			graphDFS(neighbor, visited);
                		}
            		}
        	}
    	}

    	public static void main(String[] args) 
	{
        	Scanner sc = new Scanner(System.in);

	        System.out.print("Enter the number of nodes in the graph: ");
        	int numNodes = sc.nextInt();

        	Graph g = new Graph(numNodes);

        	System.out.print("Enter the number of edges: ");
        	int numEdges = sc.nextInt();

        	for (int i = 0; i < numEdges; i++) 
		{
			System.out.print("Enter source and destination for edge " + (i + 1) + ": ");
			int source = sc.nextInt();	
			int destination = sc.nextInt();
			g.addEdge(source, destination);
        	}

	        System.out.print("\n\nEnter the starting node for DFS: ");
        	int start = sc.nextInt();

	        System.out.println("DFS traversal is as follows: ");
        	g.DFS(start);
    	}
}
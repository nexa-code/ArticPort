import java.io.*;
import java.util.*;

public class GraphBFS
{
	int V;                              //number of nodes in the graph
    	LinkedList<Integer> adj[];          //adjacency list
    	Queue<Integer> queue;               //maintaining a queue
 
    	GraphBFS(int v)
    	{
        	V = v;
	        adj = new LinkedList[v];
        	for (int i=0; i<v; i++)
	        {
        	    adj[i] = new LinkedList<>();
	        }
        	queue = new LinkedList<Integer>();
	}

	void addEdge(int v,int w)
    	{
        	adj[v].add(w);			//adding an edge to the adjacency list
    	}
 
	void BFS(int n)
    	{

        	boolean nodes[] = new boolean[V];       //initialize boolean array for holding the data
		int a = 0;
 
	        nodes[n]=true;                  
        	queue.add(n);                   //root node is added to the top of the queue
 
	        while (queue.size() != 0)
        	{
			n = queue.poll();             //remove the top element of the queue
			System.out.print(n+" ");           //print the top element of the queue
 
			for (int i = 0; i < adj[n].size(); i++)  //iterate through the linked list and push all neighbors into queue
            		{
		                a = adj[n].get(i);
                		if (!nodes[a])                    //only insert nodes into queue if they have not been explored already
                		{
                    			nodes[a] = true;
                    			queue.add(a);
                		}
            		}  
        	}
    	}

	public static void main(String args[])
    	{	
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter how many nodes you want in your graph: ");
		int noOfNodes = sc.nextInt();
        	GraphBFS graph = new GraphBFS(noOfNodes);
 
		System.out.print("Enter the number of edges: ");
       	 	int numEdges = sc.nextInt();

		System.out.println();

        	for (int i = 0; i < numEdges; i++) 
		{
            		System.out.print("Enter source and destination for edge " + (i + 1) + ": ");
			int src = sc.nextInt();
			int dest = sc.nextInt();

			graph.addEdge(src, dest);
			graph.addEdge(dest, src);
        	}


 		System.out.print("\nEnter the sorce node for BFS traversal: ");
		int srcBFS = sc.nextInt();
	        System.out.println("The Breadth First Traversal of the graph is as follows :");
        	graph.BFS(srcBFS);
	}
}
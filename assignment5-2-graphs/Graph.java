/*
 * Class represents a Graph
 * Author: Andy Hwang | 251217976
 * CS2210
 */

import java.util.Iterator;
import java.util.ArrayList;

public class Graph implements GraphADT{

    private Edge adjMatrix[][];
    private ArrayList<Node> nodeList;
    private int matrixSize;

    // Constructor for Graph class
    Graph (int n){
        adjMatrix = new Edge[n][n];
        nodeList = new ArrayList<Node>();
        matrixSize = n;

        for (int i = 0; i < n; i++) {
            nodeList.add(new Node(i));
        }
    }

    //  Returns the node with the specified id
    //  If no node with this id exists,throws GraphException
    public Node getNode(int id) throws GraphException{
        try {
            return nodeList.get(id);
        } catch (Exception e) {
            throw new GraphException();
        }
    }

    //   Adds an edge of the given type connecting u and v 
    //   This method throws a GraphException if either node does not exist or if in the graph there is already an edge connecting the given nodes
    public void addEdge(Node u, Node v, String edgeType) throws GraphException{
        int codeU = u.getId();
        int codeV = v.getId();
        
        // Check if nodes u and v exist
        try {
            nodeList.get(codeU);
            nodeList.get(codeV);
        } catch (Exception e) {
            throw new GraphException();
        }
        
        // Check if edge already exists or not
        if (adjMatrix[codeU][codeV] == null) {
            adjMatrix[codeU][codeV] = new Edge(u, v, edgeType);
            adjMatrix[codeV][codeU] = new Edge(v, u, edgeType);
        } else {
            //System.out.println("edge already exists");
            throw new GraphException();
        }
    }

    /* Returns a Java Iterator storing all the edges incident
		       on the specified node. It returns null if the node does
		       not have any edges incident on it. Throws a GraphException
		       if the node does not exist. */
    public Iterator incidentEdges(Node u) throws GraphException {
        int codeU = u.getId();
        
        // Check if node u exists
        try {
            nodeList.get(codeU);
        } catch (Exception e) {
            throw new GraphException();
        }
        
        ArrayList<Edge> incidentEdges = new ArrayList<Edge>();
        boolean edgesExist = false;

        // Adds all edges incident on u to a new array
        for (int i = 0; i < matrixSize; i++){
            if (adjMatrix[codeU][i] == null) {
                continue;
            } else {
                incidentEdges.add(adjMatrix[codeU][i]);
                edgesExist = true;
            }
        }
        
        // Check if edges exist and returns the proper value based on that
        if (edgesExist) {
            Iterator iter = incidentEdges.iterator();
            return iter;
        } else {
            return null;
        }
    }

    /* Returns the edge connecting the given nodes. Throws 
		       a GraphException if there is no edge conencting the given 
		       nodes, or if u or v do not exist. */
    public Edge getEdge(Node u, Node v) throws GraphException {
        int codeU = u.getId();
        int codeV = v.getId();
        
        // Check if nodes u and v exist
        try {
            nodeList.get(codeU);
            nodeList.get(codeV);
        } catch (Exception e) {
            throw new GraphException();
        }

        // Checks if the edge exists
        if (adjMatrix[codeU][codeV] == null) {
            throw new GraphException();
        } else {
            return adjMatrix[codeU][codeV];
        }
    }

    /* Returns true is u and v are adjacent, and false otherwise. 
		      It throws a GraphException if either node does not exist. */
    public boolean areAdjacent(Node u, Node v) throws GraphException {
        int codeU = u.getId();
        int codeV = v.getId();
        
        // Check if nodes u and v exist
        try {
            nodeList.get(codeU);
            nodeList.get(codeV);
        } catch (Exception e) {
            throw new GraphException();
        }

        if (adjMatrix[codeU][codeV] == null) {
            return false;
        } else {
            return true;
        }
    }
}
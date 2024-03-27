/*
 * Class creates and solves maps
 * Author: Andy Hwang | 251217976
 * CS2210
 */

import java.io.*;
import java.util.*;

// Constructor takes in file input to create map
public class MyMap {
    private Graph mapGraph;
    private int startNodeID;
    private int endNodeID;
    private int maxPrivate;
    private int maxConstruct;
    private int width = 0;
    private int length = 0;
    private Stack<Node> pathStack;

    private final String PUBLIC = "public";
    private final String PRIVATE = "private";
    private final String CONSTRUCTION = "construction";

    MyMap (String inputFile) throws MapException{
        pathStack = new Stack<Node>();
        // Reads input file
        try {
            FileReader file = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(file);

            // Reading integer variables
            String currentLine;
            for (int i = 0; i < 7; i++) {
                currentLine = br.readLine();
                switch (i) {
                    case 0: break;
                    case 1: startNodeID = Integer.parseInt(currentLine);
                            break;
                    case 2: endNodeID = Integer.parseInt(currentLine);
                            break;
                    case 3: width = Integer.parseInt(currentLine);
                            break;
                    case 4: length = Integer.parseInt(currentLine);
                            break;
                    case 5: maxPrivate = Integer.parseInt(currentLine);
                            break;
                    case 6: maxConstruct = Integer.parseInt(currentLine);
                            break;

                }
            }
            // Creating graph
            mapGraph = new Graph(width * length);
            int numHorz = (width * 2) - 1;
            int numVert = (length * 2) - 1;
            int nodeIdCounter = -1;
            
            // Loop for traversing rows
            for (int i = 0; i < numVert; i++) {
                currentLine = br.readLine();

                // Conditional for even rows (adding horizontal edges)
                if ((i % 2) == 0) {

                    // Loop for traversing columns
                    for (int j = 0; j < numHorz; j++) {
                        char roadInput = currentLine.charAt(j);

                        if (roadInput == '+') {
                            nodeIdCounter++;
                            continue;
                        } else if (roadInput == 'V') {
                            insertHorizontal(nodeIdCounter, PRIVATE);
                        } else if (roadInput == 'P') {
                            insertHorizontal(nodeIdCounter, PUBLIC);
                        } else if (roadInput == 'C') {
                            insertHorizontal(nodeIdCounter, CONSTRUCTION);
                        } else if (roadInput == 'B') {
                            continue;
                        }
                    }

                // Conditional for odd rows (adding vertical edges)
                } else {
                    int rowNodeCounter = nodeIdCounter + 1;
                    int checkB = 0;
                    
                    // Loop for traversing columns
                    for (int j = 0; j < numHorz; j++) {
                        char roadInput = currentLine.charAt(j);
                        
                        if (roadInput == 'V') {
                            insertVertical(rowNodeCounter, PRIVATE);
                            rowNodeCounter++;
                            checkB = 0;
                        } else if (roadInput == 'P') {
                            insertVertical(rowNodeCounter, PUBLIC);
                            rowNodeCounter++;
                            checkB = 0;
                        } else if (roadInput == 'C') {
                            insertVertical(rowNodeCounter, CONSTRUCTION);
                            rowNodeCounter++;
                            checkB = 0;
                        } else if (roadInput == 'B') {
                            checkB += 1;
                            if (checkB == 2) {
                                rowNodeCounter++;
                                checkB = 0;
                            }
                            continue;
                        }
                        
                    }
                }

                System.out.print("\n");
            }            
            br.close();
        } catch (IOException e) {
            throw new MapException();
        }
    }

    // Finds the path from the start node to the end node
    // Returns an iterator containing nodes of a path from the start to the end
    Iterator findPath (int start, int destination, int maxPrivate, int maxConstruction) {
        if (path(start, destination, maxPrivate, maxConstruction)) {
            return pathStack.iterator();
        } else {
            return null;
        }
    }

    // Helper function for finding the path
    boolean path (int startId, int endId, int privateNum, int construcNum) {
        try {
            Node startNode = mapGraph.getNode(startId);
            Node prevNode;
            
            // Checks if there was a previous node
            // If there was - initializes, if not - sets to null
            try {
                prevNode = pathStack.peek();

                // Checks if this edge is a valid path
                Edge thisEdge = mapGraph.getEdge(startNode, prevNode);
                if ((thisEdge.getType()).equals(PRIVATE)) {
                    privateNum--;
                    if (privateNum < 0) {
                        return false;
                    }
                } else if ((thisEdge.getType()).equals(CONSTRUCTION)) {
                    construcNum--;
                    if (construcNum < 0) {
                        return false;
                    }
                }
            } catch (EmptyStackException e) {
                prevNode = null;
                // Empty on purpose, because nothing needs to be done
            }

            // Marks and pushes node after validating that it is a possible path.
            startNode.markNode(true);
            pathStack.push(startNode);

            // Base case
            if (startId == endId) {
                return true;
            }

            Iterator edgeOptions = mapGraph.incidentEdges(startNode);
            // Loop to check all edges
            while (edgeOptions.hasNext()) {
                Edge nextEdge = (Edge) edgeOptions.next();
                Node nextNode = nextEdge.secondNode();

                if (nextNode.getMark() == false) {
                    if (path(nextNode.getId(), endId, privateNum, construcNum) == true) return true; 
                }
            }

            startNode.markNode(false);
            pathStack.pop();
        } catch (GraphException e) {
            // Empty with purpose
        }
        return false;
    }

    //  Returns the graph representing the roadmap
    Graph getGraph() {
        return mapGraph;
    }

    // Returns the id of the starting node 
    int getStartingNode() {
        return startNodeID;
    }
    
    // Returns the id of the destination node
    int getDestinationNode() {
        return endNodeID;
    }

    // Returns the maximum number allowed of private roads in the path from the starting node 
    // to the destination
    int maxPrivateRoads() {
        return maxPrivate;
    }


    // Returns the maximum number allowed of construction roads
    // in the path from the starting node to the destination
    int maxConstructionRoads() {
        return maxConstruct;
    }

    // Adds either a Public, Private, or Construction road horizontally based on the type
    private void insertHorizontal(int nodeID, String type) {
        int rightNode = nodeID + 1;
        try {
            mapGraph.addEdge(mapGraph.getNode(nodeID), mapGraph.getNode(rightNode), type);
        } catch (GraphException e) {
            // Empty because no action is required if empty
        }
    }

    // Adds either a Public, Private, or Construction road horizontally based on the type
    private void insertVertical(int nodeID, String type) {
        int topNode = nodeID - width;
        try {
            mapGraph.addEdge(mapGraph.getNode(nodeID), mapGraph.getNode(topNode), type);
        } catch (GraphException e) {
            // Empty because no action is required if empty
        }
    }
}

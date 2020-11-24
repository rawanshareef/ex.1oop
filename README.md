


### Undirectional Weighted Graph
**In this project we built a undirected graph which is also a weighted graph:**
![](https://ibpublicimages.s3-us-west-2.amazonaws.com/tutorial/dijkstra2.png)
_For more information to this project:_

https://en.wikipedia.org/wiki/Graph_(discrete_mathematics) 

https://www.coursera.org/lecture/advanced-data-structures/core-dijkstras-algorithm-2ctyF
https://www.interviewbit.com/tutorial/dijkstra-algorithm/

  
**Here are the departments that build the graph:**

**WGraph_DS class:**
_implements class internally "Node" ,This class represents the vertices,  when each vertex has the following properties:
getKey(),getInfo,setInfo(String s),double getTag(),setTag(double t) 
Each graph has the following properties:_

**there is 2 HashMap :**

 - **"vertex"** HashMap it is contains all the vertex in the graph .
 - the secoend HashMap
**"edges"**
 contains all the key vertex in the graph and who is the key that is connected to this vertex and the weight between the vertex.
 
- **numOfEdge:** number of edeges in the graph .

 - **numOfNodes:**
 number of vertex in the graph.
 
  - **mc:**
   count of the change
   
-  **numOfEdge:**
  number of edeges in the graph .
  
- **numOfNodes:**
number of vertex in the graph.
-**mc:**
count of the changes. 

_**the fanctiones:**_

- **WGraph_DS ():**
creating a new graph

- **getNode(int key):**
return the node  by the key

- **hasEdge(int node1, int node2):**
checks if 2 nodes are connected

- **getEdge(int node1, int node2):**
return the weight if the edge (node1, node)

- **addNode(int key):**
add  node to the graph
- **connect(int node1, int node2, double w):**
connects the 2 nodes and change the weight(w) of the edge between them.

- **Collection<node_info> getV():**
returns all the vertex in the graph

- **Collection<node_info> getV(int node_id):**
return the neighbores nodes to node_id.
-**removeNode(int key):**
remove vertex  from the graph

- **removeEdge(int node1, int node2):**
remove the edge between node1 and node2.

- **nodeSize():**
return the number of all  vertex in the graph

- **edgeSize():**
return the number of all  edeges in the graph

- **getMC():**
it return how much  changes was in the graph. 


**WGraph_Algo:**
_The Graph Algo class contains all algorithms that can be run on a graph._

_**the fanctiones:**_

- **Init:** Allows to insert a graph into the Graph Algo class so that we can run algorithms on it.
- **getGraph():** Return the underlying graph of which this class works.
- **Copy():** The algorithm compute a deep copy of this graph.
- **isConnected():** Checks whether the graph is strongly related
 - **shortestPathDist(int src, int dest):** The algorithm finds the shortest way between the node Src and the node dest, the shortest way is the way the least amount of weight .
- **List<node_info>shortestPathDist(int src, int dest):** return List of the shortest path.
- **save(String file):** saves a graph to file
- **load(String file):** loads a graph from a file




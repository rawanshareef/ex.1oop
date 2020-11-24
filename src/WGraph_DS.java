package ex1.src;

import java.io.Serializable;
import java.util.*;

/**
 * This interface represents an undirectional weighted graph.
 * It should support a large number of nodes (over 10^6, with average degree of 10).
 * The implementation should be based on an efficient compact representation
 * (should NOT be based on a n*n matrix).
 *
 */
public class WGraph_DS implements weighted_graph, Serializable  {
	private HashMap <Integer,node_info> vertex;
	private HashMap<Integer, HashMap<Integer, Double>> edges;
	private int numOfEdge;
	private int numOfNodes;
	private int mc;



	public WGraph_DS () {
		vertex =new HashMap<Integer, node_info>();
		edges =new HashMap<Integer, HashMap<Integer, Double>>();
		numOfEdge=0;
		numOfNodes=0;
		mc=0;
	}
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_info getNode(int key) {
		// TODO Auto-generated method stub
		return vertex.get(key) ;
	}
	/**
	 * return true iff (if and only if) there is an edge between node1 and node2
	 * Note: this method should run in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if(!edges.containsKey(node1)||!edges.containsKey(node2)  ) {
			return false;
		}
		if(node1==node2 ){
			return true;
		}
		return edges.get(node1).containsKey(node2)&&edges.get(node2).containsKey(node1);
	}
	/**
	 * return the weight if the edge (node1, node1). In case
	 * there is no such edge - should return -1
	 * Note: this method should run in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */
	@Override
	public double getEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if (!hasEdge(node1, node2)||!vertex.containsKey(node1) || !vertex.containsKey(node2)) {
			return -1;
		}
		return edges.get(node1).get(node2) ;

	}
	/**
	 * add a new node to the graph with the given key.
	 * Note: this method should run in O(1) time.
	 * Note2: if there is already a node with such a key -> no action should be performed.
	 * @param key
	 */
	@Override
	public void addNode(int key) {
		// TODO Auto-generated method stub
		if (!vertex.containsKey(key)) {
			Node newnode = new Node(key);
			vertex.put(key, newnode);
			edges.put(key, new HashMap<Integer, Double>());
			numOfNodes++;
			mc++;
		}
	}
	/**
	 * Connect an edge between node1 and node2, with an edge with weight >=0.
	 * Note: this method should run in O(1) time.
	 * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		// TODO Auto-generated method stub
		if (vertex.containsKey(node1) && vertex.containsKey(node2) && w > 0 && node1 != node2) {
			if (!hasEdge(node1, node2)) {
				edges.get(node1).put(node2, w);
				edges.get(node2).put(node1, w);
				edges.put(node1,edges.get(node1));
				edges.put(node2,edges.get(node2));
				mc++;
				numOfEdge++;
			}
			else {
				edges.get(node1).replace((node2), w);
				edges.get(node2).replace((node1), w);
				mc++;
			}
		}
	}

	/**
	 * This method return a pointer (shallow copy) for a
	 * Collection representing all the nodes in the graph.
	 * Note: this method should run in O(1) tim
	 * @return Collection<node_data>
	 */
	@Override
	public Collection<node_info> getV() {
		// TODO Auto-generated method stub
		return vertex.values();
	}
	/**
	 *
	 * This method returns a Collection containing all the
	 * nodes connected to node_id
	 * Note: this method can run in O(k) time, k - being the degree of node_id.
	 * @return Collection<node_data>
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		// TODO Auto-generated method stub
		if(!vertex.containsKey(node_id)) {
			return null;
		}
		Collection<node_info> nei = new ArrayList<>();
		for ( Integer i:edges.get(node_id).keySet()) {
			nei.add( getNode(i));
		}
		return nei;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WGraph_DS wGraph_ds = (WGraph_DS) o;
		return numOfEdge == wGraph_ds.numOfEdge &&
				numOfNodes == wGraph_ds.numOfNodes &&
				mc == wGraph_ds.mc &&
				Objects.equals(vertex, wGraph_ds.vertex) &&
				Objects.equals(edges, wGraph_ds.edges);
	}
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none).
	 * @param key
	 */
	@Override

	public node_info removeNode(int key) {
		// TODO Auto-generated method stub
		if (!vertex.containsKey(key)) {
			return null;
		}
		Collection<node_info> n = vertex.values();
		Iterator<node_info> itr = n.iterator();
		while (itr.hasNext()) {
			node_info node = itr.next();
			if (edges.get(node.getKey()).get(key) != null) {
				edges.get(node.getKey()).remove(key);
				numOfEdge--;
				mc++;
			}
		}
		mc++;
		numOfNodes--;
		return vertex.remove(key);

		/**
		 * Delete the edge from the graph,
		 * Note: this method should run in O(1) time.
		 * @param node1
		 * @param node2
		 */
	}
	@Override
	public void removeEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		if(hasEdge(node1,node2)){
			edges.get(node1).remove(node2) ;
			edges.get(node2).remove(node1) ;
			numOfEdge--;
			mc++;
		}
	}
	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return numOfNodes;
	}
	/**
	 * return the number of edges (undirectional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return numOfEdge;
	}
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * Any change in the inner state of the graph should cause an increment in the ModeCount
	 * @return
	 */
	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return mc;
	}

	/**
	 * Return the key (id) associated with this node.
	 * Note: each node_data should have a unique key.
	 * @return
	 */
	private class Node implements node_info,Serializable {


		private int key;
		private String info;
		private double tag;


		private Node(int key) {
			this.key=key;
			info="";
			tag=0;
		}

		public int getKey() {
			// TODO Auto-generated method stub
			return key;
		}
		/**
		 * return the remark (meta data) associated with this node.
		 * @return
		 */
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return info;
		}
		/**
		 * Allows changing the remark (meta data) associated with this node.
		 * @param s
		 */
		@Override
		public void setInfo(String s) {
			// TODO Auto-generated method stub
			info=s;
		}
		/**
		 * Temporal data (aka distance, color, or state)
		 * which can be used be algorithms
		 * @return
		 */
		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return tag;
		}

		/**
		 * Allow setting the "tag" value for temporal marking an node - common
		 * practice for marking by algorithms.
		 * @param t - the new value of the tag
		 */
		@Override
		public void setTag(double t) {
			// TODO Auto-generated method stub
			tag=t;

		}
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return key == node.key &&
					Double.compare(node.tag, tag) == 0 &&
					Objects.equals(info, node.info);
		}
	}

}

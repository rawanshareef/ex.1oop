package ex1.src;
import java.io.*;
import java.util.*;
/**
 * This interface represents an Undirected (positive) Weighted Graph Theory algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected();
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<node_data> shortestPath(int src, int dest);
 * 5. Save(file);
 * 6. Load(file);
 *
 */
public class WGraph_Algo implements weighted_graph_algorithms {

	private weighted_graph g;


	/**
	 * Init the graph on which this set of algorithms operates on.
	 * @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this.g = g;
	}
	/**
	 * Return the underlying graph of which this class works.
	 * @return
	 */
	@Override
	public weighted_graph getGraph() {
		return g;
	}
	/**
	 * Compute a deep copy of this weighted graph.
	 * @return
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph paste = new WGraph_DS();
		for (node_info present : g.getV()) {
			paste.addNode(present.getKey());
		} 
		for (node_info present : g.getV()) {
			for (node_info present2 : g.getV(present.getKey())) {
				paste.connect(present.getKey(), present2.getKey(),  g.getEdge(present.getKey(), present2.getKey()));
			}
		}
		return paste;

	}

	private void reset_tag_to0() {//reset tage for all vertex to zero
		for (node_info n : g.getV()) {
			n.setTag(0);
		}
	}
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume ubdirectional graph.
	 * @return
	 */
	@Override
	public boolean isConnected() {
		if (g.nodeSize() == 0 || g.nodeSize() == 1) {
			return true;
		}
		if (g.nodeSize() > g.edgeSize() + 1) {
			return false;
		}
		reset_tag_to0();
		node_info first = g.getV().iterator().next();
		Queue<node_info> queue = new LinkedList<>();
		queue.add(first);
		while (!queue.isEmpty()) {
			first = queue.poll();
			first.setTag(2);
			for (node_info s : g.getV(first.getKey())) {
				if (s.getTag() == 0) {
					queue.add(s);
					s.setTag(1);
				}
			}
		}
		for (node_info check : g.getV()) {
			if (check.getTag() == 0 || check.getTag() == 1)
				return false;

		}
		return true;
	}

	private boolean if_visited(node_info check) {//check if the node visited
		if (check.getInfo().equals(false)) {
			return false;
		}
		return true;
	}

	private void change_graph_to_unvisited() {//change all the info to false

		for (node_info node : g.getV()) {
			node.setInfo("false");
		}
	}

	private void change_dist_to_infinity() {// change all the tage to infinity
		double dist = Double.MAX_VALUE;
		for (node_info node : g.getV()) {
			node.setTag(dist);
		}
	}
	/**
	 * returns the length of the shortest path between src to dest
	 * Note: if no such path --> returns -1
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		node_info srcNode = g.getNode(src);
		node_info destNode = g.getNode(dest);
		if (g.getV().size() == 0 || g.getNode(src) == null || g.getNode(dest) == null) {
			return -1;
		}
		if (src == dest) {
			return 0;
		}
		change_dist_to_infinity();
		change_graph_to_unvisited();
		Queue<node_info> priorityQueue= new LinkedList<>();
		List<node_info> MyPath= new LinkedList<>();
		priorityQueue.add(srcNode);
		srcNode.setTag(0);
		while (!priorityQueue.isEmpty()) {
			node_info first = priorityQueue.poll();
			for (node_info n : g.getV(first.getKey())) {
				double weight=first.getTag() + g.getEdge(first.getKey(), n.getKey());
				if (weight < n.getTag()) {
					n.setTag(weight);
					MyPath.add(first);
					priorityQueue.remove(n);
					priorityQueue.add(n);
				}
			}

		}
		if (destNode.getTag()<0 ||destNode.getTag() > Integer.MAX_VALUE) {
			return -1;

		}
		else return destNode.getTag();

	}
	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * Note if no such path --> returns null;
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
		node_info srcNode = g.getNode(src);
		node_info destNode = g.getNode(dest);
		if (g.getV().size() == 0 || g.getNode(src) == null || g.getNode(dest) == null) {
			return null;
		}
		if (shortestPathDist(src, dest)<0) {
			return null;
		}
		List<node_info> road = new LinkedList<>();
		node_info loop = srcNode;
		road.add(srcNode);

		while (loop.getTag() > 0) {
			road.add(g.getNode(Integer.parseInt(loop.getInfo())));
			loop = (g.getNode(Integer.parseInt(loop.getInfo())));
		}
		return road;
	}

	/**
	 * Saves this weighted (undirected) graph to the given
	 * file name
	 * @param file - the file name (may include a relative path).
	 * @return true - iff the file was successfully saved
	 */
	@Override
	public boolean save(String file) {
		try {
			FileOutputStream file1= new FileOutputStream(file);
			ObjectOutputStream saved=new ObjectOutputStream(file1);
			saved.writeObject(g);
			saved.close();
			file1.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}
	/**
	 * This method load a graph to this graph algorithm.
	 * if the file was successfully loaded - the underlying graph
	 * of this class will be changed (to the loaded one), in case the
	 * graph was not loaded the original graph should remain "as is".
	 * @param file - file name
	 * @return true - iff the graph was successfully loaded.
	 */
	@Override
	public boolean load(String file) {
		try {
			FileInputStream file2=new FileInputStream(file);
			ObjectInputStream pre=new ObjectInputStream(file2);
			this.g=(WGraph_DS) pre.readObject();
			pre.close();
			file2.close();
			return true;
		} catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WGraph_Algo that = (WGraph_Algo) o;
		return Objects.equals(g, that.g);
	}



}
package lista_netezinski_nasocen;

//import java.util.Iterator;
import java.util.Iterator;
import java.util.Stack;

public class Graph<E> {

	int num_nodes;
	GraphNode<E> adjList[];

	@SuppressWarnings("unchecked")
	public Graph(int num_nodes, E[] list) {
		this.num_nodes = num_nodes;
		adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			adjList[i] = new GraphNode<E>(i, list[i]);
	}

	@SuppressWarnings("unchecked")
	public Graph(int num_nodes) {
		this.num_nodes = num_nodes;
		adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
		for (int i = 0; i < num_nodes; i++)
			adjList[i] = new GraphNode<E>(i, null);
	}

	int adjacent(int x, int y) {
		// proveruva dali ima vrska od jazelot so
		// indeks x do jazelot so indeks y
		return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
	}

	void addEdge(int x, int y) {
		// dodava vrska od jazelot so indeks x do jazelot so indeks y
		if (!adjList[x].containsNeighbor(adjList[y])) {
			adjList[x].addNeighbor(adjList[y]);
		}
	}

	void deleteEdge(int x, int y) {
		adjList[x].removeNeighbor(adjList[y]);
	}
	
	/************************TOPOLOGICAL SORT*******************************************************************/
	
	void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
		if(!visited[i]){
			visited[i] = true;
			Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
			System.out.println("dfsVisit: "+i+" Stack="+s);
			while(it.hasNext()){
				dfsVisit(s, it.next().getIndex(), visited);
			}
			s.push(i);
			System.out.println("--dfsVisit: "+i+" Stack="+s);
		}
	}
	
	void topological_sort_dfs(){
		boolean visited[] = new boolean[num_nodes];
		for(int i=0;i<num_nodes;i++){
			visited[i] = false;
		}
		
		Stack<Integer> s = new Stack<Integer>();
		
		for(int i=0;i<num_nodes;i++){
			dfsVisit(s,i,visited);
		}
		System.out.println("----Stack="+s);
		while(!s.isEmpty()){
			System.out.println(adjList[s.pop()]);
		}
	}
	
	/***********************************************************************************************************/

	@Override
	public String toString() {
		String ret = new String();
		for (int i = 0; i < this.num_nodes; i++)
			ret += i + ": " + adjList[i] + "\n";
		return ret;
	}

}

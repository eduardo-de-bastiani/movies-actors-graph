import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;

public class BFS {

	private Set<String> marked;
	private Map<String, String> edgeTo;
	private Map<String, Integer> distTo;
	private String start;

	public BFS(Graph G, String start) {
		this.start = start;
		marked = new HashSet<>();
		edgeTo = new HashMap<>();
		distTo = new HashMap<>();
		bfs(G, start);
	}

	boolean hasPathTo(String v) {
		if(v.equals(start))
			return false;
		return marked.contains(v);
	}

	int distTo(String v) {
		if(!hasPathTo(v))
			return -1; // Não chegou lá
		return distTo.get(v);
	}

	Iterable<String> pathTo(String v) {
		if (!hasPathTo(v)) return null;
		ArrayList<String> path = new ArrayList<>();
		String w = v;
		while(!w.equals(start)) {
			path.add(0,w); // insere no início da lista
			w = edgeTo.get(w);
		}
		path.add(0,start);
		return path;
	}

	private void bfs(Graph g, String s) {
		LinkedList<String> fila = new LinkedList<>();
		fila.add(s);
		marked.add(s);
		distTo.put(s,0);
		while(!fila.isEmpty()) {
			s = fila.removeFirst();
			for (String w : g.getAdj(s)) {
				if (!marked.contains(w)) {
					fila.add(w);
					marked.add(w);
					edgeTo.put(w, s);
					distTo.put(w, distTo.get(s)+1);
				}
			}	
		}
	}
}

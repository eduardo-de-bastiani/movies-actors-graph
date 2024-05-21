from graph import Graph
from collections import deque

class BreadthFirstSearch:
    def __init__(self, g, s):
        self.s = s
        self.marked = {}
        self.edgeTo = {}
        self.distTo = {}
        self.bfs(g, s)

    def hasPathTo(self, v):
        return v in self.marked


    def bfs(self, g, s):
        queue = deque([s])
        self.marked[s] = True
        self.distTo[s] = 0

        while queue:
            v = queue.popleft()
            for w in g.getAdj(v):
                if w not in self.marked:
                    queue.append(w)
                    self.marked[w] = True
                    self.getAdj[w] = v
                    self.distTo[w] = self.distTo[v] + 1


    def hasPathTo(self, v):
        return v in self.marked


    def pathTo(self, v):
        if not self.hasPathTo(v):
            return None
        path = []
        x = v

        while x != self.s:
            path.append(x)
            x = self.edgeTo[x]
        path.append(self.s)
        path.reverse
        return path
    
    def distanceTo(self, v):
        return self.distTo[v] if v in self.distTo else float('inf') #infinito positivo
    


if __name__ == "__main__":

    g = Graph("movies.txt")

    dfs = BreadthFirstSearch(g, "0")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        if dfs.hasPathTo(v):
            for w in dfs.pathTo(v):
                print(f"{w} ", end="")
        print()
    print()

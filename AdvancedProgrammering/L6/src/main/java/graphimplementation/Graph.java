package graphimplementation;

public interface Graph<V> {
  /** Return the number of vertices in the graph */
  public int getSize();

  /** Return the number of edges in the graph */
  public int getNumEdges();
  /** Return the vertices in the graph */
  public java.util.List<V> getVertices();
  /** Return the edges in the graph */
  public java.util.List<Edge> getEdges();

  /** Return the object for the specified vertex index */
  public V getVertex(int index);

  /** Return the index for the specified vertex object */
  public int getIndex(V v);

  /** Return the neighbors of vertex with the specified index */
  public java.util.List<V> getNeighbors(V v);
  /** Return the neighbors of vertex with the specified index */
  public java.util.List<Integer> getNeighborsIndex(V v);

  /** Return the incident edges of vertex v */
  public java.util.List<Edge> getIncidentEdges(V v);
  /** Return the degree for a specified vertex */
  public int getDegree(V v);

  /** Print the edges */
  public void printEdges();

  /** Clear the graph */
  public void clear();

  /** Add a vertex to the graph */  
  public boolean addVertex(V vertex);

  /** Add an edge (u, v) and (v,u) with data e to the graph */
  public boolean addEdge(int u, int v, int e);

  /** Add an edge to the graph */
  boolean addEdge(Edge e);

  /** Add an edge (u, v) and (v,u) to the graph */
  public boolean addEdge(int u, int v);

  /** Add an edge to the graph */  
//  public boolean addEdge(Edge e);

  /** Remove a vertex v from the graph, return true if successful */  
  public boolean remove(V v);

  /** Remove an edge (u, v) and (v,u) from the graph */
  public boolean remove(int u, int v);
  
//  /** Obtain a depth-first search tree */
//  public AdjacencyEdgeListGraph<V>.SearchTree dfs(int v);
//
//  /** Obtain a breadth-first search tree */
//  public AdjacencyEdgeListGraph<V>.SearchTree bfs(int v);
}


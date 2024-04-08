package graphimplementation;

public class Edge {
  int u;
  int v;
  int e;

  public Edge(int u, int v, int e) {
    this.u = u;
    this.v = v;
    this.e = e;
  }

  public boolean equals(Object o) {
    return u == ((Edge)o).u && v == ((Edge)o).v;
  }

  public int element() {
    return e;
  }


  public String toString(){
    return "(" + u + ", " + v +"): " + e;
  }
}

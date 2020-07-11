package algoritma;

import java.lang.*;
import java.util.ArrayList;
import java.util.LinkedList;

// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/ sayfasından yararlanılarak projeye uyarlanmıştır.
class MaxFlow {

    int V;    //Number of vertices in graph 
    int bitisNodeBilgisi;
    static ArrayList<Integer> yollariAt = new ArrayList<>();

    public MaxFlow(int V, int bitisNodeBilgisi) { // Node sayisini almak için bu constructorı kullandım.
        this.V = V;
        this.bitisNodeBilgisi = bitisNodeBilgisi;
    }

    public MaxFlow() {

    }

    boolean bfs(int rGraph[][], int s, int t, int parent[]) {

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i) {
            visited[i] = false;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }

    ArrayList<Integer> fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially 

        while (bfs(rGraph, s, t, parent)) {

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                System.out.println("parent[v]: " + parent[v]);
                if (v == t) {
                    yollariAt.add(bitisNodeBilgisi);
                }
                yollariAt.add(parent[v]);
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            max_flow += path_flow;
        }

        for (int i = 0; i < yollariAt.size(); i++) {
            System.out.println(yollariAt.get(i));
        }
        MaxFlow m = new MaxFlow();
        yollariAt = m.reverseArrayList(yollariAt);
        System.out.println("TERS CEVİR");
        for (int k = 0; k < yollariAt.size(); k++) {
            System.out.println(yollariAt.get(k));
        }
        yollariAt.add(max_flow);

        return yollariAt;
    }

    public ArrayList<Integer> reverseArrayList(ArrayList<Integer> alist) {

        ArrayList<Integer> revArrayList = new ArrayList<>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            revArrayList.add(alist.get(i));
        }

        return revArrayList;
    }
}

package JavaFX;


import algs4.*;

import java.io.Serializable;
import java.util.ArrayList;

import static NoWarPolis.Base_Dados.*;

import NoWarPolis.*;

import static JavaFX.Controller.readNodesFromFile;

public class SymbolGraphCaches extends Point implements Serializable {
    private ST<Node, Integer> st;
    private Node[] keys;
    private EdgeDoubleWeightedGraph graph;

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename the name of the file
     */
    public SymbolGraphCaches(String filename) {
        readNodesFromFile(".//data//nodesFX.txt");
        st = new ST<>();
        ArrayList<EdgeDoubleWeighted> ArrayEDW = new ArrayList<>();

        In in = new In(filename);

        while (!in.isEmpty()) {
            int l = Integer.parseInt(in.readLine());
            for (int i = 0; i < l; i++) {
                String line = in.readLine();
                String[] fields = line.split(";");
                switch (fields[0]) {
                    case "Rua":
                        Node n1 = new Node();
                        Node n2 = new Node();
                        Point p1 = new Point();
                        Point p2 = new Point();
                        float distCurva = 0;
                        for (String s : pois.keys()) {
                            if (pois.get(s).getNome().equals(fields[3])) {
                                n1.setN(getPoi(fields[3]));
                                p1.setLatitude(getPoi(fields[3]).getGPS().getLatitude());
                                p1.setLongitude(getPoi(fields[3]).getGPS().getLongitude());
                            }
                            if (pois.get(s).getNome().equals(fields[4])) {
                                n2.setN(getPoi(fields[4]));
                                p2.setLatitude(getPoi(fields[4]).getGPS().getLatitude());
                                p2.setLongitude(getPoi(fields[4]).getGPS().getLongitude());
                            }
                        }
                        for (String s : curvas.keys()) {
                            if (curvas.get(s).getNome().equals(fields[3])) {
                                n1.setN(getCurva(fields[3]));
                                p1.setLatitude(getCurva(fields[3]).getGPS().get(3).getLatitude());
                                p1.setLongitude(getCurva(fields[3]).getGPS().get(3).getLongitude());

                                for (int x = 0; x < getCurva(fields[3]).getGPS().size(); x++) {
                                    Point px = new Point();
                                    Point py = new Point();
                                    px.setLatitude(getCurva(fields[3]).getGPS().get(x).getLatitude());
                                    px.setLongitude(getCurva(fields[3]).getGPS().get(x).getLongitude());
                                    py.setLatitude(getCurva(fields[3]).getGPS().get(x + 1).getLatitude());
                                    py.setLongitude(getCurva(fields[3]).getGPS().get(x + 1).getLongitude());
                                    distCurva += distanciaEntrePontos(px, py);
                                }
                            }
                            if (curvas.get(s).getNome().equals(fields[4])) {
                                n2.setN(getCurva(fields[4]));
                                p2.setLatitude(getCurva(fields[4]).getGPS().get(3).getLatitude());
                                p2.setLongitude(getCurva(fields[4]).getGPS().get(3).getLongitude());

                                for (int x = 0; x < getCurva(fields[4]).getGPS().size(); x++) {
                                    Point px = new Point();
                                    Point py = new Point();
                                    px.setLatitude(getCurva(fields[4]).getGPS().get(x).getLatitude());
                                    px.setLongitude(getCurva(fields[4]).getGPS().get(x).getLongitude());
                                    py.setLatitude(getCurva(fields[4]).getGPS().get(x + 1).getLatitude());
                                    py.setLongitude(getCurva(fields[4]).getGPS().get(x + 1).getLongitude());
                                    distCurva += distanciaEntrePontos(px, py);
                                }
                            }
                        }
                        for (String s : entroncamentos.keys()) {
                            if (entroncamentos.get(s).getNome().equals(fields[3])) {
                                n1.setN(getEntroncamento(fields[3]));
                                p1.setLatitude(getEntroncamento(fields[3]).getGPS().getLatitude());
                                p1.setLongitude(getEntroncamento(fields[3]).getGPS().getLongitude());
                            }
                            if (entroncamentos.get(s).getNome().equals(fields[4])) {
                                n2.setN(getEntroncamento(fields[4]));
                                p2.setLatitude(getEntroncamento(fields[4]).getGPS().getLatitude());
                                p2.setLongitude(getEntroncamento(fields[4]).getGPS().getLongitude());
                            }
                        }
                        for (String s : cruzamentos.keys()) {
                            if (cruzamentos.get(s).getNome().equals(fields[3])) {
                                n1.setN(getCruzamento(fields[3]));
                                p1.setLatitude(getCruzamento(fields[3]).getGPS().getLatitude());
                                p1.setLongitude(getCruzamento(fields[3]).getGPS().getLongitude());
                            }
                            if (cruzamentos.get(s).getNome().equals(fields[4])) {
                                n2.setN(getCruzamento(fields[4]));
                                p2.setLatitude(getCruzamento(fields[4]).getGPS().getLatitude());
                                p2.setLongitude(getCruzamento(fields[4]).getGPS().getLongitude());
                            }
                        }
                        if (!st.contains(n1))
                            st.put(n1, st.size());
                        if (!st.contains(n2))
                            st.put(n2, st.size());

                        int v = st.get(n1);
                        int w = st.get(n2);

                        keys = new Node[st.size()];
                        for (Node name : st.keys()) {
                            if (st.get(name) != null)
                                keys[st.get(name)] = name;
                        }

                        float stWeight = distanciaEntrePontos(p1, p2);
                        EdgeDoubleWeighted e = new EdgeDoubleWeighted(v, w, stWeight);
                        ArrayEDW.add(e);

                        break;
                    case "Avenida":
                        Node n3 = new Node();
                        Node n4 = new Node();
                        Node n5 = new Node();
                        Node n6 = new Node();
                        Node n7 = new Node();
                        Point p3 = new Point();
                        Point p4 = new Point();
                        Point p5 = new Point();
                        Point p6 = new Point();
                        Point p7 = new Point();
                        for (String s : pois.keys()) {
                            if (pois.get(s).getNome().equals(fields[3])) {
                                n3.setN(getPoi(fields[3]));
                                p3.setLatitude(getPoi(fields[3]).getGPS().getLatitude());
                                p3.setLongitude(getPoi(fields[3]).getGPS().getLongitude());
                            }
                            if (pois.get(s).getNome().equals(fields[4])) {
                                n4.setN(getPoi(fields[4]));
                                p4.setLatitude(getPoi(fields[4]).getGPS().getLatitude());
                                p4.setLongitude(getPoi(fields[4]).getGPS().getLongitude());
                            }
                            if (pois.get(s).getNome().equals(fields[5])) {
                                n5.setN(getPoi(fields[5]));
                                p5.setLatitude(getPoi(fields[5]).getGPS().getLatitude());
                                p5.setLongitude(getPoi(fields[5]).getGPS().getLongitude());
                            }
                            if (pois.get(s).getNome().equals(fields[6])) {
                                n6.setN(getPoi(fields[6]));
                                p6.setLatitude(getPoi(fields[6]).getGPS().getLatitude());
                                p6.setLongitude(getPoi(fields[6]).getGPS().getLongitude());
                            }
                            if (pois.get(s).getNome().equals(fields[7])) {
                                n7.setN(getPoi(fields[7]));
                                p7.setLatitude(getPoi(fields[7]).getGPS().getLatitude());
                                p7.setLongitude(getPoi(fields[7]).getGPS().getLongitude());
                            }
                        }
                        for (String s : curvas.keys()) {
                            if (curvas.get(s).getNome().equals(fields[3])) {
                                n3.setN(getCurva(fields[3]));
                                p3.setLatitude(getCurva(fields[3]).getGPS().get(3).getLatitude());
                                p3.setLongitude(getCurva(fields[3]).getGPS().get(3).getLongitude());
                            }
                            if (curvas.get(s).getNome().equals(fields[4])) {
                                n4.setN(getCurva(fields[4]));
                                p4.setLatitude(getCurva(fields[4]).getGPS().get(3).getLatitude());
                                p4.setLongitude(getCurva(fields[4]).getGPS().get(3).getLongitude());
                            }
                            if (curvas.get(s).getNome().equals(fields[5])) {
                                n5.setN(getCurva(fields[5]));
                                p5.setLatitude(getCurva(fields[5]).getGPS().get(3).getLatitude());
                                p5.setLongitude(getCurva(fields[5]).getGPS().get(3).getLongitude());
                            }
                            if (curvas.get(s).getNome().equals(fields[6])) {
                                n6.setN(getCurva(fields[6]));
                                p6.setLatitude(getCurva(fields[6]).getGPS().get(3).getLatitude());
                                p6.setLongitude(getCurva(fields[6]).getGPS().get(3).getLongitude());
                            }
                            if (curvas.get(s).getNome().equals(fields[7])) {
                                n7.setN(getCurva(fields[7]));
                                p7.setLatitude(getCurva(fields[7]).getGPS().get(3).getLatitude());
                                p7.setLongitude(getCurva(fields[7]).getGPS().get(3).getLongitude());
                            }
                        }
                        for (String s : entroncamentos.keys()) {
                            if (entroncamentos.get(s).getNome().equals(fields[3])) {
                                n3.setN(getEntroncamento(fields[3]));
                                p3.setLatitude(getEntroncamento(fields[3]).getGPS().getLatitude());
                                p3.setLongitude(getEntroncamento(fields[3]).getGPS().getLongitude());
                            }
                            if (entroncamentos.get(s).getNome().equals(fields[4])) {
                                n4.setN(getEntroncamento(fields[4]));
                                p4.setLatitude(getEntroncamento(fields[4]).getGPS().getLatitude());
                                p4.setLongitude(getEntroncamento(fields[4]).getGPS().getLongitude());
                            }
                            if (entroncamentos.get(s).getNome().equals(fields[4])) {
                                n5.setN(getEntroncamento(fields[4]));
                                p5.setLatitude(getEntroncamento(fields[4]).getGPS().getLatitude());
                                p5.setLongitude(getEntroncamento(fields[4]).getGPS().getLongitude());
                            }
                            if (entroncamentos.get(s).getNome().equals(fields[4])) {
                                n6.setN(getEntroncamento(fields[4]));
                                p6.setLatitude(getEntroncamento(fields[4]).getGPS().getLatitude());
                                p6.setLongitude(getEntroncamento(fields[4]).getGPS().getLongitude());
                            }
                            if (entroncamentos.get(s).getNome().equals(fields[4])) {
                                n7.setN(getEntroncamento(fields[4]));
                                p7.setLatitude(getEntroncamento(fields[4]).getGPS().getLatitude());
                                p7.setLongitude(getEntroncamento(fields[4]).getGPS().getLongitude());
                            }
                        }
                        for (String s : cruzamentos.keys()) {
                            if (cruzamentos.get(s).getNome().equals(fields[3])) {
                                n3.setN(getCruzamento(fields[3]));
                                p3.setLatitude(getCruzamento(fields[3]).getGPS().getLatitude());
                                p3.setLongitude(getCruzamento(fields[3]).getGPS().getLongitude());
                            }
                            if (cruzamentos.get(s).getNome().equals(fields[4])) {
                                n4.setN(getCruzamento(fields[4]));
                                p4.setLatitude(getCruzamento(fields[4]).getGPS().getLatitude());
                                p4.setLongitude(getCruzamento(fields[4]).getGPS().getLongitude());
                            }
                            if (cruzamentos.get(s).getNome().equals(fields[4])) {
                                n5.setN(getCruzamento(fields[4]));
                                p5.setLatitude(getCruzamento(fields[4]).getGPS().getLatitude());
                                p5.setLongitude(getCruzamento(fields[4]).getGPS().getLongitude());
                            }
                            if (cruzamentos.get(s).getNome().equals(fields[4])) {
                                n6.setN(getCruzamento(fields[4]));
                                p6.setLatitude(getCruzamento(fields[4]).getGPS().getLatitude());
                                p6.setLongitude(getCruzamento(fields[4]).getGPS().getLongitude());
                            }
                            if (cruzamentos.get(s).getNome().equals(fields[4])) {
                                n7.setN(getCruzamento(fields[4]));
                                p7.setLatitude(getCruzamento(fields[4]).getGPS().getLatitude());
                                p7.setLongitude(getCruzamento(fields[4]).getGPS().getLongitude());
                            }
                        }
                        if (!st.contains(n3))
                            st.put(n3, st.size());
                        if (!st.contains(n4))
                            st.put(n4, st.size());
                        if (!st.contains(n5))
                            st.put(n5, st.size());
                        if (!st.contains(n6))
                            st.put(n6, st.size());
                        if (!st.contains(n7))
                            st.put(n7, st.size());

                        int v1 = st.get(n3);
                        int w1 = st.get(n4);

                        int v2 = st.get(n4);
                        int w2 = st.get(n5);

                        int v3 = st.get(n5);
                        int w3 = st.get(n6);

                        int v4 = st.get(n6);
                        int w4 = st.get(n7);

                        keys = new Node[st.size()];
                        for (Node name : st.keys()) {
                            if (st.get(name) != null)
                                keys[st.get(name)] = name;
                        }


                        float stWeight1 = distanciaEntrePontos(p3, p4);
                        float stWeight2 = distanciaEntrePontos(p4, p5);
                        float stWeight3 = distanciaEntrePontos(p5, p6);
                        float stWeight4 = distanciaEntrePontos(p6, p7);
                        EdgeDoubleWeighted e1 = new EdgeDoubleWeighted(v1, w1, stWeight1);
                        ArrayEDW.add(e1);
                        EdgeDoubleWeighted e2 = new EdgeDoubleWeighted(v2, w2, stWeight2);
                        ArrayEDW.add(e2);
                        EdgeDoubleWeighted e3 = new EdgeDoubleWeighted(v3, w3, stWeight3);
                        ArrayEDW.add(e3);
                        EdgeDoubleWeighted e4 = new EdgeDoubleWeighted(v4, w4, stWeight4);
                        ArrayEDW.add(e4);

                        break;
                }
            }
        }
    }

    public EdgeDoubleWeightedGraph graph() {
        return graph;
    }

    public ST<Node, Integer> getSt() {
        return st;
    }

    public void setSt(ST<Node, Integer> st) {
        this.st = st;
    }

    public Node[] getKeys() {
        return keys;
    }

    public void setKeys(Node[] keys) {
        this.keys = keys;
    }

    public EdgeDoubleWeightedGraph getGraph() {
        return graph;
    }

    public void setGraph(EdgeDoubleWeightedGraph graph) {
        this.graph = graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Node nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     *
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(Node s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(Node s) {
        return st.get(s);
    }
}
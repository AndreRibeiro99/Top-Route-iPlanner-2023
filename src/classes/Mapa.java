package classes;

import algs4.DijkstraSP;
import algs4.DirectedEdge;
import algs4.EdgeWeightedDigraph;
import algs4.RedBlackBST;

import java.util.*;

class Mapa {
    private List<Station> stations;
    private List<Connection> connections;
    private int n_vertex = 0;

    public Mapa() {
        stations = new ArrayList<>();
        connections = new ArrayList<>();
    }


    public void addStation(Station station) {
        stations.add(station);
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public EdgeWeightedDigraph gerarmap(Database db) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(db.contarConexoes());
        for (Integer id : db.getConnectionDB().keys()) {
            Connection conexao = db.getConnectionDB().get(id);
            int source = conexao.getSource().getId();
            int destination = conexao.getDestination().getId();
            double distance = db.calculateDistance(conexao.getSource().getX(), conexao.getSource().getY(), conexao.getDestination().getX(), conexao.getDestination().getY());

            n_vertex ++;
            DirectedEdge edge = new DirectedEdge(source, destination, distance);
            graph.addEdge(edge);
        }
        return graph;
    }

    public void shortdistance(EdgeWeightedDigraph graph, int source){
        DijkstraSP sp = new DijkstraSP(graph, source);

        // Exiba as distâncias mais curtas
        for (int v = 0; v < n_vertex; v++) {
            if (sp.hasPathTo(v)) {
                if (v != source) {
                    System.out.println("Distância mais curta de " + source + " para " + v + ": " + sp.distTo(v));
                    System.out.print("Caminho: ");
                    for (DirectedEdge edge : sp.pathTo(v)) {
                        System.out.print(edge.from() + "->" + edge.to() + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Não há caminho de " + source + " para " + v);
            }
        }
    }

    // Outros métodos para manipular o mapa
}
import java.util.*;

class Mapa {
    private List<Station> stations;
    private List<Connection> connections;

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

    // Outros métodos para manipular o mapa
}
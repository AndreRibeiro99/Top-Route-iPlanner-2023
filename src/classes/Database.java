import java.util.*;

class Database {
    private Mapa map;
    private Map<Station, List<Connection>> stationConnections;
    // Outras coleções de dados

    public Database() {
        map = new Mapa();
        stationConnections = new HashMap<>();
        // Inicialização de outras coleções de dados
    }

    public void addStation(Station station) {
        map.addStation(station);
        stationConnections.put(station, new ArrayList<>());
    }

    public void addConnection(Connection connection) {
        map.addConnection(connection);
        stationConnections.get(connection.getSource()).add(connection);
    }

    // Outros métodos para gerir as entidades da base de dados

    public List<Connection> getConnectionsByStation(Station station) {
        return stationConnections.get(station);
    }

    // Outros métodos para realizar pesquisas e consultas
}
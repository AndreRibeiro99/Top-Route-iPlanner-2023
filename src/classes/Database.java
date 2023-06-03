package classes;

import algs4.RedBlackBST;

import java.util.*;

class Database {

    private RedBlackBST<Integer, User> userDB;
    private RedBlackBST<Integer, Connection> connectionDB;
    private RedBlackBST<Integer, Station> stationDB;

    public Database() {
        userDB = new RedBlackBST<>();
        connectionDB = new RedBlackBST<>();
        stationDB = new RedBlackBST<>();
    }

    public void adicionarUsuario(User user) {
        userDB.put(user.getId(), user);
    }

    public void adicionarConexao(Connection conexao) {
        connectionDB.put(conexao.getId(), conexao);
    }

    public void adicionarStation(Station station) {
        stationDB.put(station.getId(), station);
    }


    public User buscarUser(Integer id) {
        if (userDB.contains(id)) {
            return userDB.get(id);
        } else {
            return null;
        }
    }

    public Connection buscarConexao(Station origem, Station destino) {
        for (Integer id : connectionDB.keys()) {
            Connection conexao = connectionDB.get(id);
            if (conexao.getSource() == origem && conexao.getDestination() == destino) {
                return connectionDB.get(id);
            }
        }
        return null;
    }


    public void listarUsers() {
        for (Integer id : userDB.keys()) {
            User user = userDB.get(id);
            System.out.println("Nome: " + user.getNome() + ", Preferencia: " + user.getPreferencia());
        }
    }

    public void listarConexoes() {
        for (Integer id : connectionDB.keys()) {
            Connection connection = connectionDB.get(id);
            System.out.println("Origem: " + connection.getSource() +
                    ", Destino: " + connection.getDestination() +
                    ", Distância: " + connection.getDistance() +
                    ", Preço: " + connection.getPrice() +
                    ", Tempo_inicio: " + connection.getStartdate() +
                    ", Tempo_fim: " + connection.getEnddate());
        }
    }

    public int contarConexoes() {
        int count = 0;

        for (Integer id : connectionDB.keys()) {
            count ++;
        }

        return count;
    }
    //R4 a)conexões encontradas
    //Pesquisar as conexoes de um user num determinado espaço de tempo
    public RedBlackBST<Date, Connection> buscarConexoesPorTempo(User user, Date dataInicio, Date dataFim) {
        RedBlackBST<Date, Connection> conexoesPeriodo = new RedBlackBST<>();

        RedBlackBST<Date, Connection> historicoUsuario = user.getHistorico();
        for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
            Connection conexao = historicoUsuario.get(data);
            conexoesPeriodo.put(data, conexao);
        }

        return conexoesPeriodo;
    }

    //R4 b)estações nao utilizadas
    //Lista de Stations não utilizadas por um user num determinado espaço de tempo
    public List<Station> buscarEstacoesNaoUtilizadas(User user, Date dataInicio, Date dataFim) {
        Set<Station> estacoesUtilizadas = new HashSet<>();

        RedBlackBST<Date, Connection> historicoUsuario = user.getHistorico();
        for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
            Connection conexao = historicoUsuario.get(data);
            estacoesUtilizadas.add(conexao.getSource());
            estacoesUtilizadas.add(conexao.getDestination());
        }

        List<Station> estacoesNaoUtilizadas = new ArrayList<>();
        Set<Station> estacoesRepetidas = new HashSet<>();

        for (Integer id : connectionDB.keys()) {
            Connection conexao = connectionDB.get(id);
            Station origem = conexao.getSource();
            Station destino = conexao.getDestination();

            if (!estacoesUtilizadas.contains(origem) && !estacoesRepetidas.contains(origem)) {
                estacoesNaoUtilizadas.add(origem);
                estacoesRepetidas.add(origem);
            }

            if (!estacoesUtilizadas.contains(destino) && !estacoesRepetidas.contains(destino)) {
                estacoesNaoUtilizadas.add(destino);
                estacoesRepetidas.add(destino);
            }
        }

        return estacoesNaoUtilizadas;
    }

    //R4 c)user que passaram na station
    //Lista de Conexoes utilizadas por um user num determinado espaço de tempo
    public List<User> userPorConexao(Station estacao, Date dataInicio, Date dataFim) {
        List<User> usuariosPassaram = new ArrayList<>();

        for (Integer id : userDB.keys()) {
            User usuario = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = usuario.getHistorico();

            for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
                Connection conexao = historicoUsuario.get(data);

                if (conexao.getSource() == estacao || conexao.getDestination() == estacao) {
                    usuariosPassaram.add(usuario);
                    break; // Não é necessário verificar mais conexões para este user
                }
            }
        }

        return usuariosPassaram;
    }

    //R4 d)top 3 users
    //Lista do top3 de users
    public List<User> Top3UserStations(Date dataInicio, Date dataFim) {
        Map<User, Set<Station>> usuariosEstacoesVisitadas = new HashMap<>();

        // Percorre o histórico de cada usuário e registra as estações visitadas
        for (Integer id : userDB.keys()) {
            User usuario = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = usuario.getHistorico();
            Set<Station> estacoesVisitadas = new HashSet<>();

            for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
                Connection conexao = historicoUsuario.get(data);
                estacoesVisitadas.add(conexao.getSource());
                estacoesVisitadas.add(conexao.getDestination());
            }

            usuariosEstacoesVisitadas.put(usuario, estacoesVisitadas);
        }

        // Ordena os usuários com base no número de estações visitadas (em ordem decrescente)
        List<User> topUsuarios = new ArrayList<>(usuariosEstacoesVisitadas.keySet());
        topUsuarios.sort((u1, u2) -> Integer.compare(usuariosEstacoesVisitadas.get(u2).size(), usuariosEstacoesVisitadas.get(u1).size()));

        // Retorna os top-3 usuários
        return topUsuarios.subList(0, Math.min(3, topUsuarios.size()));
    }





    //Gerar Relatorio Global
    public void gerarRelatorioGlobal(Date dataInicio, Date dataFim) {
        System.out.println("\n===== Relatório Global do Sistema =====");
        System.out.println("=== Lista de Stations ===");
        listarStations();
        System.out.println("\n=== Lista de Users ===");
        listarUsers();
        System.out.println("\n=== Lista de Ligações entre Stations ===");
        listarConexoes();
        System.out.println("\n=== Frequência de Utilização Diária das Stations ===");
        listarFreqDiariaStations(dataInicio, dataFim);
        System.out.println("\n=== Frequência de Utilização Semanal das Stations ===");
        listarFreqSemanalStations(dataInicio, dataFim);
    }

    public void listarStations() {
        for (Integer id : stationDB.keys()) {
            Station station = stationDB.get(id);
            System.out.println("ID: " + station.getId() + ", Nome: " + station.getName());
        }
    }

    public void listarFreqDiariaStations(Date dataInicio, Date dataFim) {
        Map<Station, Integer> frequenciaDiariaStations = new HashMap<>();

        for (Integer id : userDB.keys()) {
            User usuario = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = usuario.getHistorico();

            for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
                Connection conexao = historicoUsuario.get(data);
                Station origem = conexao.getSource();
                Station destino = conexao.getDestination();

                frequenciaDiariaStations.put(origem, frequenciaDiariaStations.getOrDefault(origem, 0) + 1);
                frequenciaDiariaStations.put(destino, frequenciaDiariaStations.getOrDefault(destino, 0) + 1);
            }
        }

        for (Station station : frequenciaDiariaStations.keySet()) {
            int frequenciaDiaria = frequenciaDiariaStations.get(station);
            System.out.println("Estação: " + station + ", Frequência Diária: " + frequenciaDiaria);
        }
    }

    public void listarFreqSemanalStations(Date dataInicio, Date dataFim) {
        Map<Station, Integer> frequenciaSemanalStations = new HashMap<>();

        for (Integer id : userDB.keys()) {
            User user = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = user.getHistorico();

            for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
                Connection conexao = historicoUsuario.get(data);
                Station origem = conexao.getSource();
                Station destino = conexao.getDestination();

                frequenciaSemanalStations.put(origem, frequenciaSemanalStations.getOrDefault(origem, 0) + 1);
                frequenciaSemanalStations.put(destino, frequenciaSemanalStations.getOrDefault(destino, 0) + 1);
            }
        }

        for (Station station : frequenciaSemanalStations.keySet()) {
            int frequenciaSemanal = frequenciaSemanalStations.get(station);
            System.out.println("Estação: " + station + ", Frequência Semanal: " + frequenciaSemanal);
        }
    }
    public void nearByStation(Station s, double distance) {
        List<Station> stationsWithinDistance = new ArrayList<>();

        // Percorrer todas as estações
        for (Integer id : stationDB.keys()) {
            Station station = stationDB.get(id);
            // Verificar se a Station é do tipo "aviao"
            if (station.getAviao() == 1) {
                // Calcular a distância entre pontos
                double dist = calculateDistance(s.getX(), s.getY(), station.getX(), station.getY());

                // Verificar se distância menor que a que foi determinada (distance)
                if (dist < distance) {
                    // Adicionar a estação à lista de resultados
                    stationsWithinDistance.add(station);
                }
            }
        }

        // Imprimir as estações encontradas
        System.out.println("\nEstações do tipo 'aviao' a menos de " + (distance/1000) +"Km da station " + s.getName() + " ("+ s.getY() + ", " + s.getY() + "):");
        for (Station station : stationsWithinDistance) {
            System.out.println(station.getName());
        }
    }

    public double calculateDistance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public RedBlackBST<Integer, Connection> getConnectionDB() {
        return connectionDB;
    }
}
package classes;

import algs4.In;
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


    public User buscarUsuario(Integer id) {
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

    // outras funções para remover, editar e listar a informação das coleções

    public void listarUsuarios() {
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

    public RedBlackBST<Date, Connection> buscarConexoesPorPeriodo(User user, Date dataInicio, Date dataFim) {
        RedBlackBST<Date, Connection> conexoesPeriodo = new RedBlackBST<>();

        RedBlackBST<Date, Connection> historicoUsuario = user.getHistorico();
        for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
            Connection conexao = historicoUsuario.get(data);
            conexoesPeriodo.put(data, conexao);
        }

        return conexoesPeriodo;
    }


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

    public List<User> usuariosPorConexao(Station estacao, Date dataInicio, Date dataFim) {
        List<User> usuariosPassaram = new ArrayList<>();

        for (Integer id : userDB.keys()) {
            User usuario = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = usuario.getHistorico();

            for (Date data : historicoUsuario.keys(dataInicio, dataFim)) {
                Connection conexao = historicoUsuario.get(data);

                if (conexao.getSource() == estacao || conexao.getDestination() == estacao) {
                    usuariosPassaram.add(usuario);
                    break; // Não é necessário verificar mais conexões para este usuário
                }
            }
        }

        return usuariosPassaram;
    }

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
        listarUsuarios();
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
            User usuario = userDB.get(id);
            RedBlackBST<Date, Connection> historicoUsuario = usuario.getHistorico();

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






}
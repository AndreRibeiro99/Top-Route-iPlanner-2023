package classes;

import algs4.RedBlackBST;
import algs4.Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Planejador de Viagens!");
        System.out.println("»-------------------------«");

        Database db = new Database();

        User user1 =  new User(1,"André", "barco");
        User user2 =  new User(2,"Frederico", "taxi");
        User user3 =  new User(3,"José", "aviao");
        User user4 =  new User(4,"Francisco", "aviao");

        db.adicionarUsuario(user1);
        db.adicionarUsuario(user2);
        db.adicionarUsuario(user3);
        db.adicionarUsuario(user4);

        System.out.println("Usuários:");
        db.listarUsuarios();
        System.out.println("\n");

        //Station(id, nome, aviao, barco, metro, taxi)
        Station estacao1 = new Station(0,"Porto",1,0,1,0); //aviao e metro
        Station estacao2 = new Station(1,"Paris", 0,1,0,1); //barco e taxi
        Station estacao3 = new Station(2,"Londres", 1,1,1,1); //aviao, barco, metro e taxi

        db.adicionarStation(estacao1);
        db.adicionarStation(estacao2);
        db.adicionarStation(estacao3);


        Date inicio = new Date(1,1,2023,1,0);
        Date fim = new Date(31,12,2023,23,59);

        Date startdate1 = new Date(10,2,2023,16,30);
        Date enddate1 = new Date(10,2,2023,18,30);

        Date startdate2 = new Date(12,2,2023,13,30);
        Date enddate2 = new Date(12,2,2023,9,30);

        Date startdate3 = new Date(23,4,2024,16,15);
        Date enddate3 = new Date(24,4,2024,19,15);

        Date startdate4 = new Date(13,2,2023,16,30);
        Date enddate4 = new Date(13,2,2023,18,30);

        Date startdate5 = new Date(14,2,2023,16,30);
        Date enddate5 = new Date(14,2,2023,18,30);

        Date startdate6 = new Date(15,2,2023,16,30);
        Date enddate6 = new Date(15,2,2023,18,30);

        // Criar conexões entre estações
        Connection conexao1 = new Connection(1, estacao1, estacao2, 100.0, 70.0, startdate1, enddate1);
        Connection conexao2 = new Connection(2, estacao2, estacao3, 50.0, 10.0, startdate2, enddate2);
        Connection conexao3 = new Connection(3, estacao1, estacao3, 200.0, 120.0, startdate3, enddate3);

        Connection conexao4 = new Connection(4, estacao1, estacao1, 140.0, 80.0, startdate1, enddate2);
        Connection conexao5 = new Connection(5, estacao2, estacao1, 60.0, 35.0, startdate2, enddate2);
        Connection conexao6 = new Connection(6, estacao3, estacao2, 230.0, 185.0, startdate3, enddate3);

        Connection conexao7 = new Connection(7, estacao3, estacao1, 140.0, 80.0, startdate4, enddate4);
        Connection conexao8 = new Connection(8, estacao3, estacao2, 60.0, 35.0, startdate5, enddate5);
        Connection conexao9 = new Connection(9, estacao3, estacao1, 230.0, 185.0, startdate6, enddate6);

        // Adicionar conexões à base de dados
        db.adicionarConexao(conexao1);
        db.adicionarConexao(conexao2);
        db.adicionarConexao(conexao3);
        db.adicionarConexao(conexao4);
        db.adicionarConexao(conexao5);
        db.adicionarConexao(conexao6);
        db.adicionarConexao(conexao7);
        db.adicionarConexao(conexao8);
        db.adicionarConexao(conexao9);

        // Listar conexões
        System.out.println("Conexões:");
        db.listarConexoes();
        System.out.println("\n");

        user1.addHistorico(conexao1.getStartdate(), conexao1);
        user1.addHistorico(conexao2.getStartdate(), conexao2);
        user1.addHistorico(conexao3.getStartdate(), conexao3);
        user2.addHistorico(conexao1.getStartdate(), conexao1);
        user2.addHistorico(conexao3.getStartdate(), conexao3);
        user3.addHistorico(conexao1.getStartdate(), conexao1);
        user3.addHistorico(conexao2.getStartdate(), conexao2);
        user4.addHistorico(conexao3.getStartdate(), conexao3);

        user1.addHistorico(conexao7.getStartdate(), conexao7);
        user1.addHistorico(conexao8.getStartdate(), conexao8);
        user1.addHistorico(conexao9.getStartdate(), conexao9);
        user2.addHistorico(conexao9.getStartdate(), conexao9);
        user2.addHistorico(conexao9.getStartdate(), conexao9);
        user3.addHistorico(conexao7.getStartdate(), conexao7);
        user3.addHistorico(conexao7.getStartdate(), conexao7);
        user4.addHistorico(conexao6.getStartdate(), conexao6);


        RedBlackBST<Date, Connection> conexoesPeriodo = db.buscarConexoesPorPeriodo(user3, inicio, fim);

        //R4 a)conexões encontradas
        System.out.println("Conexões encontradas no Periodo de Tempo: ");
        for (Date data : conexoesPeriodo.keys()) {
            Connection conexao = conexoesPeriodo.get(data);
            System.out.println("Conexao " + conexao.getId() + ":" + conexao + ", Data: " + data);
        }
        System.out.println("\n");

        //R4 b)estações nao utilizadas
        List<Station> estacoesNaoUtilizadas = db.buscarEstacoesNaoUtilizadas(user2, inicio, fim);
        System.out.println("Estações não utilizadas pelo user: " );
        for (Station estacao : estacoesNaoUtilizadas) {
            System.out.println(estacao.getName());
        }
        System.out.println("\n");

        //R4 c)user que passaram na station
        List<User> usuariosPorConexao = db.usuariosPorConexao(estacao3, inicio, fim);
        System.out.println("Users que passaram pela Station: ");
        for (User user : usuariosPorConexao) {
            System.out.println(user.getNome());
        }
        System.out.println("\n");

        //R4 d)top 3 users
        List<User> Top3UserStations = db.Top3UserStations(startdate3, enddate3);
        System.out.println("Top3 Users que visitaram mais Stations: ");
        for (User user : Top3UserStations) {
            System.out.println(user.getNome());
        }

        db.gerarRelatorioGlobal(startdate1, enddate6); //dia 10 a dia 15

        // Mapeamento
        Graph graph = new Graph(3); // N de vértices

        HashMap<Station, Integer> stationToVertex = new HashMap<>();
        stationToVertex.put(estacao1, 0);
        stationToVertex.put(estacao2, 1);
        stationToVertex.put(estacao3, 2);

        // Adição das conexões como arestas do grafo
        graph.addEdge(stationToVertex.get(conexao1.getSource()), stationToVertex.get(conexao1.getDestination()));
        graph.addEdge(stationToVertex.get(conexao2.getSource()), stationToVertex.get(conexao2.getDestination()));
        graph.addEdge(stationToVertex.get(conexao3.getSource()), stationToVertex.get(conexao3.getDestination()));


        /*Connection search = db.buscarConexao(estacao1, estacao2);
        System.out.println(search);*/
    }
}
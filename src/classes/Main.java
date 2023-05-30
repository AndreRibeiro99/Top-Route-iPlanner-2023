package classes;

import algs4.RedBlackBST;

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

        Station estacao1 = new Station(0,"Porto","aviao");
        Station estacao2 = new Station(1,"Paris", "barco");
        Station estacao3 = new Station(2,"Londres", "taxi");

        db.adicionarStation(estacao1);
        db.adicionarStation(estacao2);
        db.adicionarStation(estacao3);


        Date inicio = new Date(1,1,2023,1,0);
        Date fim = new Date(31,12,2023,23,59);

        Date startdate1 = new Date(10,2,2023,16,30);
        Date enddate1 = new Date(10,2,2023,18,30);

        Date startdate2 = new Date(28,3,2023,16,30);
        Date enddate2 = new Date(3,4,2023,18,30);

        Date startdate3 = new Date(23,4,2024,16,30);
        Date enddate3 = new Date(25,4,2024,18,30);

        // Criar conexões entre estações
        Connection conexao1 = new Connection(1, estacao1, estacao2, 100.0, 70.0, startdate1, enddate1);
        Connection conexao2 = new Connection(2, estacao2, estacao3, 50.0, 10.0, startdate2, enddate2);
        Connection conexao3 = new Connection(3, estacao1, estacao3, 200.0, 120.0, startdate3, enddate3);

        Connection conexao4 = new Connection(4, estacao1, estacao1, 140.0, 80.0, startdate1, enddate2);
        Connection conexao5 = new Connection(5, estacao2, estacao1, 60.0, 35.0, startdate2, enddate2);
        Connection conexao6 = new Connection(6, estacao3, estacao2, 230.0, 185.0, startdate3, enddate3);

        // Adicionar conexões à base de dados
        db.adicionarConexao(conexao1);
        db.adicionarConexao(conexao2);
        db.adicionarConexao(conexao3);
        db.adicionarConexao(conexao4);
        db.adicionarConexao(conexao5);
        db.adicionarConexao(conexao6);

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


        RedBlackBST<Date, Connection> conexoesPeriodo = db.buscarConexoesPorPeriodo(user3, inicio, fim);

        // Exibir as conexões encontradas
        System.out.println("Conexões encontradas no Periodo de Tempo: ");
        for (Date data : conexoesPeriodo.keys()) {
            Connection conexao = conexoesPeriodo.get(data);
            System.out.println("Conexao " + conexao.getId() + ":" + conexao + ", Data: " + data);
        }
        System.out.println("\n");

        List<Station> estacoesNaoUtilizadas = db.buscarEstacoesNaoUtilizadas(user2, inicio, fim);
        System.out.println("Estações não utilizadas pelo user: " );
        for (Station estacao : estacoesNaoUtilizadas) {
            System.out.println(estacao.getName());
        }
        System.out.println("\n");

        List<User> usuariosPorConexao = db.usuariosPorConexao(estacao3, inicio, fim);
        System.out.println("Users que passaram pela Station: ");
        for (User user : usuariosPorConexao) {
            System.out.println(user.getNome());
        }
        System.out.println("\n");

        List<User> Top3UserStations = db.Top3UserStations(startdate3, enddate3);
        System.out.println("Top3 Users que visitaram mais Stations: ");
        for (User user : Top3UserStations) {
            System.out.println(user.getNome());
        }

        db.gerarRelatorioGlobal();

        /*Connection search = db.buscarConexao(estacao1, estacao2);
        System.out.println(search);*/
    }
}
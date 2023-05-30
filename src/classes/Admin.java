package classes;

import java.util.*;

public class Admin {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[ADMIN] Top Route Planner!");
        System.out.println("»-------------------------«");


        while (true) {
            System.out.println("Opções:");
            System.out.println("1. Adicionar estação");
            System.out.println("2. Adicionar conexão");
            System.out.println("3. Pesquisar conexões por estação");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //addStation();
                    break;
                case 2:
                    //addConnection();
                    break;
                case 3:
                    //searchConnectionsByStation();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }
    }

}

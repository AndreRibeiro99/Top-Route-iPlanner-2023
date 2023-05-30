import java.util.*;

public class TravelPlanner {
    private Map<String, Integer> cities;

    public TravelPlanner() {
        cities = new HashMap<>();
        cities.put("Porto", 1);
        cities.put("Paris", 2);
        cities.put("Madrid", 3);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Top Route Planner!");
        System.out.println("»-------------------------«");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Verificar disponibilidade de voos");
            System.out.println("2 - Reservar um voo");
            System.out.println("3 - Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (choice) {
                case 1:
                    checkFlightAvailability(scanner);
                    break;
                case 2:
                    reserveFlight(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private void checkFlightAvailability(Scanner scanner) {
        System.out.println("\nVerificar disponibilidade de voos");

        System.out.print("Origem: ");
        String source = scanner.nextLine();

        System.out.print("Destino: ");
        String destination = scanner.nextLine();

        if (cities.containsKey(source) && cities.containsKey(destination)) {
            int sourceCode = cities.get(source);
            int destinationCode = cities.get(destination);

            System.out.println("Voos disponíveis de " + source + " para " + destination);
            System.out.println("Código de origem: " + sourceCode);
            System.out.println("Código de destino: " + destinationCode);
        } else {
            System.out.println("Cidades de origem ou destino inválidas.");
        }
    }

    private void reserveFlight(Scanner scanner) {
        System.out.println("\nReservar um voo");

        System.out.print("Código de origem: ");
        int sourceCode = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.print("Código de destino: ");
        int destinationCode = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Lógica para reservar o voo

        System.out.println("Voo reservado de " + getSource(sourceCode) + " para " + getSource(destinationCode));
    }

    private String getSource(int code) {
        for (Map.Entry<String, Integer> entry : cities.entrySet()) {
            if (entry.getValue() == code) {
                return entry.getKey();
            }
        }
        return null;
    }
}
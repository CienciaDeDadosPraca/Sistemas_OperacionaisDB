import java.util.HashMap;
import java.util.Scanner;

public class AvestruzDB {

    public static void main(String[] args) {
        HashMap<Integer, String> avestruzDB = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite um comando:");
            String command = scanner.nextLine();

            if (command.equals("sair")) {
                break;
            }
            switch (command) {
                case "inserir":
                    int key = Integer.parseInt(scanner.nextLine());
                    String value = scanner.nextLine();
                    avestruzDB.put(key, value);
                    break;
                case "remover":
                    key = Integer.parseInt(scanner.nextLine());
                    avestruzDB.remove(key);
                    break;
                case "buscar":
                    key = Integer.parseInt(scanner.nextLine());
                    value = avestruzDB.get(key);
                    System.out.println(value);
                    break;
                default:
                    System.out.println("Comando desconhecido: " + command);
            }
        }
        scanner.close();
    }
}

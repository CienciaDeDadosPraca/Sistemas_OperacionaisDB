import java.util.HashMap;
import java.util.Scanner;

public class AvestruzDB {
    private static final String FILE_NAME = "avestruz_db.txt";
    
    public static void main(String[] args) {
        HashMap<Integer, String> avestruzDB = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite um comando:");
            String command = scanner.nextLine();
            String[] comando = command.split(" ");
            String comand = comando[0];

            String[] ChaveValor = comando[1].split(",");
            Integer key = Integer.valueOf(ChaveValor[0]);
            


            if (comando[0].equals("sair")) {
                break;
            }
            switch (comand) {
                case "inserir":
                    String value = ChaveValor[1];
                    avestruzDB.put(key, value);
                    System.out.println("Inserido");
                    break;
                case "remover":
                    avestruzDB.remove(key);
                    break;
                case "buscar":
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

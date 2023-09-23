import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class AvestruzDB {
    
    private static final String ARQUIVO_DB = "avestruz.txt";

    public static void main(String[] args) throws IOException {
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

            if (comand.equals("inserir")) {
                String value = ChaveValor[1];
                avestruzDB.put(key, value);

                salvar(avestruzDB);

                System.out.println("Inserido");
            } else if (comand.equals("remover")) {
                avestruzDB.remove(key);

                salvar(avestruzDB);

                System.out.println("Removido");
            } else if (comand.equals("buscar")) {
                String value = avestruzDB.get(key);

                System.out.println(value);
            } else {
                System.out.println("Comando desconhecido: " + command);
            }
        }
        scanner.close();
    }
    
    private static void salvar(HashMap<Integer, String> avestruzDB) throws IOException {
        File arquivo = new File(ARQUIVO_DB);

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        FileWriter writer = new FileWriter(arquivo);

        for (Integer key : avestruzDB.keySet()) {
            String value = avestruzDB.get(key);

            writer.write(key + "," + value + "\n");
        }

        writer.close();
    }
}

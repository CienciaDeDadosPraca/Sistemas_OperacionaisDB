import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
    
public class KeyValueDB {
    private static final String FILE_NAME = "Database.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KeyValueDatabase keyValueDB = new KeyValueDatabase(FILE_NAME);

        while (true) {
            System.out.println("Digite um comando (inserir, remover, buscar, sair):");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("sair")) {
                break;
            } else if (command.equals("inserir") || command.equals("remover") || command.equals("buscar")) {
                System.out.println("Digite a chave e o valor (ex: chave,valor):");
                String input = scanner.nextLine().trim();
                String[] keyValue = input.split(",");
                
                if (keyValue.length != 2) {
                    System.out.println("Formato inválido. Use o formato 'chave,valor'.");
                    continue;
                }
                
                int key = Integer.parseInt(keyValue[0]);
                String value = keyValue[1];
                
                try {
                    if (command.equals("inserir")) {
                        keyValueDB.insert(key, value);
                    } else if (command.equals("remover")) {
                        keyValueDB.remove(key);
                    } else if (command.equals("buscar")) {
                        String result = keyValueDB.search(key);
                        System.out.println("Valor encontrado: " + result);
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
                }
            } else {
                System.out.println("Comando desconhecido: " + command);
            }
        }

        scanner.close();
    }
}

class KeyValueDatabase {
    private String fileName;
    private HashMap<Integer, String> data;

    public KeyValueDatabase(String fileName) {
        this.fileName = fileName;
        this.data = new HashMap<>();
        loadFromFile();
    }

    public void insert(int key, String value) throws IOException {
        data.put(key, value);
        saveToFile();
        System.out.println("Inserido.");
    }

    public void remove(int key) throws IOException {
        data.remove(key);
        saveToFile();
        System.out.println("Removido.");
    }

    public String search(int key) {
        return data.get(key);
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(",");
                if (keyValue.length == 2) {
                    int key = Integer.parseInt(keyValue[0]);
                    String value = keyValue[1];
                    data.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    private void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer key : data.keySet()) {
                String value = data.get(key);
                writer.write(key + "," + value);
                writer.newLine();
            }
        }
    }
}

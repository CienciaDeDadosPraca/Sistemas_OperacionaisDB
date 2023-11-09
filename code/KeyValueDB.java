import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class KeyValueDB {
    private static final String FILE_NAME = "Database.txt";

    public static void main(String[] args) {
        KeyValueDatabase keyValueDB = new KeyValueDatabase(FILE_NAME);
        String command = args[0];
        String[] keyValue = args[1].split(",");
        


            if (command.equals("inserir") || command.equals("remover") || command.equals("buscar") || command.equals("atualizar")) {
                if (keyValue.length != 2) {
                    System.out.println("Formato inválido. Use o formato 'chave,valor'.");
                }
            
                int key = Integer.parseInt(keyValue[0]);
                
                try {
                    if (command.equals("inserir")) {
                        String value = keyValue[1];
                        keyValueDB.tazmaniacrazy(key, value);
                    } else if (command.equals("remover")) {
                        keyValueDB.remove(key);
                    } else if (command.equals("buscar")) {
                        String result = keyValueDB.search(key);
                        System.out.println("Valor encontrado: " + result);
                    } else if (command.equals("atualizar")) {
                        String value = keyValue[1];
                        keyValueDB.update(key, value);
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("O valor de entrada para a chave deve ser um número inteiro.");
                }
            } else {
                System.out.println("Comando desconhecido: " + command);
            }
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

    public void tazmaniacrazy(int key, String value) throws IOException {
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

    public void update(int key, String newValue) throws IOException {
        if (data.containsKey(key)) {
            data.put(key, newValue);
            saveToFile();
            System.out.println("Atualizado.");
        } else {
            System.out.println("Chave não encontrada.");
        }
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

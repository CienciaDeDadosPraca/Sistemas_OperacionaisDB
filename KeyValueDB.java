import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class KeyValueDB {

    public String FILE_NAME;  // Define o nome do arquivo para armazenamento do banco de dados
    public KeyValueDatabase keyValueDB;
    
    public KeyValueDB(String nomeCliente){
        this.FILE_NAME = nomeCliente;
        this.keyValueDB = new KeyValueDatabase(FILE_NAME); // Inicializa o objeto KeyValueDatabase
    }

    public String conversaComBD(String entrada) {
        // Analisa os argumentos da linha de comando
        String[] args = entrada.split(" ");
        String command = args[0];
        String[] keyValue = args[1].split(",");
        String resultado = "drg";


        // Lida com diferentes operações de banco de dados com base no comando
        if (command.equals("inserir") || command.equals("remover") || command.equals("buscar") || command.equals("atualizar")) {
            if (keyValue.length != 2) { // Valida o número de pares de valores-chave
                resultado = "Formato inválido. Use o formato 'chave,valor'.";
            } else {
                int key = Integer.parseInt(keyValue[0]); // Extrai a chave da entrada

                try { // Lida com possíveis exceções de E/S
                    if (command.equals("inserir")) { // Insire um novo par de valores-chave
                        String value = keyValue[1];
                        resultado = keyValueDB.insert(key, value);
                    } else if (command.equals("remover")) { // Remove um par de valores-chave
                        keyValueDB.remove(key);
                    } else if (command.equals("buscar")) { // Procura um valor com base na chave
                        String result = keyValueDB.search(key);
                        resultado = ("Valor encontrado: " + result);
                    } else if (command.equals("atualizar")) { // Atualiza o valor de uma chave existente
                        String value = keyValue[1];
                        resultado = keyValueDB.update(key, value);
                    }
                } catch (IOException e) {
                    resultado = ("Erro ao acessar o arquivo: " + e.getMessage()); // Lida com erros de IO
                } catch (NumberFormatException e) {
                    resultado = "O valor de entrada para a chave deve ser um número inteiro."; // Lida com formato de chave inválido
                }
            }
        } else {
            resultado = ("Comando desconhecido: " + command); // Lida com comandos inválidos
        }
        return resultado;
    }
}

class KeyValueDatabase {

    private String fileName; // Armazena o nome do arquivo do banco de dados
    private HashMap<Integer, String> data; // Armazena os pares chave-valor em um HashMap

    public KeyValueDatabase(String fileName) {
        this.fileName = fileName;
        this.data = new HashMap<>(); // Inicializa o HashMap
        loadFromFile(); // Carrega os dados do banco de dados do arquivo
    }

    public String insert(int key, String value) throws IOException {
        data.put(key, value); // Insire o par chave-valor no HashMap
        saveToFile(); // Salva os dados atualizados no arquivo
        String resultado = "Inserido."; // Imprime mensagem de sucesso
        return resultado;
    }

    public String remove(int key) throws IOException {
        data.remove(key); // Remove o par chave-valor do HashMap
        saveToFile(); // Salva os dados atualizados no arquivo
        String resultado = "Removido."; // Imprime mensagem de sucesso
        return resultado;
    }

    public String search(int key) {
        return data.get(key); // Retorna o valor associado à chave
    }

    public String update(int key, String newValue) throws IOException {
        if (data.containsKey(key)) { // Verifica se a chave existe
            data.put(key, newValue); // Atualiza o valor da chave existente
            saveToFile(); // Salva os dados atualizados no arquivo
            String resultado = "Atualizado."; // Imprime mensagem de sucesso
            return resultado;
        } else {
            String resultado = "Chave não encontrada."; // Imprime mensagem de chave não encontrada
            return resultado;
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) { // Lê o arquivo do banco de dados
            String line;
            while ((line = reader.readLine()) != null) { // Lê cada linha
                String[] keyValue = line.split(","); // Divide a linha em chave e valor
                if (keyValue.length == 2) { // Verifica se a linha tem um formato válido
                    int key = Integer.parseInt(keyValue[0]); // Converte chave em número inteiro
                    String value = keyValue[1]; // Extrai valor
                    data.put(key, value); // Adiciona par chave-valor ao HashMap
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage()); // Lida com erros de carregamento de arquivos
        }
    }

    private void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { // Reescreve o arquivo de banco de dados
            for (Integer key : data.keySet()) { // Itera sobre chaves
                String value = data.get(key); // Pega o valor associado
                writer.write(key + "," + value); // Grava par de valores-chave no arquivo
                writer.newLine(); // Insire uma nova linha após cada entrada
            }
        }
    }
}
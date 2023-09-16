import java.util.HashMap;

public class AvestruzDB {

    private HashMap<Integer, String> TabelaChaveValor;

    public AvestruzDB() {
        TabelaChaveValor = new HashMap<>();
    }
    // Comando de insert
    public void insert(int key, String value) {
        TabelaChaveValor.put(key, value);
    }
    // Comando de remove
    public void remove(int key) {
        TabelaChaveValor.remove(key);
    }
    // Comando de busca
    public String search(int key) {
        return TabelaChaveValor.get(key);
    }
    // Comando de busca
    public void update(int key, String newValue) {
        TabelaChaveValor.put(key, newValue);
    }

    public static void main(String[] args) {
        AvestruzDB avestruzDB = new AvestruzDB();

        // Ler os commando na CLI 
        // Algo ta dando errado
        String command = args[0];
        int key = Integer.parseInt(args[1]);
        String value = null;
        if (command.equals("--insert")) {
            value = args[2];
        }

        // Switch pra verificar cada comando
        switch (command) {
            case "--insert":
                avestruzDB.insert(key, value);
                break;
            case "--remove":
                avestruzDB.remove(key);
                break;
            case "--search":
                value = avestruzDB.search(key);
                System.out.println(value);
                break;
            case "--update":
                value = args[2];
                avestruzDB.update(key, value);
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }
}

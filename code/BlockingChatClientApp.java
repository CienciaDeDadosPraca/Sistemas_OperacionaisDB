import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 Cliente de chat usando a classe Socket com requisições bloqueantes (blocking).
 Implementa a interface Runnable para ser executado em uma nova thread.
*/
public class BlockingChatClientApp implements Runnable {
    
    // Endereço IP ou nome DNS do servidor. IP padrão.
    public static final String SERVER_ADDRESS = "127.0.0.1";
    
    // Objeto para capturar dados do teclado e permitir que o usuário digite mensagens.
    private final Scanner scanner;
    
    // Armazena dados do cliente (como login) e o Socket para conexão com o servidor.
    private ClientSocket clientSocket;
    
    /*
     Método principal da aplicação cliente.
     Pode-se executar várias instâncias desta classe para ter vários clientes conectados ao servidor.
    */
    public static void main(String[] args) {
        try {
            BlockingChatClientApp client = new BlockingChatClientApp();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
    
    // Instancia um cliente, realizando operações mínimas necessárias.
    public BlockingChatClientApp(){
        scanner = new Scanner(System.in);
    }

    /*
     Inicia o cliente, conectando ao servidor e entrando no loop de envio e recebimento de mensagens.
     Lança IOException quando ocorre um erro de I/O, como falha ao conectar ao servidor.
    */
    private void start() throws IOException {
        final Socket socket = new Socket(SERVER_ADDRESS, BlockingChatServerApp.PORT);
        clientSocket = new ClientSocket(socket);
        System.out.println(
            "Cliente conectado ao servidor no endereço " + SERVER_ADDRESS +
            " e porta " + BlockingChatServerApp.PORT);

        login();

        new Thread(this).start(); // Inicia nova thread para recebimento de mensagens
        messageLoop();
    }

    /*
     Realiza o login no sistema, enviando o login para o servidor.
     O servidor recebe o login do cliente assim que ele se conecta.
    */
    private void login() {
        System.out.print("Digite seu login: ");
        final String login = scanner.nextLine();
        clientSocket.setLogin(login);
        clientSocket.sendMsg(login);
    }

    /*
     Inicia o loop de envio e recebimento de mensagens.
     O loop é interrompido quando o usuário digita "sair".
    */
    private void messageLoop() {
        String msg;
        do {
            System.out.print("Digite uma mensagem (ou 'sair' para encerrar): ");
            msg = scanner.nextLine();
            clientSocket.sendMsg(msg);
        } while(!"sair".equalsIgnoreCase(msg));
        clientSocket.close();
    }

    /*
     Aguarda mensagens do servidor enquanto o socket não é fechado e o cliente não recebe uma mensagem nula.
     Se uma mensagem nula é recebida, indica erro na conexão com o servidor.
     Este método implementa a interface Runnable para ser executado em uma nova thread.
    */
    @Override
    public void run() {
        String msg;
        while((msg = clientSocket.getMessage()) != null) {
            System.out.println(msg);
        }
    }
}

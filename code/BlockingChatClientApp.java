
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Aplicação cliente de chat utilizando a classe Socket que permite apenas requisições bloqueantes (blocking).
 * 
 * A classe implementa a interface Runnable.
 * Com isto, o método run() foi incluído
 * para que ele seja executado por uma nova thread que criamos dentro do messageLoop().
 * O método run() fica em loop aguardando mensagens do servidor.
 */
public class BlockingChatClientApp implements Runnable {
    /**
     * Endereço IP ou nome DNS para conectar no servidor. IP padrão.
     * O número da porta é obtido diretamente da constante BlockingChatServerApp#PORT na classe do servidor.
     */
    public static final String SERVER_ADDRESS = "127.0.0.1";

    /**
     * Objeto para capturar dados do teclado e assim permitir que o usuário digite mensagens a enviar.
     */
    private final Scanner scanner;
    
    /**
     * Objeto que armazena alguns dados do cliente (como o login)
     * e o Socket que representa a conexão do cliente com o servidor.
     */
    private ClientSocket clientSocket;
    
    /**
     * Executa a aplicação cliente.
     * Pode-se executar quantas instâncias desta classe desejar.
     * Isto permite ter vários clientes conectados e interagindo por meio do servidor.
     * 
     * param args parâmetros de linha de comando (não usados para esta aplicação)
     */

     public static void main(String[] args) {
        try {
            BlockingChatClientApp client = new BlockingChatClientApp();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
    
    /**
     * Instancia um cliente, realizando o mínimo de operações necessárias.
     */
    public BlockingChatClientApp(){
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia o cliente, conectando ao servidor e
     * entrando no loop de envio e recebimento de mensagens.
     * throws IOException quando um erro de I/O (Input/Output, ou seja,
     * Entrada/Saída) ocorrer, como quando o cliente tentar
     * conectar no servidor, mas o servidor não está aberto
     * ou o cliente não tem acesso à rede.
     */
    private void start() throws IOException {
        final Socket socket = new Socket(SERVER_ADDRESS, BlockingChatServerApp.PORT);
        clientSocket = new ClientSocket(socket);
        System.out.println(
            "Cliente conectado ao servidor no endereço " + SERVER_ADDRESS +
            " e porta " + BlockingChatServerApp.PORT);

        login();

        new Thread(this).start();
        messageLoop();
    }

    /**
     * Executa o login no sistema, enviando o login digitado para o servidor.
     * A primeira mensagem que o servidor receber após um cliente conectar é então o login daquele cliente.
     */
    private void login() {
        System.out.print("Digite seu login: ");
        final String login = scanner.nextLine();
        clientSocket.setLogin(login);
        clientSocket.sendMsg(login);
    }

    /**
     * Inicia o loop de envio e recebimento de mensagens.
     * O loop é interrompido quando o usuário digitar "sair".
     */
    private void messageLoop() {
        String msg;
        do {
            System.out.print("Digite uma msg (ou 'sair' para encerrar): ");
            msg = scanner.nextLine();
            clientSocket.sendMsg(msg);
        } while(!"sair".equalsIgnoreCase(msg));
        clientSocket.close();
    }

    /**
     * Aguarda mensagens do servidor enquanto o socket não for fechado
     * e o cliente não receber uma mensagem null.
     * Se uma mensagem null for recebida, é porque ocorreu erro na conexão com o servidor.
     * Neste caso, podemos encerrar a espera por novas mensagens.
     * O método tem esse nome pois estamos implementando a interface Runnable
     * na declaração da classe, o que nos obriga a incluir um método com tal nome
     * na nossa classe. Com isto, permitimos que tal método possa ser executado
     * por uma nova thread que criamos no método messageLoop(), o que facilita a criação da thread.
     * 
     */
    @Override
    public void run() {
        String msg;
        while((msg = clientSocket.getMessage())!=null) {
            System.out.println(msg);
        }
    }
}

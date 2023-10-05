import java.net.ServerSocket;
import java.io.IOException


public class chatServer{
    
    // as aplicações possuem portas exclusivas
    // Caso a porta esteja sendo usada devemos setar outra
    public final int PORTA = 4000;

    // a classe serverSocket instancia um socket numa app servidora
    // e ira ficar escutando a porta indicada
    private ServerSocket serverSocket;

    public void start() throws IOException{
        // construtor com a porta especificada. 
        // Caso haja outro servidor nessa porta, pode ser lançada uma exceção
        System.out.println("O servidor foi iniciado na porta " +PORTA);
        serverSocket = new ServerSocket(PORTA);

    }

    // o método cliente connect loop é um loop infinito que fica aguardando as mensagem do cliente
    private void clientConnectionLoop(){
        while(true){
            Socket clientSocket = serverSocket.accept();
        }
    }

    public static void main(String[] args){
        // a exceção lançada no método start deve ser tratada no main
        try{
            chatServer server = new chatServer();
            server.start();
        }
         catch (IOException ex){
            System.out.println("Erro ao inciar o servidor: ",ex.getMessage());
         }
         System.out.println("O servidor foi finalizado");
    }
}

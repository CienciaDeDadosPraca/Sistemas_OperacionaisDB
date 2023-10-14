import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/*O CONTEUDO DE SOCKET ESTÁ HOSPEDADO NO link
 * https://www.youtube.com/watch?v=MtAfYUW7fJ4&t=983s
 * MANOEL CAMPOS DEV 1 Criando aplicação CLIENTE/SERVIDOR de CHAT em JAVA usando SOCKETS e THREADS
 */


/*PARA TERMINAR A APLICAÇÃO SERVIDOR DEVE SER INTERROMPIDO O PROCESSO E NÃO APENAS FECHALO 
 * CASO CONTRÁRIO O SERVIDOR CONTINUARÁ CONECTADO E A PORTA SENDO DISPONIBILIZADA PARA AQUELE SERVIDO
 * DEVE-SE FECHAR A IDE E INICIAR DENOVO A EXECUÇÃO DOS PROGRAMAS 
 */
// Duas aplicações no mesmo projeto

class ChatServer{

    /*A constante PORT é a porta no Sistema operacional onde será iniciada a conexão 
     * Deve ser uma constante publica para que a classe chatClient interaja com esta
     * A constante é estática devido à sua imutabilidade
    */
    public static final int PORT = 4000;

    private ServerSocket serverSocket;

    public void start() throws IOException{
        
        System.out.println("Servidor iniciado na porta: " + PORT);

        // a função irá iniciar um socket na porta definida
        serverSocket = new ServerSocket(PORT);
        /*O loop é chamado no start para que inicie a espera da solitação dos clientes */
        clientConnectionLoop();

    }

    /*O servidor deve possuir um método para ficar em escuta o tempo inteiro esperando a solitação do cliente 
     * Ele fica aguardando as conexões dos clientes. É um loop infinito
    */
    private void clientConnectionLoop() throws IOException{
        while(true){
            /*O metodo accept retorna um socket do cliente  */
            Socket clientSocket = serverSocket.accept();
            /*Aqui estamos obtendo o endereço remoto do cliente conectado
             * A mensagem irá aparecer a porta que o cliente está utilizando para receber o retorno do server
             * O cliente conecta na porta 4000 
             * O servidor irá  utiizar a porta passada pelo getRemoteSocketAdress, essa porta é definida pelo Sistema Operacional
             */


            /* Na classe servidor, devemos ter um objeto "in" para receber a mensagem transmitida pelo cliene através do "out" 
              * Aqui possuimos o mesmo problema dos bytes
              * Temos que utilizar a mesma lógica que adotamos na classe cliente
              * Criaremos um bufferedRead e atribuiremos um novo atributo na classe Server  
              * Copiamos o código do cliente e onde temos Output substituimos por Input
              * onde está writer trocamos por reader
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            /*Lê até encontrar um \n */
            String msg = in.readLine();
            System.out.println("Mensagem recebida do cliente "+clientSocket.getRemoteSocketAddress()+ ": " +msg);

            clientSocket.getInputStream();


            System.out.println("Cliente: "+clientSocket.getRemoteSocketAddress());
        }
    }

    public static void main(String[] args){
        ChatServer server = new ChatServer();
        try {
            server.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("erro ao iniciar ao servidor: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Servidor finalizado!");

        
    }
}

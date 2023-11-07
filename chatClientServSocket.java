import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


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
    private final List<clientSocket> clients = new LinkedList<>();

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
            /*O método accept é uma operação bloqueante. O servidor fica parado
             * Ao terminar o loop o método fica esperando uma outra conexão
             */
            clientSocket clientSocket = new clientSocket(serverSocket.accept());

            /*apos criar o socket iremos armazenalo em uma lista */
            clients.add(clientSocket);


            /*Aqui estamos obtendo o endereço remoto do cliente conectado
             * A mensagem irá aparecer a porta que o cliente está utilizando para receber o retorno do server
             * O cliente conecta na porta 4000 
             * O servidor irá  utiizar a porta passada pelo getRemoteSocketAdress, essa porta é definida pelo Sistema Operacional
             */

            // o método na função lambda () nao precisa de definição de acesso e nem mesmo o tipo de retorno.
            // o Java possui inferencia de tipo ele identifica por conta propria o retorno
            // a -> é uma arrow function. O método na função lambda não pode receber funções que retorno Throws Exception
            new Thread(() -> clientMessageLoop(clientSocket)).start(); 

        }
    }

    public void clientMessageLoop(clientSocket socket){
        String msg;
        try{
            while((msg = socket.getMessage())!=null){
                if("sair".equalsIgnoreCase(msg))
                    return;

                System.out.printf("Msg recebida do cliente %s: %s\n", socket.getRemoteSocketAddress(),msg);

                sendMessageToAll(socket, msg);
        }} finally{
            socket.close();
        }
    }

    private void sendMessageToAll(clientSocket sender,String msg){
        for(clientSocket clientSocket:clients){
            if(!sender.equals(clientSocket))
                clientSocket.sendMessage(msg);
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

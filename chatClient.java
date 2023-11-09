import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class chatClient implements Runnable {
    /*No caso do cliente, declaramos o socket */
    private clientSocket clientSocket;


    /*Devemos criar um endereço para conectar o socket do cliente ao servidor
     * O endereço 127.0.0.1 é um alias para o servido local ( mesmo computador)
     */
    private final String SERVER_ADRESS = "127.0.0.1";

    /*Aqui devemos ter um scanner declarado */
    private Scanner scanner;



    /*O construtor da classe irá sempre inicializar um scanner */
    public chatClient(){
        /*Captura a entrada do teclado */
        scanner = new Scanner(System.in);
    }

    public void start() throws  IOException{
        try{
        System.out.println("Cliente conectado ao servidor em: " + SERVER_ADRESS + ":" + ChatServer.PORT);
        /*No caso do cliente Socket pode ser que ocorra uma exceção ao tentar abrir a porta e ao tentar conectar ao endereço IP passado */
        clientSocket = new clientSocket(new Socket(SERVER_ADRESS, 4000));






        /*Na fonte o autor explica que não é necessário utilizar os 3 objetos criados OutPutStream(bytes), OutPutStreamWrites(caracteres) e BufferedWriter(linhas)
         * Podemos utilizar apenas o PrintWriter que ele encapsulas as funções dos outros 2 objetos OutPutStreamWrites(caracteres) e BufferedWriter(linhas)
         */

        new Thread(this).start();

        messageLoop();
        }finally{
            clientSocket.close();
        }
    }

    @Override
    public void run(){
        String msg;
        while((msg = clientSocket.getMessage())!=null){
        System.out.printf("Msg recebida do servidor: %s\n",msg);
    }
}

    /*No caso do cliente, também devemos possuir um loop infinito para que a mensagem será trocada 
     * O loop do cliente é um loop de espera de conexões
     * Quando precisar finalizar ele será encerrado.
     * Aqui precisamos de um scanner para que receba a entrada do teclado
    */
    private void messageLoop() throws IOException{
        String msg;
        do{
            System.out.println("Digite uma mensagem ou 'sair' para finalizar: ");
            msg = scanner.nextLine(); 


            /*o Out irá enviar a mensagem  
             * e também uma quebra de linha para determinar o fim da mensagem
            */


            /*Aqui modificamos o código para receber apenas o PrintWriter
             * O println realiza as duas operações que os objetos anteriores realizavam
             * Então capturamos a mensagem e definimos o seu final
             * Envia a mensagem com a quebra de linha no final
             */
            clientSocket.sendMessage(msg);
            /*como as mensagens são pequenas iremos envialas de uma vez.
             * Se fossem grandes deveriamos enviar por pacotes 
             */
            /*O flush garante que a mensagem após escrita seja enviada*/
            /*O PrintWrite não requer o flush, no seu construtor(método start) ja possui um parametro de autoflush setado para true */


        }while(!msg.equalsIgnoreCase("sair"));
        
    }

    

    public static void main(String[] args){

        try {
            chatClient client = new chatClient();
            client.start();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Erro ao iniciar o cliente: " + e.getMessage());
        }

        System.out.println("Cliente finalizado!");
    }
}

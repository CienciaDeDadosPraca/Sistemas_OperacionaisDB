import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class clientSocket{
    private final Socket socket;
    private final BufferedReader in; //objeto de entrada
    private final  PrintWriter out; //objeto de saida


    /*o construtor irá inicializar a classe passando o socket por parametro
     * este é usado para se comunicar com o servidor
    */

    public clientSocket(Socket socket) throws IOException{
        
        this.socket = socket;
        System.out.println("Cliente: "+socket.getRemoteSocketAddress()+" conectou");
        


        
            /* Na classe servidor, devemos ter um objeto "in" para receber a mensagem transmitida pelo cliene através do "out" 
              * Aqui possuimos o mesmo problema dos bytes
              * Temos que utilizar a mesma lógica que adotamos na classe cliente
              * Criaremos um bufferedRead e atribuiremos um novo atributo na classe Server  
              * Copiamos o código do cliente e onde temos Output substituimos por Input
              * onde está writer trocamos por reader
             */
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                /*O outputstream representa o fluxo de dados, corrente de dados
         * o método write da OutPutStream irá enviar mensagens para o servidor
         * Ele envia um vetor de bytes
         * Neste caso devemos realizar uma conversão de String para Bytes!!!
         * Então criamos um objeto OutPutStreamWriter, que possui como parametro no construtor um objeto OutPutStream 
         * que foi criado pelo clientSocket
         * Dessa maneira conseguimos enviar um vetor de caracteres automaticamente, não sendo necessário lidar com bytes
         */
        /*O BufferedWrite criado irá determinar quando é o fim da mensagem que normalmente é o \n 
         * aqui possuimos o método newLine 
         * Outro fator importante é que o Buffered cria um buffer (alocação de um espaço de memória para) que torna a operação mais rápida 
        */
        /*Algo intessante é a criação de um atributo BufferedWriter na class chatCliente que é o out
         * esse "out" é uma saida para enviar dados pela rede até o servidor
         */

        
        this.out = new PrintWriter(socket.getOutputStream(),true);
    }

    public String getMessage(){
        try{
            return in.readLine();
        }
        catch (IOException e){
            return null;
        }
    }

    /*o método abaixo nos informa se a mensagem foi enviada ou não */
    public boolean sendMessage(String msg){
        out.println(msg);
        /*o método checkError nos informa se a mensagem foi enviada */
        return !out.checkError();

    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close(){
       
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Erro ao fechar socket: " + e.getMessage());
        }
    }

}
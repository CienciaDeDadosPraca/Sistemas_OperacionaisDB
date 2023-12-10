import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Queue; 
  
  
class FIFO
{ 
    //Método para encontrar falhas de página usando FIFO
    static int pageFaults(int pages[], int n, int capacity) 
    { 
        // Para representar o conjunto de páginas atuais. É utilizado
        // um conjunto não ordenado para verificar rapidamente se a página está presente no conjunto ou não.
        HashSet<Integer> s = new HashSet<>(capacity); 
       
        // Guardar as páginas em FIFO
        Queue<Integer> indexes = new LinkedList<>() ; 
        
        // Início da página inicial 
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            // Checar se cabe mais páginas no conjunto 
            if (s.size() < capacity) 
            { 
                // Inserção de página no conjunto 
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
       
                    // Incremento na falha de página
                    page_faults++; 
       
                    // Empurrar a página corrente na fila
                    indexes.add(pages[i]); 
                } 
            }  

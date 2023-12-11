import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Queue; 
  
  
class FIFO
{
    // Método para encontrar falhas de página usando FIFO
    static int pageFaults(int pages[], int n, int capacity)
    {
        // Para representar o conjunto de páginas atuais. É utilizado um conjunto não ordenado para verificar rapidamente se a página está presente no conjunto ou não.
        HashSet<Integer> s = new HashSet<>(capacity);
       
        // Guarda as páginas em FIFO
        Queue<Integer> indexes = new LinkedList<>();
        
        // Início da página inicial 
        int page_faults = 0;
        for (int i=0; i<n; i++)
        {
            // Confere se cabe mais páginas no conjunto 
            if (s.size() < capacity)
            {
                // Insere página no conjunto 
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);
                    // Incrementa falha de página
                    page_faults++;
                    // Empurra a página corrente na fila
                    indexes.add(pages[i]);
                }
            }
            else
            {
                // Confere se a página atual não está já presente
                if(!s.contains(pages[i]))
                {
                    // Adiciona a primeira página da fila
                    in val = indexes.peek();
                    indexes.poll();
                    // Remove a página de indices
                    s.remove(val);
                    // Insere a página atual
                    s.add(pages[i]);
                    // Avança a página na fila
                    indexes.add(pages[i]);
                    // Incrementa falha de página
                    page_faults++;
                }
            }
        }

        return page_faults;
    }
}

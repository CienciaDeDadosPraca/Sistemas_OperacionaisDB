import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Iterator; 
  
class LRU
{ 
    // Método para encontrar falhas de página usando índices
    static int pageFaults(int pages[], int n, int capacity) 
    { 
        // Para representar o conjunto de páginas atuais. Usamos um unordered_set para que possamos verificar rapidamente se uma página está presente no conjunto ou não
        HashSet<Integer> s = new HashSet<>(capacity); 
       
        // Para armazenar índices usados ​​recentemente de páginas
        HashMap<Integer, Integer> indexes = new HashMap<>(); 
       
        // Começa da página inicial
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            // Verifica se o conjunto pode conter mais páginas
            if (s.size() < capacity) 
            { 
                // Insire no conjunto se não estiver presente já que representa falha de página
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
       
                    // Incrementa a falha de página
                    page_faults++; 
                } 
       
                // Armazena o índice usado recentemente de cada página
                indexes.put(pages[i], i); 
            } 
       
            // Se o conjunto estiver cheio, então é necessário realizar LRU ou seja, remover a página menos usada recentemente e insirir a página atual
            else
            { 
                
                if (!s.contains(pages[i])) 
                { 
                    // Encontra as páginas menos usadas recentemente que está presente no set
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
                      
                    Iterator<Integer> itr = s.iterator(); 
                      
                    while (itr.hasNext()) { 
                        int temp = itr.next(); 
                        if (indexes.get(temp) < lru) 
                        { 
                            lru = indexes.get(temp); 
                            val = temp; 
                        } 
                    } 
                  
                    // Remove a página de índices
                    s.remove(val);
                  
                    // Remove LRU do hashmap
                    indexes.remove(val);
                  
                    // Insire a página atual
                    s.add(pages[i]); 
       
                    // Incrementa falhas de página 
                    page_faults++; 
                } 
       
                // Atualiza o índice da página atual
                indexes.put(pages[i], i); 
            } 
        } 
       
        return page_faults; 
    }
}

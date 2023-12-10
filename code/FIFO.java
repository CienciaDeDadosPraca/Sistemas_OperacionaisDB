import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Queue; 
  
  
class FIFO
{ 
    // Method to find page faults using FIFO 
    static int pageFaults(int pages[], int n, int capacity) 
    { 
        // To represent set of current pages. We use 
        // an unordered_set so that we quickly check 
        // if a page is present in set or not 
        HashSet<Integer> s = new HashSet<>(capacity); 
       
        // To store the pages in FIFO manner 
        Queue<Integer> indexes = new LinkedList<>() ; 
       

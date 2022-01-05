/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequentialprimesieve;


import java.util.ArrayList;

/**
 *
 * @author up877962
 */
public class SequentialPrimeSieve {
    
    final static int S = 1; 
    final static int N = 20000000; 

    
    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> primes = new ArrayList<>(); 
        
        for(int i = S; i < N; i++){
            if(isPrime(i)){
                primes.add(i);
            }
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println(primes.size() + " Primes found" + " in " + (endTime - startTime) + " ms"   );
        //System.out.println(primes);
    }
    
    
    //Function to check if a number is a prime
    
    public static boolean isPrime(int number) {  
        if(number <= 1) return false;
        for(int i = 2; i <= Math.sqrt(number); ++i)
        {
            if(number % i == 0) return false;
        }
        
        return true;  
   }  
}
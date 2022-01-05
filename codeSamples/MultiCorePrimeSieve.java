/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicoreprimesieve;


/**
 *
 * @author up877962
 */
import java.util.ArrayList;


public class MultiCorePrimeSieve extends Thread{
    final static int S = 1; 
    final static int N = 200000000; 
    final static int P = 4; 
    
    final static ArrayList<ArrayList<Integer>> primes = new ArrayList<>();
    final static int total = 0;
    
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        
        MultiCorePrimeSieve[] threads = new MultiCorePrimeSieve[P];
        for (int me = 0; me < P; me++) {
            threads[me] = new MultiCorePrimeSieve(me);
            threads[me].start();
        }

        for (int me = 0; me < P; me++) {
            threads[me].join();
        }
        
        ArrayList<Integer> primeList = new ArrayList<>();
        primes.forEach((list) -> {
            primeList.addAll(list);
        });
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Found "+ primeList.size() +" primes across "+ P +" threads in " + (endTime - startTime) + "ms" );
        //System.out.println(primes);
    }
    
    int me;
    public MultiCorePrimeSieve(int me) {
        this.me = me;
    }

//blockwise decomposition, works best here
    
public void run(){
    
    int begin = me * (N / P);
    int end = begin + (N / P);

    ArrayList<Integer> prime = new ArrayList<>();

    for(int i = begin; i < end; i++){
        if(isPrime(i)){
            prime.add(i);
        }
    }

    primes.add(prime);
}

    
//cyclic decomposition
    
//public void run(){
//    
//
//    ArrayList<Integer> prime = new ArrayList<>();
////blockwise decomposition works best here, do coMparison
//    for(int i = me; i < N; i+=P){
//        if(isPrime(i)){
//            prime.add(i);
//        }
//    }
//
//    primes.add(prime);
//}

    public static boolean isPrime(int number) {  
        if(number <= 1) return false;
        for(int i = 2; i <= Math.sqrt(number); ++i)
        {
            if(number % i == 0) return false;
        }
        
        return true;  
   }  
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpi_primesieve;

/**
 *
 * @author up877962
 */

import mpi.*;
import java.util.ArrayList;

public class MPI_PrimeSieve extends Thread{
    final static int N = 200000000; 
    static int P, me; 
    final static ArrayList<Integer> primes = new ArrayList<>();;    

    public static void main(String[] args) throws Exception {
        MPI.Init(args);

        long startTime = System.currentTimeMillis();
        
        me = MPI.COMM_WORLD.Rank(); 
        P = MPI.COMM_WORLD.Size(); 
        
        int begin = me * (N / P);
        int end = begin + (N / P);
        
        ArrayList<Integer> temporaryStorage = new ArrayList<>();
        for(int i = begin; i < end; i++){
            if(isPrime(i)){
                temporaryStorage.add(i);
            }
        }
       
       
        if(me > 0){
            int[] sendBuf = new int[]{temporaryStorage.size()};
            MPI.COMM_WORLD.Send(sendBuf, 0, 1, MPI.INT, 0, 1);
            
            for(int i = 0; i < temporaryStorage.size(); i++){
                int[] send = new int[]{temporaryStorage.get(i)};
                MPI.COMM_WORLD.Send(send, 0, 1, MPI.INT, 0, 0);
            }
        } else {
            for(int i = 0; i < temporaryStorage.size(); i++){
                primes.add(temporaryStorage.get(i));
            }
            
            int[] recvBuf = new int[1];
            for (int src = 1; src < P; src++) {
                MPI.COMM_WORLD.Recv(recvBuf, 0, 1, MPI.INT, src, 1);
                
                for (int ind = 1; ind <= recvBuf[0]; ind++) {
                    int[] recv = new int[1];
                    MPI.COMM_WORLD.Recv(recv, 0, 1, MPI.INT, src, 0);
                    primes.add(recv[0]);
                }
            }
        }
               
        long endTime = System.currentTimeMillis();   
         
        if(me == 0){
            System.out.println("Found "+ primes.size() +" primes across "+ P +" threads in " + (endTime - startTime) + "ms" );
            //System.out.println(primes);
        }
        
        MPI.Finalize();
    }
    
    public static boolean isPrime(int number) {  
        if(number <= 1) return false;
        for(int i = 2; i <= Math.sqrt(number); ++i)
        {
            if(number % i == 0) return false;
        }
        
        return true;  
   }
}

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool;
import  java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.Arrays;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nknpet001
 */
 public class Sorting {
     static long startTime = 0;
     static final ForkJoinPool fjPool = new ForkJoinPool();
     static float time;
      

     public static float sort(int[] arr, int CO){
           tick();
 	         fjPool.invoke(new ParallelMergeSort(arr,0,arr.length,CO));
           time = toc();
           return time;

 	  }
   
   public static float Qsort(int[] arr, int CO){
           tick();
 	         fjPool.invoke(new ParallelQuickSort(arr,0,arr.length,CO));
           time = toc();
           return time;

 	  }
   
     private static void tick(){
 	     startTime = System.currentTimeMillis();
     }
     private static float toc(){
 	     return (System.currentTimeMillis() - startTime) / 1000.0f;
     }
     public static void main (String args[]) throws FileNotFoundException  {
        String sort = args[0];
        int arrmin = Integer.parseInt(args[1]);
        int arrmax = Integer.parseInt(args[2]);
        int inc = Integer.parseInt(args[3]);
        String filename = args[4];
        int[] foo;
        float check;
       
        //System.out.println("sort : "+sort+"\narrmin : "+arrmin+"\narrmax : "+arrmax+"\ninc : "+inc+"\nfilename : "+filename);  
       
        //File file = new File ("C:/Users/Me/Desktop/directory/file.txt");
        PrintWriter writer = new PrintWriter(filename);
        //writer.println("sort : "+sort+"\narrmin : "+arrmin+"\narrmax : "+arrmax+"\ninc : "+inc+"\nfilename : "+filename);
        writer.println("<arraySize> <optimalNumThreads> <bestTime> <bestSpeedup>");
        //writer.close();
       
        Random randomGen = new Random();       
        int bcut = 0;
        float btime;
        float temp = 0;
        for(int x = arrmin; x <= arrmax; x+=inc ){// third loop
          btime = 10000;
          for(int y = 2; y<= 32; y=y*2){//second loop
            for(int z = 0; z<5; z++){ //third loop
              //creating & filling array
              
              foo = new int[x];
              for(int num = 1; num < x; ++num){
                foo[num] = randomGen.nextInt(100000);}
              if(sort.equals("ParallelMergeSort")){
              temp = sort(foo, y);}
              else if(sort.equals("ParallelQuickSort")){temp = Qsort(foo, y);}
              if(temp < btime){
                btime = temp;
                bcut = y;
                
              }
            }
          }
          foo = new int[x];
          for(int num = 1; num < x; ++num){
            foo[num] = randomGen.nextInt(100000);}
          tick();
          Arrays.sort(foo);
          check = toc();
          writer.println("arraySize : "+x+" optimalNumThreads : "+x/bcut+" bestTime : "+btime +" speedup : "+check/btime);
          
        }
       writer.close();
        
         
         
     }
         /*
         System.out.println("Before : "+Arrays.toString(foo));
         sort(foo);
         System.out.println("After : "+Arrays.toString(foo));*/


   }

 

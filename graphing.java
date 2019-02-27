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
 public class graphing {
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
        int arrsize = Integer.parseInt(args[1]);
        
        String filename = args[2];
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
          btime = 10000;
          for(int y = 2; y<= 131072; y=y*2){//second loop
            for(int z = 0; z<5; z++){ //third loop
              //creating & filling array
              
              foo = new int[arrsize];
              for(int num = 1; num < arrsize; ++num){
                foo[num] = randomGen.nextInt(100000000);}
              if(sort.equals("ParallelMergeSort")){
              temp = sort(foo, y);}
              else if(sort.equals("ParallelQuickSort")){temp = Qsort(foo, y);}
              if(temp < btime){
                btime = temp;
                bcut = y;
                
              }
            }
          
          foo = new int[arrsize];
          for(int num = 1; num < arrsize; ++num){
            foo[num] = randomGen.nextInt(100000);}
          tick();
          Arrays.sort(foo);
          check = toc();
          writer.println("arrsize : " +arrsize+" NumThreads : "+arrsize/y+" bestTime : "+btime +" speedup : "+check/btime);
          
        }
       writer.close();
        
         
         
     }
         /*
         System.out.println("Before : "+Arrays.toString(foo));
         sort(foo);
         System.out.println("After : "+Arrays.toString(foo));*/


   }
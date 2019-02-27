import java.util.Arrays;
import java.util.concurrent.*;

public class ParallelQuickSort extends RecursiveAction{
    
    private int arr[];
    private int length, start, end, threshold;
    static long startTime = 0;

    public ParallelQuickSort(int[] arr,int start, int end, int threshold) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
        
    }
    
    
    @Override
    protected void compute(){
        
        if (end - start <= threshold) {
                    // sequential sort
                    Arrays.sort(arr, start, end);
                    return;
            }
            
            // Sort halves in parallel
            int mid = start + (end-start) / 2;
            int temp = QuickSort(mid);
            invokeAll(
                    new ParallelQuickSort(arr, start, temp, threshold),
                    new ParallelQuickSort(arr, temp, end, threshold) );
            
    }
    
    
    private int QuickSort(int mid){
        int IL = start;
        int IR = end-1;
        int pivot = arr[mid];
        while (IL <= IR) {  
            while (arr[IL] < pivot) {
                IL++;}
            while (arr[IR] > pivot) {
                IR--;}
            if (IL <= IR) {
                int var = arr[IL];
                arr[IL] = arr[IR];
                arr[IR] = var; 
                IL++;
                IR--;}
        }
        return IL;  
    }

}
  
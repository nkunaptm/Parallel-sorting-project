import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import  java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class ParallelMergeSort extends RecursiveAction {
	private int[] arr;
    private int start, end;
    private int threshold;



    public ParallelMergeSort(int[] arr, int start, int end, int threshold) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
    }

    @Override
    protected void compute() {
            if (end - start <= threshold) {
                    // sequential sort
                    Arrays.sort(arr, start, end);
                    return;
            }

            // Sort halves in parallel
            int mid = start + (end-start) / 2;
            invokeAll(
                    new ParallelMergeSort(arr, start, mid, threshold),
                    new ParallelMergeSort(arr, mid, end, threshold) );

            // sequential merge
            sequentialMerge(mid);
    }

    private void sequentialMerge(int mid) {
         //implement the merge
				 int [] Templeft = Arrays.copyOfRange(arr, start, mid);
			         int [] Tempright = Arrays.copyOfRange(arr, mid, end);

			         int LI = 0;
			         int HI = 0;
			         int check = start;

			         while( LI<Templeft.length && HI<Tempright.length){
			             if(Templeft[LI]< Tempright[HI]){
			                 arr[check] = Templeft[LI];
			                 LI++;
			             }
			             else{
			                 arr[check] = Tempright[HI];
			                 HI++;
			             }
			             check++;
			         }
			         while (LI < Templeft.length){
			             arr[check] = Templeft[LI];
			             LI++;
			             check++;
			         }
			         while (HI < Tempright.length){
			             arr[check] = Tempright[HI];
			             HI++;
			             check++;
			         }
    }

}

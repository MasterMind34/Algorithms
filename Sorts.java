import java.util.Arrays;
import java.io.PrintWriter;
import java.io.File;

public class Sorts
{
   static int[] sizes = {8, 32, 64, 128, 256, 512, 1024, 2*1024, 4*1024, 8*1024,
         16*1024, 32*1024, 64*1024, 128*1024, 256*1024, 512*1024/*, 1024*1024*/};  
   public static void main(String[] args) throws Exception
   {
      int sIdx = 0;
      long startTime;
      long endTime;
      double time;
      String lastName = "Liddell";
      PrintWriter pw = new PrintWriter(new File(lastName + "_sortTimes.csv"));
      pw.println( "Size, BubbleSort, MergeSort, QuickSort, JavaSort" );
      while( sIdx < sizes.length ) 
      {
         int[] A = new int[sizes[sIdx]];
         int[] B = new int[sizes[sIdx]];  
         int[] C = new int[sizes[sIdx]]; 
         int[] D = new int[sizes[sIdx]]; 
         int[] E = new int[sizes[sIdx]]; 
         for( int i=0; i<A.length; i++ ) 
         {
            A[i] = (int)(Math.random()*sizes[sIdx]*2);
            B[i] = A[i];
            C[i] = A[i];
            D[i] = A[i];
            E[i] = A[i];
         }
         System.out.println();
         System.out.println("--------------------------------------------------");
         System.out.println("Initial Values: ");
         printArray(A);
         pw.print(A.length + ", ");

         System.out.println( "Starting bubble sort ---------------- Size = " + B.length);
         startTime = System.nanoTime();
         bubbleSort(B);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Bubble sort finished - time = " + time + " seconds" );
         pw.print(time + ", ");
         printArray(B);
         System.out.println();

         System.out.println( "Starting merge sort ---------------- Size = " + C.length);
         startTime = System.nanoTime();         
         C = mergeSort(C);         
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Merge sort finished - time = " + time + " seconds.");
         pw.print(time + ", ");
         printArray(C);
         System.out.println();

         System.out.println( "Starting quick sort ---------------- Size = " + D.length);
         startTime = System.nanoTime();
         D = quickSort(D);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "quickSort finished - time = " + time  + " seconds." );
         pw.print(time + ", ");
         printArray(D);
         System.out.println();
         
         System.out.println( "Starting java's Array sort ---------------- Size = " + E.length);
         startTime = System.nanoTime();
         E = javaSort(E);
         endTime = System.nanoTime();
         time = (endTime - startTime) / 1000000000.;
         System.out.println( "Java's Array sort finished - time = " + time  + " seconds." );
         pw.println(time);
         printArray(E);
         System.out.println();
         sIdx = sIdx + 1;   
      }
      pw.close();
   }  
   public static void printArray(int[] X)
   {
      System.out.print("[ ");
      int i=0;
      for( ; i<15 && i<X.length; i++ )  
      {
         System.out.print( X[i] + " " );
      }
      if( i < X.length ) System.out.print("... ");
      System.out.println("]");
   }
   
   public static void bubbleSort(int[] A)
   {
      for(int i =0; i<A.length-1; i++)
         for(int j =0; j<A.length-i-1; j++)
            if(A[j] > A[j+1])
               {
                  int temp = A[j];
                  A[j] = A[j+1];
                  A[j+1] = temp;
               }
               
   }
   public static int[] mergeSort(int[] X)
   {
   if(X.length<=1)return X;
   {
      int[]A = new int[X.length/2];
      int[]B = new int[X.length-A.length];
      
      for(int i = 0; i<X.length;i++)
      {
         if(i<A.length)
         {
            A[i] = X[i];
         }
         else
         {
            B[i-A.length] = X[i];
         }
         A =mergeSort(A);
         B = mergeSort(B);
         X = merge(A,B);
         return X;
      
      
      }
      
   }
      return X;
   }
   public static int[] merge(int[] A, int[] B)
   {
      int []array = new int[A.length + B.length];
      int aI = 0;
      int bI = 0;
      int cI = 0;
      int i = 0;
      
      while(i<array.length)
      {
         if(aI>=A.length)
         {
            array[i] =B[bI];
            bI++;
         }
         else if(bI>=B.length)
         {
            array[i] =A[aI];
            aI++;
         }
         else if(A[aI]<B[bI])
         {
            array[i] = A[aI];
            aI++;
         }
         else
         {
            array[i] = B[bI];
            bI++;
         }
         i++;
      }
      return array;
   }
   public static int[] javaSort(int[] X)
   {
      Arrays.sort(X);
      return X;
   }
   public static int[] quickSort(int[] X)
   {
      //quicksort doesn't create a new array
      //  so we need additional params for our 
      //  recursive method - this method is what the user sees
      return quickSort(X, 0, X.length-1);   
   }
   public static int[] quickSort(int[] X, int s, int e)
   {
      if(e<=s)
      return X;
      int p = partition(X,s,e);
      quickSort(X, s, p-1);
      quickSort(X, p+1 ,e);
      return X;
   }
   public static int partition( int[]X, int s, int e )
   {
      int pivot = s;
      for(int i = s+1; i<e; i++)
      {
         if(X[i] < X[s])
         {
            pivot++;
         int t = X[i];
         X[i] = X[pivot];
         X[pivot] = t;
      
      }
      }
            
            int t = X[pivot];
         X[pivot] = X[s];
         X[s] = t;
         return pivot;
      

            
}
}
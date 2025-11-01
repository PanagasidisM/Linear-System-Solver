
import java.util.Scanner;

public class Gauss {
	// Method that makes all elements below the main diagonal zero
	public static void zeros(double arr[][]) {
		 // Small value to avoid division by zero 
		double e = 0.00001;
		 // Loop over each dividor row to check if an element is close to 0
		for (int p = 0; p < arr.length- 1; p++) {
			if (Math.abs(arr[p][p]) < e) {
				System.out.print("The system is unsolvable");
				return;
			}
			// Loop over rows below the dividor row
			for (int i = p + 1; i < arr.length; i++) {
				double reseter = arr[i][p];
				for (int j = 0; j < arr[0].length; j++) {
					arr[i][j] = arr[i][j] - (reseter / arr[p][p]) * arr[p][j];
				}
				/**
				 * every time p increases by one, the whole row below the diagonal is equal to zero
				 * 
				 */
			}
		}
	}
	// Method that converts the diagonal elements into 1
	public static void ones(double arr[][]) {
		double e = 0.00001;
		for (int i = 0; i < arr.length; i++) {
			if (Math.abs(arr[i][i]) < e) {
				System.out.print("The system is unsolvable");
				return;
			}
			// Divide the whole row by the diagonal element to make it 1
			double dieretis = arr[i][i];
			for (int j = 0; j <arr[0].length; j++) {
				arr[i][j] = arr[i][j] / dieretis;
			}
		}
	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		//the array A is the original matrix and the array temp is a copy where all the editing will be done
		double A[][] = new double[n][n + 1];
		double temp[][] = new double[n][n + 1];
		double e = 0.00001;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n + 1; j++) {
				A[i][j] = scan.nextDouble();
				temp[i][j] = A[i][j];
			}
		}
		 System.out.println("\nGAUSSIAN ELIMINATION\n");
	     System.out.println("The Problem\n");
	     for(int i=0; i<n; i++) {
	    	 for(int j=0; j<n; j++) {
	    		 System.out.printf("%10.2f", A[i][j]);
	    	 }
	    	 System.out.print("     x " + (i+1) + "       ");
	    	 System.out.printf("%.2f",A[i][n]);
	    	 System.out.println();
	     }
	    zeros(temp);
	    ones(temp);
	 // Array to store the solution values
		double solutions[] = new double[n];
		// Back-substitution to calculate variable values
		for (int i = n - 1; i >= 0; i--) {
			solutions[i] = solutions[i] + temp[i][n];
			for (int j = 0; j < n; j++) {
				if (i != j)
					solutions[i] = solutions[i] - (solutions[j] * temp[i][j]);
			}
		}
		  // Array to store recalculated results(plugging in the solutions to the original matrix) for accuracy checking
		double B[]=new double[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				B[i] = B[i] + solutions[j] * A[i][j];
			}
		}
		  // Array to store percentage errors
		double percentage[]=new double[n];
		for (int i = 0; i < n; i++) {
			double s = A[i][n];
			if (Math.abs(s - B[i]) < e)
				percentage[i] = 0;
			else if (s == 0)
				percentage[i] = 100 * B[i] / e;
			else
				percentage[i] = 100 * (1 - (B[i] / s));
		}
		
		 System.out.println("\nThe Solution\n");
		 for(int i=0; i<n; i++) {
	    	 for(int j=0; j<n; j++) {
	    		 System.out.printf("%8.2f",A[i][j]);
	    	 }
	    	 System.out.printf("%15.5f",solutions[i]);
	    	 System.out.printf("%12.5f",A[i][n]);
	    	 System.out.println();
	     }
		System.out.println("\nAccuracy of Solution\n");
		for(int i=0; i<n; i++) {
				System.out.print("for equation  " + (i+1) + " the error is      ");
				System.out.printf("%.5f",percentage[i]);
				System.out.print("%");
				System.out.println();
		}
		scan.close();
	}
}


package roadRater;

/**
 * java.util.Random is used for the Random type and the nextInt() method. 
 */
import java.util.Random;

/**
 * The SortName class utilizes the quicksort algorithm to sort an array of type Road by road name.
 * It references Algorithms 4th Edition by Sedgewick and Wayne for the implementation of quicksort.
 * 
 * SortName can take an array of type Road and return an array sorted alphabetically by the name of the Roads.
 */
public class SortName {

	/**
	 * Sorts an array of type Road by road name.
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 * @param a - An array of type Road to be sorted.
	 * @return Returns a sorted array of type Road.
	 */	
	public static Road[] sortName(Road[] a) {
		// Shuffle array to avoid worst-case time. 
		Random rand = new Random();
		for (int i = a.length-1; i > 0; i--) {
			int j = rand.nextInt(i+1);
			swap(a, i, j);
		}
		sortName(a, 0, a.length-1);
		
		return a;
	}
	
	/**
	 * Sorts an array of type Road. 
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 *  
	 * @param a - An array of type Road to be sorted.
	 * @param low - Int representing the first index in array a.
	 * @param high - Int representing the last index in array a. 
	 */
	private static void sortName(Road[] a, int low, int high) {
		if (high <= low) {
			return;			// Ends recursive call when indices meet. 
		}
		int j = partN(a, low, high); 	// Finds partitioning element.
		sortName(a, low, j-1);				// Sort left half of array.
		sortName(a, j+1, high);				// Sort right half of array.
	}
	
	/**
	 * Finds partitioning element in an array.
	 * 
	 *  Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 *  @param a - An array of type Road.
	 *  @param low - An int representing the first index in the array a.
	 *  @param high - An int representing the last index in the array a.
	 *  @return j - Returns an int, the index of the partitioning element for the sort. 
	 */
	private static int partN(Road[] a, int low, int high) {
		int i = low, j = high+1;
		Road v = a[low];
		while (true) {
			while (lessN(a[++i], v)) {
				if (i == high) {
					break;
				}
			}
			while (lessN(v, a[--j])) {
				if (j == low) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, low, j);
		return j;
	}
	
	/**
	 * Compares names of two Roads.
	 * 
	 * @param x - First Road to be compared.
	 * @param y - Second Road to be compared.
	 * @return Returns a boolean; returns true if the name of Road x comes before the name of Road y alphabetically, 
	 * 			else returns false.
	 */
	private static boolean lessN(Road x, Road y) {
		if (x.compareName(y) == -1) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	/**
	 * Swaps 2 array entries. 
	 * 
	 *  Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 *  @param a - An array of type Road.
	 *  @param x - An int representing an index of a Road in array a. 
	 *  @param y - An int representing an index of a Road in array a. 
	 */
	private static void swap(Road[] a, int x, int y) {
		Comparable temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}

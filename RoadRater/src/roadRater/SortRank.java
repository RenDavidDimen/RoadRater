package roadRater;

/**
 * java.util.Random is used for the Random type and the nextInt() method. 
 */
import java.util.Random;

/**
 * The SortRank class utilizes the quicksort algorithm to sort an array. It references Algorithms 4th Edition by
 * Sedgewick and Wayne for the implementation of quicksort.
 * 
 * SortRank can take an array of type Road and return an array sorted by the rank of the Roads.
 */
public class SortRank {

	/**
	 * Sorts an array of type Road by rank.
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 * 
	 * @param a - An array of type Road to be sorted.
	 */
	public static Road[] sortRank(Road[] a) {
		// Shuffle array to avoid worst-case time. 
		Random rand = new Random();
		for (int i = a.length-1; i > 0; i--) {
			int j = rand.nextInt(i+1);
			swap(a, i, j);
		}
		sort(a, 0, a.length-1);
		
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
	private static void sort(Road[] a, int low, int high) {
		if (high <= low) {
			return;			// Ends recursive call when indices meet. 
		}
		int j = partition(a, low, high); 	// Finds partitioning element.
		sort(a, low, j-1);				// Sorts the left half of the array.
		sort(a, j+1, high);				// Sorts the right half of the array.
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
	private static int partition(Road[] a, int low, int high) {
		int i = low, j = high+1;
		Road v = a[low];
		while (true) {
			while (less(a[++i], v)) {
				if (i == high) {
					break;
				}
			}
			while (less(v, a[--j])) {
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
	 * Compares ranks.
	 * 
	 * Compares the ranks of two Roads and returns an int value based on the comparison.
	 * @param x - First Road.
	 * @param y - Second Road; to be compared to the first.
	 * @return Returns a boolean; true if Road x has a smaller rank than Road y, false if not.
	 */
	private static boolean less(Road x, Road y) {
		if (x.compareTo(y) == -1) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	/**
	 * Swaps 2 array entries. 
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 * @param a - An array of type Road.
	 * @param x - An int representing an index in the array a.
	 * @param y - An int representing an index in the array a.
	 */
	private static void swap(Road[] a, int x, int y) {
		Road temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}

package roadRater;

import java.util.Random;

public class SortRank {

	/*
	 * Sorts an array a.
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 */
	public static void sortRank(Comparable[] a) {
		// Shuffle array to avoid worst-case time. 
		Random rand = new Random();
		for (int i = a.length-1; i > 0; i--) {
			int j = rand.nextInt(i+1);
			swap(a, i, j);
		}
		sort(a, 0, a.length-1);
	}
	
	/*
	 * Sorts an array of RoadADT objects by rank. 
	 * 
	 *  Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 */
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;			// Ends recursive call when indices meet. 
		}
		int j = partition(a, lo, hi); 	// Finds partitioning element.
		sort(a, lo, j-1);				// Sort left half of array.
		sort(a, j+1, hi);				// Sort right half of array.
	}

	/*
	 * Finds partitioning element in an array.
	 * 
	 *  Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while (true) {
			while (less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			while (less(v, a[--j])) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}
	
	/* 
	 * Compares ranks.
	 * 
	 */
	private static boolean less(Comparable u, Comparable v) {
		if (u.compareTo(v) == -1) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	/*
	 * Swaps 2 array entries. 
	 * 
	 *  Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 */
	private static void swap(Comparable[] a, int x, int y) {
		Comparable temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}

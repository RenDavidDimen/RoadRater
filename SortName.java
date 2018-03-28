package roadRater;

import java.util.Random;



public class SortName {

	/*
	 * Sorts an array a.
	 * 
	 * Algorithms 4th Edition by Sedgewick and Wayne was referenced for this code.
	 */
	
	public static void sortName(Road[] a) {
		// Shuffle array to avoid worst-case time. 
		Random rand = new Random();
		for (int i = a.length-1; i > 0; i--) {
			int j = rand.nextInt(i+1);
			swap(a, i, j);
		}
		sortName(a, 0, a.length-1);
	}
	
	private static void sortName(Road[] a, int lo, int hi) {
		if (hi <= lo) {
			return;			// Ends recursive call when indices meet. 
		}
		int j = partN(a, lo, hi); 	// Finds partitioning element.
		sortName(a, lo, j-1);				// Sort left half of array.
		sortName(a, j+1, hi);				// Sort right half of array.
	}
	
	private static int partN(Road[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		Road v = a[lo];
		while (true) {
			while (lessN(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			while (lessN(v, a[--j])) {
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
	
	private static boolean lessN(Road a, Road b) {
		if (a.compareName(b) == -1) {
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
	
	public static void main(String[] args) {
		Road a = new Road("a", "b", "c", "s");
		Road b = new Road("a", "b", "a", "s");
		Road c = new Road("a", "b", "d", "s");
		Road d = new Road("a", "b", "b", "s");
		Road e = new Road("a", "b", "a", "s");
		
		Random x = new Random();
		
		Road[] z = new Road[] {a, b, c, d, e};
		for (int i = 0; i < z.length; i++) {
			z[i].setRank(x.nextInt(5));
			System.out.println(z[i].getRank());
		}
		
		
		System.out.println("\n");
		for (int j = 0; j < z.length; j++) {
			System.out.println(z[j].getRank());
		}
		
		sortName(z);
		for (int k = 0; k < z.length; k++) {
			System.out.println(z[k].getRdName());
		}
	}
	
}

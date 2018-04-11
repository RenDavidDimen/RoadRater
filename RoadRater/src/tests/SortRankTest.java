package tests;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import roadRater.DataParser;
import roadRater.Road;
import roadRater.SortRank;

public class SortRankTest {

	/**
	 * TestArray that holds the data used in the dataset.
	 */
	private Road[] testArray = new Road[3820];

	private Road a = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road b = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road c = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road d = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road e = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road f = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road g = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road h = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road i = new Road("Hamilton", "Ontario", "Main", "Street");
	private Road j = new Road("Hamilton", "Ontario", "Main", "Street");
	
	/**
	 * Test arrays that hold arbitrary data, used for testing a wider range of cases.
	 */
	private Road[] testArray1 = new Road[] {a, b, c, d, e, f, g, h, i, j};
	private Road[] testArray2 = testArray1;
	
	/**
	 * Initializes the ranks of the testArrays. 
	 * 
	 * @param r - the array to be initialized.
	 * @param x - an int that specifies how to initialize the array.
	 */
	private void initialize(Road[] r, int x) {
		Random rand = new Random();
		
		if (x == 0) {
			int j = 0;
			while(j < r.length) {
				r[j].setRank(1 + rand.nextInt(5));
				j++;
			}
		} else if (x == 1) {
			for (int k = 0; k < r.length; k++) {
				r[k].setRank(rand.nextInt());
				//System.out.println(testArray1[k].getRank());
			}
		} else if (x == 2) {
			for (int l = 0; l < r.length; l++) {
				r[l].setRank(l);
			}
		} else if (x == 3) {
			for (int n = r.length - 1; n >= 0; n--) {
				r[n].setRank(n);
			}
		}
		
	}

	/**
	 * This function is used to ensure an array is sorted. 
	 * 
	 * @param r - the array.
	 * @return Returns true if sorted, false if not.
	 */
	private boolean isSorted(Road[] r) {
		
		int l = 0;
		while(l < r.length -1) {
			int i = r[l].compareTo(r[++l]);
			if (i == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * This tests shows that the basic case that sortRank() will normally be used for works
	 * 	as intended. It first ensures that the array, testArray, is unsorted, then calls 
	 * 	sortRank(), and finally confirms that the array is now sorted.
	 */
	@Test
	public void testSortRank() {
		try {
			testArray = DataParser.parseDataSet();
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		initialize(testArray, 0);
		
		assertFalse(isSorted(testArray));
		
		SortRank.sortRank(testArray);
		
		assert(isSorted(testArray));
	}
	
	/**
	 * This test is used to test ranks that are outside of the 1 to 5 range set by our specification.
	 * 	It shows that sortRank() works for large positive and negative numbers. 
	 */
	@Test
	public void test2SortRank() {
		initialize(testArray1, 1);
		
		assertFalse(isSorted(testArray1));
		
		SortRank.sortRank(testArray1);
		
		assert(isSorted(testArray1));
		
	}
	
	/**
	 * Rank is initialized to zero, so this test shows that nothing happens when all of the ranks
	 * 	are the same number.
	 */
	@Test
	public void test3SortRank() {
		assert(isSorted(testArray2));
		
		SortRank.sortRank(testArray2);
		
		assert(isSorted(testArray2));
	}
	
	/**
	 * Tests sortRank() functionality when the array is already sorted, and when it is sorted, 
	 * 	but in reverse order.
	 */
	@Test
	public void test4SortRank() {
		initialize(testArray1, 2);
		assert(isSorted(testArray1));
		
		SortRank.sortRank(testArray1);
		
		assert(isSorted(testArray1));
		
		initialize(testArray1, 3);
		assert(isSorted(testArray1));
		
		SortRank.sortRank(testArray1);
		assert(isSorted(testArray1));
	}
}

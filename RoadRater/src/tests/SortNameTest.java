package tests;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import roadRater.DataParser;
import roadRater.Road;
import roadRater.SortName;

public class SortNameTest {

	/**
	 * This array holds the data from our dataset.
	 */
	private Road[] testArray = new Road[3820];
	
	/**
	 * This function is used to ensure an array is sorted.
	 * 
	 * @param r - the array to check.
	 * @return Returns true if sorted, or false if not.
	 */
	private boolean isSorted(Road[] r) {
		
		int l = 0;
		while(l < r.length -1) {
			int i = r[l].compareName(r[++l]);
			if (i == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests that the basic case of sorting unique names comprised only of alphanumeric characters
	 * 	succeeds. 
	 */
	@Test
	public void testSortName() {
		try {
			testArray = DataParser.parseDataSet();
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		assertFalse(isSorted(testArray));
		
		SortName.sortName(testArray);
		
		assert(isSorted(testArray));
		
	}
	
	/**
	 * Tests how sortName() reacts when all names are the same.
	 */
	@Test
	public void test1SortName() {
		Road a = new Road("Hamilton", "Ontario", "Main", "Street");
		Road b = new Road("Hamilton", "Ontario", "Main", "Street");
		Road c = new Road("Hamilton", "Ontario", "Main", "Street");
		Road d = new Road("Hamilton", "Ontario", "Main", "Street");
		Road e = new Road("Hamilton", "Ontario", "Main", "Street");
		Road[] test = new Road[] {a, b, c, d, e};
		
		assert(isSorted(test));
		
		SortName.sortName(test);
		assert(isSorted(test));
	}

	/**
	 * Tests how sortName() reacts when all names are already sorted, and when they're sorted, 
	 * but in descending (reverse) order.
	 */
	@Test
	public void test2SortName() {
		Road a = new Road("Hamilton", "Ontario", "Cline", "Street");
		Road b = new Road("Hamilton", "Ontario", "Dalewood", "Street");
		Road c = new Road("Hamilton", "Ontario", "East", "Street");
		Road d = new Road("Hamilton", "Ontario", "Elmwood", "Street");
		Road e = new Road("Hamilton", "Ontario", "Main", "Street");
		Road[] test = new Road[] {a, b, c, d, e};
		Road[] test1 = new Road[] {e, d, c, b, a};
		
		assert(isSorted(test));
		SortName.sortName(test);
		assert(isSorted(test));
		
		assertFalse(isSorted(test1));
		SortName.sortName(test1);
		assert(isSorted(test1));
	}
	
	/**
	 * Tests how sortName() reacts to special characters (outside of letters and numbers).
	 */
	@Test
	public void test3SortName() {
		Road a = new Road("Hamilton", "Ontario", "**Main**", "Street");
		Road b = new Road("Hamilton", "Ontario", "!!Dalewood.", "Street");
		Road c = new Road("Hamilton", "Ontario", ")Ea$t", "Street");
		Road d = new Road("Hamilton", "Ontario", "`~~@", "Street");
		Road e = new Road("Hamilton", "Ontario", ")(*&^%$#@!@#$%^&*()", "Street");
		Road[] test = new Road[] {a, b, c, d, e};
		
		assertFalse(isSorted(test));
		SortName.sortName(test);
		assert(isSorted(test));
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i].getRdName());
		}
	}
}

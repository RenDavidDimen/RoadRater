package tests;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import roadRater.BinarySearchST;
import roadRater.Road;

/**
 * BinarySearchSTTest
 * Test Class for BinarySearchST
 * Will look into special edge cases to ensure the binary search works as intended
 * @author Ren-David
 */
public class BinarySearchSTTest {

	private static Road[] rdData = new Road[10];
	
	@BeforeClass
	public static void setUp() {
		System.out.println("Prepping Binary Search Test Cases");
		rdData[0] = new Road("City", "Province", "*", "Type");
		rdData[1] = new Road("City", "Province", "1", "Type");
		rdData[2] = new Road("City", "Province", "20", "Type");
		rdData[3] = new Road("City", "Province", "A", "Type");
		rdData[4] = new Road("City", "Province", "AA", "Type");
		rdData[5] = new Road("City", "Province", "B", "Type");
		rdData[6] = new Road("City", "Province", "Cold Brew", "Type");
		rdData[7] = new Road("City", "Province", "D", "Type");
		rdData[8] = new Road("City", "Province", "Ren-David", "Type");
		rdData[9] = new Road("City", "Province", "e", "Type");
	}
	
	@AfterClass
    public static void tearDown()
    {
		rdData = null;
        System.out.println("Binary Search Tests Complete");
    }
	
	@Test
	public void testValidRoad() {
		// Test if search works for an existing road in a sorted array
		int result = BinarySearchST.findRoad(rdData, "B");
		assertEquals(result,5);
	}
	
	@Test
	public void testSpecialCharRoad() {
		// Test if search works for special characters in a sorted array
		int result = BinarySearchST.findRoad(rdData, "*");
		assertEquals(result,0);
	}
	
	@Test
	public void testNumber() {
		// Test if search works for numbers in a sorted array
		int result = BinarySearchST.findRoad(rdData, "1");
		assertEquals(result,1);
	}

	@Test
	public void testSimilarName1() {
		// Test if search differentiates between similar names in a sorted array
		int result = BinarySearchST.findRoad(rdData, "A");
		assertEquals(result,3);
	}
	
	@Test
	public void testSimilarName2() {
		// Test if search differentiates between similar names in a sorted array
		int result = BinarySearchST.findRoad(rdData, "AA");
		assertEquals(result,4);
	}
	
	@Test
	public void testCaseSensitive() {
		// Test if search differentiates case sensitive names in a sorted array
		int result = BinarySearchST.findRoad(rdData, "a");
		assertEquals(result,-1);
	}
	
	@Test
	public void testSpace() {
		// Test if search works names with a space in a sorted array
		int result = BinarySearchST.findRoad(rdData, "Cold Brew");
		assertEquals(result,6);
	}
	
	@Test
	public void testHyphen() {
		// Test if search works names with a hyphen/special character in a sorted array
		int result = BinarySearchST.findRoad(rdData, "Ren-David");
		assertEquals(result,8);
	}
	
	@Test
	public void testInvalidRoad() {
		// Test if search works for an non existent road in a sorted array
		int result = BinarySearchST.findRoad(rdData, "fake");
		assertEquals(result,-1);
	}
}

package tests;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import roadRater.Road;

public class RoadTest {

	private Road tr = new Road("Hamilton", "Ontario", "Haddon Avenue South", "Avenue");
	private Road tr1 = new Road("Hamilton", "Ontario", "Main Street", "Street");
	
	/**
	 * Tests the setRank() function. Attempts to set the rank to a float, an int, and a char;
	 * 	ends up casting all but the float into floats. Won't accept a String input by nature.
	 */
	@Test
	public void testSetRank() {
		float f = 5;
		tr.setRank(f);
		assert(f == tr.getRank());
		
		int i = 10;
		tr.setRank(i);
		assert(i == tr.getRank());
		
		char c = 't';
		tr.setRank(c);
		assert(c == tr.getRank());
	}

	/**
	 * Tests updateRank(). Sets a rank and updates that rank, and tests that the new rank is the 
	 * average of the previous two.
	 */
	@Test
	public void testUpdateRank() {
		tr.setRank(5);
		tr.updateRank(3);
		assert(4.0 == tr.getRank());
		
		// Test if updateRank() works for negative integers.
		tr1.setRank(4);
		tr1.updateRank(-4);
		assert(0.0 == tr1.getRank());
	}

	/**
	 * Tests getRank() by seeing if it can get the same rank more than once, and if it can get 
	 * 	the updated rank as well.
	 */
	@Test
	public void testGetRank() {
		assert(0 == tr.getRank());
		
		float a = 5;
		tr.setRank(5);
		float x = tr.getRank();
		assert(a == x);
		x = tr.getRank();
		assert(a == x);
		
		a = 7;
		tr.updateRank(a);
		x = tr.getRank();
		assert(6.0 == x);
	}

	/**
	 * Tests getRdName(). Checks that the road name is the correct one, and also checks that
	 * 	it doens't for some reason return true no matter what.
	 */
	@Test
	public void testGetRdName() {
		String name = tr.getRdName();
		
		assertSame("Haddon Avenue South", name);
		
		assertNotSame("Main", name);
	}

	/**
	 * Tests getType(). Checks that the correct type is returned, and that it doesn't always
	 * 	return true.
	 */
	@Test
	public void testGetType() {
		String type = tr.getType();
		
		assertSame("Avenue", type);
		
		assertNotSame("Street", type);
	}

	/**
	 * Tests getCity(). Checks that the correct city name is returned, and that it doesn't always
	 * 	return true.
	 */
	@Test
	public void testGetCity() {
		String city = tr.getCity();
		
		assertSame("Hamilton", city);
		assertNotSame("Apex", city);
	}

	/**
	 * Tests getProv(). Checks that the correct province name is returned, and that it doesn't always
	 * 	return true.
	 */
	@Test
	public void testGetProv() {
		String province = tr.getProv();
		
		assertSame("Ontario", province);
		assertNotSame("Alberta", province);
	}

	/**
	 * Checks that compareTo() behaves appropriately when the Road's ranks are different and the same.
	 */
	@Test
	public void testCompareTo() {
		tr.setRank(5);
		tr1.setRank(4);
		
		int x = tr1.compareTo(tr);
		assert(x == -1);
		
		x = tr.compareTo(tr1);
		assert(x == 1);
		
		tr.updateRank(3);
		x = tr.compareTo(tr1);
		assert(x == 0);
	}

	/**
	 * Tests compareName() and ensures that it acts appropriately when names are different 
	 * 	and when they're the same. 
	 */
	@Test
	public void testCompareName() {
		int x = tr.compareName(tr1);
		assert(x == -1);
		
		int y = tr1.compareName(tr);
		assert(y == 1);
		
		Road tr2 = new Road("Hamilton", "Ontario", "Haddon Avenue South", "Avenue");
		int z = tr.compareName(tr2);
		assert(z == 0);
		
	}

	/**
	 *  Tests the toString() function. Specifically ensures that the output of toString() is a string.
	 */
	@Test
	public void testToString() {
		String a = tr.toString();
		assert(a.getClass().equals(String.class));
	}

}

package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import roadRater.DataParser;
import roadRater.Road;

public class DataParserTest {
	DataParser dp = new DataParser();
	private static Road[] baseData = new Road[2];
	private static Road[] parsedData = new Road[2];
	private static String dir = "";
	
	@BeforeClass
	public static void setUp() {
		System.out.println("Prepping Binary Search Test Cases");
		baseData[0] = new Road("Hamilton", "Ontario", "Telephone", "RD");
		baseData[1] = new Road("Hamilton", "Ontario", "Ditton", "DR");
	}
	
	@Test
	public void testParseDataSet() throws IOException {
		parsedData = dp.parseDataSet("TestStreetData.txt");

	    for (int i = 0; i < 2; i++) {
	    		assertEquals("Array Object Name Comparison",parsedData[i].getRdName(), baseData[i].getRdName());
	    		assertEquals("Array Object City Comparison",parsedData[i].getCity(), baseData[i].getCity());
	    		assertEquals("Array Object Province Comparison",parsedData[i].getProv(), baseData[i].getProv());
	    		assertEquals("Array Object Type Comparison",parsedData[i].getType(), baseData[i].getType());
	    		assertEquals("Array Object Rank Comparison",parsedData[i].getRank(), baseData[i].getRank(), 0.0001);
	    }
	}
	
}

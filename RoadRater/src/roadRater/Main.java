package roadRater;


import java.io.IOException;
import java.util.Scanner;

/**
 * Application Controller
 * 
 * @author Ren-David
 */
public class Main {
	
	static Road[] rdData = new Road[3820];
	public static String feature = "";
	public static int roadIndex1 = 0;
	public static String roadName1 = "";
	public static int roadIndex2 = 0;
	public static String roadName2 = "";
	
	/**
	 * Main method to call other methods
	 * @param args
	 */
	public static void main(String args[]) {
		// Instantiates new object Main
		Main RoadRater = new Main();
		
		System.out.println("Loading Data...");
		rdData = RoadRater.getData();
		rdData = SortName.sortName(rdData);
		
		getUserInput();
		
	}
	
	/**
	 * Gets user input from console
	 */
	public static void getUserInput() {
		// Read user input
		Scanner sc = new Scanner(System.in);
		System.out.println("Commands:\n\tUpdate Rank\n\tSearch\n\tRoute\n\tExit");
		System.out.println("Please enter a command");
		
		feature = sc.nextLine();
		feature = feature.toLowerCase();
		
		System.out.println(feature);
		
		switch (feature) {
			case "update rank":
				update(sc);
			case "search":
				search(sc);
			case "route":
				// point to graphing algorithm
			case "exit":
				System.out.println("Exiting Road Rater");
				System.exit(1);
			default:	
				System.out.println("Invalid Input: Please try again");
				getUserInput();
		}
		
		sc.close();
	}
	
	/**
	 * Gets an array of roads
	 * @return rdData
	 */
	private Road[] getData() {
		try {
			rdData = DataParser.parseDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rdData;
	}
	
	//	***********************************
	// 				Update Rank
	//	***********************************
	private static void update(Scanner in) {
		String specs = "";
		// Read user input
		System.out.println("Commands:");
		System.out.println("Please enter a Street Name of who's Rank you would like to Update or enter 'Exit' to return to the main menu");
		
		String input = in.next();
		
		switch (input) {
			case "exit":
				System.out.println("Exiting Update Rank");
				getUserInput();
			default:
				updateRoadRank(rdData, input, in);
				System.out.println("Rank Updated Successfully");
				specs = getRoadSpecs(rdData, input, in);
				System.out.println("******** TEST ********");
				System.out.println(specs);
				input = "";
				update(in);
		}
	}
	
	private static void updateRoadRank(Road[] rdData, String rdName, Scanner in) {
		int index = BinarySearchST.findRoad(rdData, rdName);
		
		if(index == -1) {
			System.out.println("Street not found, please try again");
			update(in);
		}else {
			
			System.out.println("Please enter a rank from 1 to 5");
			
			// Ensures input is an integer
			while(!in.hasNextInt() ) {
				System.out.println("Invalid Input");
			    in.next();
			}
			int rank = in.nextInt();
			
			//Ensure the input is between 1 and 5
			if (rank < 1 || rank > 5) {
				System.out.println("Invalid Input");
				updateRoadRank(rdData, rdName, in);
			} else {
				rdData[index].updateRank(rank);
			}
		}
	}
	
	//	******************************
	// 				Search
	//	******************************
	
	/**
	 * Search
	 * this method is used when as the main searching feature of the application
	 * @param in:	Scanner - Used to get user input
	 */
	private static void search(Scanner in) {
		String specs = "";
		// Read user input
		System.out.println("Commands:");
		System.out.println("Please enter a Street Name to Search or enter 'Exit' to return to the main menu");
		
		String input = in.nextLine();
		
		switch (input) {
			case "exit":
				System.out.println("Exiting Search");
				getUserInput();
			default:
				specs = getRoadSpecs(rdData, input, in);
				System.out.println(specs);
				search(in);
		}
	}
	
	/**
	 * getRoadSpecs
	 * This method searches the data set for a specific street and returns it's data as a string 
	 * @param rdData:	Array of type Road
	 * @param rdName:	String - Street name being searched
	 * @param in	:		Scanner - Used to get user input
	 * @return String of data for the searched road
	 */
	private static String getRoadSpecs(Road[] rdData, String rdName, Scanner in) {
		int index = BinarySearchST.findRoad(rdData, rdName);
		
		if(index == -1) {
			System.out.println("Street not found, please try again");
			search(in);
		}
		
		return rdData[index].toString();
	}
}

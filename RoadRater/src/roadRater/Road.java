package roadRater;

public class Road implements Comparable<Road> {

	private float rank;
	private String city;
	private String type;
	private String prov;
	private String name;
	private int count;
	
	/**
	 * Constructor for RoadADT.
	 * 
	 * @param city - The name of the city the road is found in.
	 * @param prov - The name of the province the road is found in. 
	 * @param name - The name of the road. 
	 * @param type - The type of the road (street, avenue, court, etc). 
	 */
	public Road(String city, String prov, String name, String type) {
		this.city = city;
		this.prov = prov;
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Assigns an initial rank to the road.  
	 *  
	 * This is the inital method for setting the numerical ranking of a road. 
	 * It begins the count for keeping track of how many times the rank has been updated by users.
	 * This is so that an average can be taken for the rank of the road, instead of simply overwriting
	 * the rank every time a user inputs a new rank.
	 */
	public void setRank(float r) {
		this.rank = r;
		this.count = 1;
	}
	
	/**
	 * Updates the rank of the road. 
	 * 
	 * Takes into account previous rank to create an average instead of erasing past rank and replacing.
	 * @param newRank
	 */
	public void updateRank(float newRank) {
		this.rank = this.rank * this.count;
		this.count++;
		this.rank = (this.rank + newRank)/this.count;
	}
	
	/**
	 * Returns the rank of the road.
	 */
	public float getRank() {
		return this.rank;
	}
	
	/**
	 * Returns the name of the road.
	 */
	public String getRdName() {
		return this.name;
	}
	
	/**
	 * Returns type of street.
	 * @return this.type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Returns the name of the road's city.
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * Returns the name of the road's province.
	 */
	public String getProv() {
		return this.prov;
	}
	
	/**
	 * CompareTo method compares ranks of two RoadADTs and returns value based on which rank is lower/higher.
	 * 
	 * @param that - The road being compared to. 
	 * @return Returns an int; -1 if this has a lower rank than that, 1 if this has a higher rank than that, 
	 * 			or 0 if the ranks are equivalent. 
	 */
	public int compareTo(Road that) {
		if (this.getRank() < that.getRank()) {
			return -1;
		}
		else if (this.getRank() > that.getRank()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Compare method for 'name'. Returns value based on which street name comes first alphabetically.
	 * 
	 * @param that - The road being compared to.
	 * @return Returns an int value; -1 if the name of 'this' is first alphabetically, 1 if the name of 'that' 
	 * 			is first alphabetically, or 0 if the names are equal. 
	 */
	public int compareName(Road that) {
		char[] a = this.getRdName().toCharArray();
		char[] b = that.getRdName().toCharArray();
		int i = a.length, j = b.length;
		for (int k = 0; k < i && k < j; k++) {
			if (a[k] < b[k]) {
				return -1;
			}
			else if (a[k] > b[k]) {
				return 1;
			}
		}
		if (i < j) {
			return -1;
		}
		else if (j < i) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * String representation of the road.
	 * @return rdData - String holding the name, city name, province name, road type, and road rank. 
	 */
	public String toString() {
		String rdData = "";
		rdData += "Name:\t\t" + this.getRdName();
		rdData += "\nType:\t\t " + this.getType();
		rdData += "\nCity:\t\t" + this.getCity();
		rdData += "\nProvince:\t" + this.getProv();
		rdData += "\nRank:\t\t" + this.getRank() + "\n";
		return rdData;
	}

	
}

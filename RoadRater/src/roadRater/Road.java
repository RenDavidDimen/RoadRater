package roadRater;

public class Road implements Comparable<Road> {

	private float rank;
	private String city;
	private String type;
	private String prov;
	private String name;
	private int count;
	
	/*
	 * Constructor for RoadADT.
	 */
	public Road(String city, String prov, String name, String type) {
		this.city = city;
		this.prov = prov;
		this.name = name;
		this.type = type;
	}
	
	/*
	 * Allows a rank to be assigned to a street.  
	 * 
	 * To consider: When rank is updated, needs to take into account previous rank, 
	 * and calculate average. 
	 */
	public void setRank(float r) {
		this.rank = r;
		this.count = 1;
	}
	
	/*
	 * Updates the rank. 
	 * 
	 * Takes into account previous rank to create an average instead of erasing past rank and replacing.
	 */
	public void updateRank(int newRank) {
		this.rank = this.rank * this.count;
		this.count++;
		this.rank = (this.rank + newRank)/this.count;
	}
	
	/*
	 * Returns the rank of a RoadADT.
	 */
	public float getRank() {
		return this.rank;
	}
	
	/*
	 * Returns the name of the RoadADT.
	 */
	public String getRdName() {
		return this.name;
	}
	
	/*
	 * Returns type of street.
	 */
	public String getType() {
		return this.type;
	}
	
	/*
	 * Returns a RoadADT's city.
	 */
	public String getCity() {
		return this.city;
	}
	
	/*
	 * Returns a RoadADT's province.
	 */
	public String getProv() {
		return this.prov;
	}
	
	/*
	 * CompareTo method compares ranks of two RoadADTs and returns value based on which rank is lower/higher.
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
	
	/*
	 * Compare method for 'name'. Returns value based on which street name comes first alphabetically.
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
	 * Returns string containing the road's data
	 * @return rdData:	String
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

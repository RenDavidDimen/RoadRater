package roadRater;

/**
 * This code is based off the BinarySearch class in Algorithms 4th Edition by Sedgewick and Wayne
 * @author Ren-David
 *
 */
public class BinarySearchST{
	
	/**
	 * findRoad:
	 * Searches through array of type Road for a specific road
	 * @param array:	Road[]
	 * @param key:	String
	 * @return int:	Index of found string
	 */
	public static int findRoad(Road[] array, String key) {
		int lo = 0;
        int hi = array.length - 1;
        int compare = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            compare = key.compareTo(array[mid].getRdName());
            
            if (compare < 0) {
            		hi = mid - 1;
            } else if (compare > 0) {
            		lo = mid + 1;
            } else {
            		return mid;
            }
        }
        return -1;
    }
	
}
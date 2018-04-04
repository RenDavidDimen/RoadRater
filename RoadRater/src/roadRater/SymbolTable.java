package roadRater;

import java.util.TreeMap;

/**
 * ADT for SymbolTable
 * Creates a symbol table with a <Key, Value> tuple
 * This class is based off the API described in Algorithms 4th Edition by Sedgewick and Wayne.
 * @author Ren-David
 *
 */
public class SymbolTable<Key extends Comparable<Key>, Value> {

	private TreeMap<Key, Value> st;
	
	/**
	 * SymbolTable
	 * Create new SymboleTable
	 */
	public SymbolTable() {
		st = new TreeMap<Key, Value>();
	}
	
	/**
	 * put
	 * Adds tuple to symbol table
	 * @param street: Key - Street name
	 * @param id: Value - Index value
	 */
	public void put(Key street, Value id) {
		st.put(street, id);
	}
	
	/**
	 * get
	 * Returns ID of street
	 * @param street: String - Street Name
	 * @return
	 */
	public Value get(Key street) {
		return st.get(street);
	}
}

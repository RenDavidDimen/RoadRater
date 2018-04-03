package roadRater;

import java.util.HashMap;
import java.util.LinkedList;
public class Graph {

	public static HashMap<String,node> nodes = new HashMap<String,node>();
	public static LinkedList<String> nodeIDs = new LinkedList<String>();
	
	public static class node{
		private String id;
		LinkedList<String> connected = new LinkedList<String>();
		HashMap<String,Integer> distance = new HashMap<String,Integer>();
		
		node(String name){
			this.id=name;
		}
	}
	
	public static void addedge(String node1,String node2, int dist){
		getnode(node1).connected.add(node2);
		getnode(node2).connected.add(node1);
		getnode(node1).distance.put(node2, dist);
		getnode(node2).distance.put(node1, dist);
	}
	
	public static node getnode(String node1){
		return nodes.get(node1);
	}
	
	public static void Dijkstra(String strt,String end){
		//the original list contains all id of nodes,this one will be empty as soon as all nodes has been checked
		LinkedList<String> all = new LinkedList<String>();
		//the HashMap records<ID,distance>for searched nodes
		HashMap<String,Integer>  done = new HashMap<String,Integer>();
		//the list which contains all searched id in HashMap done, in order to avoid search duplicate nodes.
		LinkedList<String> visited = new LinkedList<String>();
		//the HashMap which contains nodes which are ready to be compared
		HashMap<String,Integer> temp = new HashMap<String,Integer>();
		//the List which contains all children of nodes in visited but need to not same as visited
		LinkedList<String>  tempid = new LinkedList<String>();
		all=nodeIDs;
		done.put(strt,0);
		visited.add(strt);
		all.remove(strt);
		//Proceed as the "all" list is not empty.
		while(!all.isEmpty()){
			temp.clear();
			tempid.clear();
			for(String i: visited){
				for(String j: getnode(i).connected){
					if(!visited.contains(j)){
						temp.put(getnode(j).id,done.get(i)+getnode(j).distance.get(i));
						tempid.add(getnode(j).id);
					}
				}
			}
			int min=temp.get(tempid.get(0));
			String res=tempid.get(0);
			for(String i : tempid){
				if(temp.get(i)<min){
					res=i;
				}
			}
			done.put(res,temp.get(res));
			visited.add(res);
			all.remove(res);
		}
		System.out.println(done.get(end));
	}
	
	/**
	 * Prep graph by clearing any remaining hashmaps and linked lists
	 * 
	 */
	public static void prepGraph(String roadName) {
		nodeIDs.clear();
		nodes.clear();
		
	}
	
	public static void populateGraph() {
		
		nodeIDs.clear();
		nodes.clear();
		nodeIDs.add("1");
		nodeIDs.add("2");
		nodeIDs.add("3");
		node a = new node("1");
		node b = new node("2");
		node c = new node("3");
		nodes.put("1",a);
		nodes.put("2",b);
		nodes.put("3",c);
		addedge("1","2",3);
		addedge("1","3",1);
		addedge("2","3",1);
		Dijkstra("1","2");
	}
}

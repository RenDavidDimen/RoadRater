package roadRater;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

	public static HashMap<String,node> nodes = new HashMap<String,node>();
	public static LinkedList<String> nodeIDs = new LinkedList<String>();
	public static LinkedList<String> secnodeIDs = new LinkedList<String>();
	
	public Graph(){
	}
	
	public static void addnode(String id){
		nodes.put(id,new node(id));
		if(!nodeIDs.contains(id)){
			nodeIDs.add(id);
		}
		if(!secnodeIDs.contains(id)){
			secnodeIDs.add(id);			
		}
	}
	
	public static class node{
		private String id;
		LinkedList<String> connected = new LinkedList<String>();
		HashMap<String,Integer> distance = new HashMap<String,Integer>();
		HashMap<String,Double> rank = new HashMap<String,Double>();
		node(String name){
			this.id=name;
		}
	}
	
	public static void addedge(String node1,String node2, int dist, double rank){
		getnode(node1).connected.add(node2);
		getnode(node1).distance.put(node2, dist);
		getnode(node2).distance.put(node1, dist);
		getnode(node1).rank.put(node2, rank);
		getnode(node2).rank.put(node1, rank);
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
		//the HashMap which will store the result of the route
		HashMap<String,String> doneroute = new HashMap<String,String>();
		//the HashMap will store the route for every procedure
		HashMap<String,String> temproute = new HashMap<String,String>();
		
		all=nodeIDs;
		done.put(strt,0);
		doneroute.put(strt, strt);
		visited.add(strt);
		all.remove(strt);
		//Proceed as the "all" list is not empty this one will find the shortest path.
		while(!all.isEmpty()){
			temp.clear();
			tempid.clear();
			for(String i: visited){
				for(String j: getnode(i).connected){
					if(!visited.contains(j)){
						temp.put(getnode(j).id,done.get(i)+getnode(j).distance.get(i));
						temproute.put(getnode(j).id,doneroute.get(i)+","+getnode(j).id);
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
			doneroute.put(res,temproute.get(res));
			visited.add(res);
			all.remove(res);
		}
		System.out.println("The shortest path have a length of "+done.get(end)+" and the procedure is "+doneroute.get(end));
	}
	
	public static void Dijkstra2(String strt,String end){
		//the original list contains all id of nodes,this one will be empty as soon as all nodes has been checked
		LinkedList<String> all = new LinkedList<String>();
		//the HashMap records<ID,rank>for searched nodes
		HashMap<String,Double>  done = new HashMap<String,Double>();
		//the list which contains all searched id in HashMap done, in order to avoid search duplicate nodes.
		LinkedList<String> visited = new LinkedList<String>();
		//the HashMap which contains nodes which are ready to be compared
		HashMap<String,Double> temp = new HashMap<String,Double>();
		//the List which contains all children of nodes in visited but need to not same as visited
		LinkedList<String>  tempid = new LinkedList<String>();
		//the HashMap which will store the result of the route
		HashMap<String,String> droute = new HashMap<String,String>();
		HashMap<String,String> doneroute = new HashMap<String,String>();
		//the HashMap will store the route for every procedure
		HashMap<String,String> temproute = new HashMap<String,String>();
		
		all=secnodeIDs;
		done.put(strt,0.);
		doneroute.put(strt, strt);
		visited.add(strt);
		all.remove(strt);
		//Proceed as the "all" list is not empty this one will find the shortest path.
		while(!all.isEmpty()){
			temp.clear();
			tempid.clear();
			for(String i: visited){
				for(String j: getnode(i).connected){
					if(!visited.contains(j)){
						temp.put(getnode(j).id,done.get(i)+getnode(j).rank.get(i));
						temproute.put(getnode(j).id,doneroute.get(i)+","+getnode(j).id);
						tempid.add(getnode(j).id);
					}
				}
			}
			Double max=temp.get(tempid.get(0));
			String res=tempid.get(0);
			for(String i : tempid){
				if(temp.get(i)>max){
					res=i;
				}
			}
			done.put(res,temp.get(res));
			doneroute.put(res,temproute.get(res));
			visited.add(res);
			all.remove(res);
		}
		System.out.println("The best path have a rank of "+done.get(end)+" and the route will be " + doneroute.get(end)+" and the average rank will be "+done.get(end)/(doneroute.get(end).split(",").length-1.0));
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
		// TODO Auto-generated method stub
		nodeIDs.clear();
		nodes.clear();
		addnode("1");
		addnode("2");
		addnode("3");
		addedge("1","2",3,1);
		addedge("1","3",1,2);
		addedge("2","3",1,3);
		Dijkstra("1","2");
		Dijkstra2("1","2");
	}
}

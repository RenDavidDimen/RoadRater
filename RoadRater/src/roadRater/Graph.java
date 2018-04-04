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
		if(!nodeIDs.contains(id)){
			nodes.put(id,new node(id));
		}
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
	
	public static String Dijkstra(String strt,String end){
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
		String output = "";
		
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
						//System.out.println(getnode(j).id);
						//System.out.println(done.get(i));
						//System.out.println(getnode(j).distance.get(i));
						temp.put(getnode(j).id,done.get(i)+getnode(j).distance.get(i));
						temproute.put(getnode(j).id,doneroute.get(i)+","+getnode(j).id);
						tempid.add(getnode(j).id);
					}
				}
			}
			if(tempid.isEmpty()){
				break;
			}
			int min=temp.get(tempid.get(0));
			String res=tempid.get(0);
			for(String i : tempid){
				if(temp.get(i)<min){
					res=i;
				}
			}
			//System.out.println(res);
			done.put(res,temp.get(res));
			doneroute.put(res,temproute.get(res));
			visited.add(res);
			all.remove(res);
		}
		output = "Shortest Path\nDistance:\t "+done.get(end)+"\nPath:\t"+doneroute.get(end);
		System.out.println(output);
		return output;
	}
	
	public static String Dijkstra2(String strt,String end){
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
		
		String output = "";
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
			if(tempid.isEmpty()){
				break;
			}
			String res=tempid.get(0);
			Double max=temp.get(res)/(temproute.get(res).split(",").length-1.0);
			
			for(String i : tempid){
				if(temp.get(i)/(temproute.get(i).split(",").length-1.0)>max){
					res=i;
				}
			}
			done.put(res,temp.get(res));
			doneroute.put(res,temproute.get(res));
			visited.add(res);
			all.remove(res);
		}
		output = "Smoothest Path:\nTotal Rank:\t"+done.get(end)+"\nPath:\t" + doneroute.get(end)+"\nAverage Rank:\t"+done.get(end)/(doneroute.get(end).split(",").length-1.0);
		System.out.println(output);
		return output;
	}
	
	/**
	 * Prep graph by clearing any remaining hashmaps and linked lists
	 * 
	 */
	public static void prepGraph(String roadName) {
		nodeIDs.clear();
		nodes.clear();
		
	}
}

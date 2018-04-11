package tests;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import roadRater.Graph;

public class GraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGraph() {
//	}

	@Test
	public void testAddnode() {
		Graph p = new Graph();
		p.addnode("1");
		assertFalse(p.getnode("1") == null);
		p.addnode("2");
		assertFalse(p.getnode("2") == null);
		p.addnode("2");
		assertFalse(p.getnode("2") == null);
	}

	@Test
	public void testAddedge() {
		Graph p = new Graph();
		p.addnode("1");
		p.addnode("2");
		p.addnode("3");
		p.addedge("1","2",3,1);
		p.addedge("1","3",1,2);
		p.addedge("2","3",1,3);
		assertFalse(p.getnode("1").connected == null);
		assertFalse(p.getnode("2").connected == null);
		assertFalse(p.getnode("3").connected == null);
	}

	@Test
	public void testGetnode() {
		Graph p = new Graph();
		p.addnode("a");
		p.addnode("b");
		p.addnode("c");
		assertFalse(p.getnode("a") == null);
		assertFalse(p.getnode("b") == null);
		assertFalse(p.getnode("c") == null);
	}

	@Test
	public void testDijkstra() {
		Graph p = new Graph();
		p.addnode("1");
		p.addnode("2");
		p.addnode("3");
		p.addedge("1","2",3,1);
		p.addedge("1","3",1,2);
		p.addedge("2","3",1,3);
		p.Dijkstra("1","2");
	}

	@Test
	public void testDijkstra2() {
		Graph p = new Graph();
		p.addnode("1");
		p.addnode("2");
		p.addnode("3");
		p.addedge("1","2",3,1);
		p.addedge("1","3",1,2);
		p.addedge("2","3",1,3);
		p.Dijkstra2("1","2");
	}

	@Test
	public void testPrepGraph() {
		Graph p = new Graph();
		p.addnode("1");
		p.addnode("2");
		p.addnode("3");
		p.prepGraph(null);
		assertFalse(!p.nodeIDs.isEmpty());
	}

}

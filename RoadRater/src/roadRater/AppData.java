package roadRater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class AppData {

	private JFrame frame;
	private static Road[] r;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppData window = new AppData();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public AppData() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		//*********** Setting up frame. ***********
		frame = new JFrame("RoadRater");
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//*********** Creating Tab Bar. ***********
		JTabbedPane atabs = new JTabbedPane();
		
		//*********** Initializing data to be used for the application demo. ***********
		r = initData(r);
		
		//*********** Creating all of the panels in one place. ***********
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p23 = new JPanel();
		JPanel p32 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		
		//*********** Creating all of the labels. ***********
		JLabel logo = new JLabel("Welcome to RoadRater!");
		
		JLabel searchLabel = new JLabel("Street name:");
		
		JLabel searchOutput = new JLabel();
		
		JLabel inputRate = new JLabel("Input your rating: ");
		JTextField updateField = new JTextField(20);
		JLabel dispNewRank = new JLabel();
		
		JLabel inputStart = new JLabel("Input your start address: ");
		JLabel inputEnd = new JLabel("Input your end address: ");
		
		
		//*********** Creating all of the textboxes. *********** 
		JTextArea intro = new JTextArea(20, 30);
		intro.setEditable(false);
		intro.setText("To search the name of a road and update its rank, \nclick on the tab 'Search Streets,' \nand follow the prompts. \n\nTo view data on all of the streets in Hamilton, \nclick on the tab 'View Streets.'");
		JScrollPane scrollIntro = new JScrollPane(intro);
		scrollIntro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p1.add(scrollIntro);
		
		JTextArea displayStreets = new JTextArea(20, 30);
		displayStreets.setEditable(false);
		JScrollPane scroll = new JScrollPane (displayStreets);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p5.add(scroll);
		p5.setVisible(false);
		
		
		JTextField searchInputText = new JTextField(20);
		searchInputText.setBounds(100,20,165,25);
		
		JTextArea routeOut = new JTextArea(20, 30);
		routeOut.setEditable(false);
		JScrollPane ROscroll = new JScrollPane(routeOut);
		ROscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JTextField start = new JTextField(30);
		JTextField end = new JTextField(30);
		
		
		
		//*********** Creating all of the buttons. ***********
		JButton searchBtn = new JButton("Search");
		JButton updateBtn = new JButton("Update Rank");
		
		JButton sortBtn1 = new JButton("Sort Alphabetically");
		JButton sortBtn2 = new JButton("Sort by Rank");
		
		JButton resetBtn = new JButton("Reset");
		
		JButton calcRoute = new JButton("Calculate Route");
		
		//*********** Code for Home Tab. ***********
		p1.add(logo);
		p1.add(intro);
		
		
		//*********** Code for Search Streets Tab. ***********
		p2.add(searchLabel);
		p2.add(searchInputText);
		p2.add(searchBtn);
		p32.add(searchOutput);
		
		p3.add(inputRate);
		p3.add(updateField);
		p3.add(updateBtn);
		p3.add(dispNewRank);
		dispNewRank.setVisible(false);
		p3.setVisible(false);
		
		JPanel p232 = new JPanel();
		p232.add(resetBtn);
		p232.setVisible(false);
		
		p23.add(p2, BorderLayout.PAGE_START);
		p23.add(p32, BorderLayout.CENTER);
		p23.add(p3, BorderLayout.CENTER);
		p23.add(p232, BorderLayout.PAGE_END);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Sort r alphabetically for BinarySearch.
				sorted(r, 0);
				
				// Get user input
				String sr = "";
				sr = searchInputText.getText();
						
				// Get index from BinarySearch.
				int index = BinarySearchST.findRoad(r,sr);
				//System.out.println(sr + "\t" + index);
				
				// If sr isn't found and BinarySearch returns an index out of bounds, display "Cannot find road."
				if (index == -1 || index > r.length-1) {
					searchOutput.setText("Cannot find road.");
							
					searchOutput.setVisible(true);
				} else {
				
				// If searched Road (sr) is found, find it in the array r and display its information. 
					Road result = r[index];
						
					searchOutput.setText(result.toString());
					searchOutput.setVisible(true);
					
					p3.setVisible(true);
					
					updateBtn.setVisible(true);
					
					// Above line displays button for updating the rank of a Road; the following ActionListener allows users to do this.
					updateBtn.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
							// Get update rank.
							System.out.println(result.getRdName());
							String upr = updateField.getText();
									
							int rank = Integer.parseInt(upr);
							
							// Update the rank
							result.updateRank(rank);
							
							// Output the new, updated rank, and hide a whole bunch of components.
							String upRtext = "Updated rank: " + r[index].getRank();
							dispNewRank.setText(upRtext);
							dispNewRank.setVisible(true);
							searchOutput.setVisible(false);
							inputRate.setVisible(false);
							updateField.setVisible(false);
							updateBtn.setVisible(false);
							p2.setVisible(false);

							// Display reset button, to start the process over. 
							p232.setVisible(true);
									
						}
								
					});
				}
						
			}
		}); 
		
		// Reset button resets visibilities but currently doesn't reset the states for variables. 
		resetBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispNewRank.setVisible(false);
				
				p2.setVisible(true);
				p3.setVisible(false);
				inputRate.setVisible(true);
				updateField.setVisible(true);
				updateBtn.setVisible(true);
				
				p232.setVisible(false);
			}
			
		});

		
		
		//*********** Code for View Streets Tab. ***********
		
		ActionListener l1 = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				r = sorted(r, 0);
				String s = sOutput(r);
				
				displayStreets.setText(s);
				p5.setVisible(true);
			}
		};
		sortBtn1.addActionListener(l1);
		p4.add(sortBtn1);
		
		ActionListener l2 = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				r = sorted(r, 1);
				String s = sOutput(r);
				
				displayStreets.setText(s);
				p5.setVisible(true);
			}
		};
		sortBtn2.addActionListener(l2);
		p4.add(sortBtn2);
		
		p6.add(p4, BorderLayout.CENTER);
		p6.add(p5, BorderLayout.PAGE_END);
		
		//********** Code for Calculate Routes Tab. **********
		p7.add(inputStart);
		p7.add(start);
		p7.add(inputEnd);
		p7.add(end);
		p7.add(calcRoute);
		routeOut.setVisible(false);
		p7.add(routeOut);
		
		calcRoute.addActionListener(new ActionListener() {

			// Calculates the average rank of the route between the start and end address given by user.
			public void actionPerformed(ActionEvent arg0) {
				routeOut.setText("The route information will go here when a function is implemented!!!");
				
				String startp = start.getText();
				String endp = end.getText();
				String output = "";

// *****************************************************************************************************************				
// ************	Something with graphing here ***********************************************************************
// *****************************************************************************************************************	
				
				System.out.println(r.length);
				
				r = SortName.sortName(r);
				
				output = DataParser.getRoute(startp, endp, r);
				
				// example of how to set output in textbox:
				routeOut.setText(output);
				
				routeOut.setVisible(true);
				calcRoute.setText("Calculate New Route");
				
			}
			
		});
		
		
		// Add all of the Tabs to the Tab Bar. 
		atabs.add("Home", p1);
		atabs.add("Search Streets", p23);
		atabs.add("View Streets", p6);
		atabs.add("Calculate Route", p7);
		
		// Add Tab Bar to frame. 
		frame.add(atabs);
	}
	
	/**
	 * Initializes an array of type Road to hold the information on the roads in Hamilton. Also assigns a random int value
	 * 		to the rank of each Road to simulate a dataset that had collected this information from users. 
	 * 
	 * @param r: An empty array of type Road. 
	 * @return r: The full array of type Road.
	 * @throws IOException
	 */
	private Road[] initData(Road[] r) throws IOException {
		r = new Road[3820];
		r = DataParser.parseDataSet();
		
		Random rand = new Random();
		
		for (int i = 0; i < r.length; i++) {
			r[i].setRank(1 + rand.nextInt(5));
		}
		return r;
	}
	
	
	/** 
	 * Sorts an array of type Road, either alphabetically with respect to road name, or numerically by rank. 
	 * 
	 * @param r: An unsorted array of type Road. 
	 * @param b: An int (either 0 or 1) that's used to specify what parameter in the Road type to sort by (0 for name, 1 for rank).
	 * @return Returns a sorted array of type Road.
	 */
	private Road[] sorted(Road[] r, int b) {
		if (b == 0) {
			SortName.sortName(r);
		} else if (b == 1) {
			SortRank.sortRank(r);
		} 
		return r;
	}
	
	/**
	 * Creates the output to be displayed when a user asks to see the whole dataset on Hamilton streets.
	 * 
	 * @param r: An array of type Road.
	 * @return Returns a string representation of the information held in Road array, r. 
	 */
	private static String sOutput(Road[] r) {
		String s = "";
		
		for (int i = 0; i < r.length; i++) {
			s = s + r[i].getRdName() + "    " + r[i].getType() + ", " + r[i].getRank() + "\n";
		}
		
		return s;
	}
}

package roadRater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataParser:
 * Parses through a dataset and assigns corresponding values to the instance of an object.
 * 
 * @author Ren-David Dimen
 */
public class DataParser {
	
	static Road[] rd = new Road[3820];
		
	public static Road[] parseDataSet() throws IOException {
		
		BufferedReader br = null;
		String rdName = "";
		String rdType = "";
		String rdCity = "";
		String rdProvince = "";
		String currentString = "";
		int counter = 0;
		boolean uniqueName = true;

        try {
            br = new BufferedReader(new FileReader("HamiltonStreetData.txt"));
            
            while ((currentString = br.readLine()) != null) {
            		
            		if (currentString.equals("<gml:featureMember>") || !currentString.equals("</gml:featureMember>")) {
            			if (currentString.contains("<fme:NAME>")) {
            				rdName = currentString;
            				rdName = rdName.replace("<fme:NAME>", "");
            				rdName = rdName.replace("</fme:NAME>", "");
            			} else if (currentString.contains("<fme:TYPE>")) {
            				rdType = currentString;
            				rdType = rdType.replace("<fme:TYPE>", "");
            				rdType = rdType.replace("</fme:TYPE>", "");
            			} else if (currentString.contains("<fme:CSDNAME_L>")) {
            				rdCity = currentString;
            				rdCity = rdCity.replace("<fme:CSDNAME_L>", "");
            				rdCity = rdCity.replace("</fme:CSDNAME_L>", "");
            			} else if (currentString.contains("<fme:PRNAME_L>")) {
            				rdProvince = currentString;
            				rdProvince = rdProvince.replace("<fme:PRNAME_L>", "");
            				rdProvince = rdProvince.replace("</fme:PRNAME_L>", "");
            			}
            		} else if (currentString.equals("</gml:featureMember>")) {
            			if(!rdName.equals("") && !rdType.equals("")) {
            				
            				if(counter != 0) {
	            				for(int i = 0; i < counter; i++) {
		            				if(rd[i].getRdName().equals(rdName)) {
		            					uniqueName = false;
		            					break;
		            				} else {
		            					uniqueName = true;
		            				}
	            				}
            				}
            				
            				if (uniqueName == true) {
	            				// Instantiate road object every iteration with
	                    		Road roadObj = new Road(rdCity, rdProvince, rdName, rdType);
	                    		rd[counter]= roadObj;
	                			counter++;
            				}
            			}
            			
            			rdName = "";
            			rdType = "";
            			rdCity = "";
            			rdProvince = "";
            		}
            }
            System.out.println("Total Roads:\t" + counter + "\n");
            return rd;
            
        } finally {
            if (br != null) {
                br.close();
            }
        }
	}
}
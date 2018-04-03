package roadRater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DataParser:
 * Parses through a dataset and assigns corresponding values to the instance of an object.
 * 
 * @author Ren-David Dimen
 */
public class DataParser {
		
	public static Road[] parseDataSet() throws IOException {
		
		BufferedReader br = null;
		Road[] rd = new Road[3820];
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
	
	public static void getRoute(String start, String end) {
		// Call Google Maps API to get JSON Data
		URL url;
		JSONObject jsonObj;
		JSONArray jsonArr;
		String result = "";
		String roadNode = "";
		int distance = Integer.MIN_VALUE;
		start = start.replaceAll(" ", "+");
		end = end.replaceAll(" ", "+");
		
		// get URL content
		try {
			String a="https://maps.googleapis.com/maps/api/directions/json?origin="+start+"&destination="+end+"&alternatives=true&language=en&key=AIzaSyDPEMVJTi9s1fCKFOJDTL1suZBn39v1qXE";
			url = new URL(a);
			URLConnection conn = url.openConnection();
			
			// Open stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				inputLine.replace("\\s+", "");
				//System.out.println(inputLine);
				result=result+inputLine;
			}
		
			br.close();
			result.replace("\\s+", "");
			
			// Parse JSON data to get routes
			jsonObj = new JSONObject(result);
			jsonArr = jsonObj.getJSONArray("routes");
			
			JSONObject jsonDir[] = new JSONObject[jsonArr.length()];
			JSONArray jsonArrLegs[] = new JSONArray[jsonArr.length()];
			JSONObject jsonObjLegs[] = new JSONObject[jsonArr.length()];
			JSONArray jsonArrSteps[] = new JSONArray[jsonArr.length()];
			

			for (int i = 0; i < jsonArr.length(); i++) {
				jsonDir[i] = jsonArr.getJSONObject(i);
				jsonArrLegs[i] = jsonDir[i].getJSONArray("legs");
				jsonObjLegs[i] = jsonArrLegs[i].getJSONObject(0);
				
				jsonArrSteps[i] = jsonObjLegs[i].getJSONArray("steps");
				JSONObject jsonObjSteps[] = new JSONObject[jsonArrSteps[i].length()];
				String jsonInsSteps[] = new String[jsonArrSteps[i].length()];
				JSONObject jsonDist[] = new JSONObject[jsonArrSteps[i].length()];
				
				// Number of Nodes along path: jsonObjSteps.length
				System.out.println("************************************************************************");
				for (int j = 0; j < jsonObjSteps.length; j++) {
					jsonObjSteps[j] = jsonArrSteps[i].getJSONObject(j);
					jsonInsSteps[j] = jsonObjSteps[j].getString("html_instructions");
					jsonDist[j] = jsonObjSteps[j].getJSONObject("distance");
					distance = jsonDist[j].getInt("value");
					roadNode = getRoad(jsonInsSteps[j]);
					System.out.printf("%20s %20d\n", roadNode, distance);
				}
				System.out.println("************************************************************************");
			}
			
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// Populate Graph
	}
	
	public static String getRoad(String s) {
		String pattern1 = "<b>";
		String pattern2 = "</b>";
		String roadName = "";
		ArrayList<String> keyTerms = new ArrayList();
		
		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(s);
		
		while (m.find()) {
			keyTerms.add(m.group(1));
		}
		
		for (int i = 0; i < keyTerms.size(); i++) {
//			System.out.println(keyTerms.get(i));
			
			if(i == 0 && keyTerms.get(i).equals("U-turn")) {
				roadName = "last";
			} else if (i == 1 && !keyTerms.get(i).equals("left") && !keyTerms.get(i).equals("right")) {
				String[] road = keyTerms.get(i).split(" ");
				roadName = road[0];
			} else if (i == 2 && roadName.equals("")) {
				String[] road = keyTerms.get(i).split(" ");
				roadName = road[0];
			}
		}
		return roadName;
	}
}
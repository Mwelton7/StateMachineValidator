import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

//Matthew Welton
//ICSI333 Phipps
//AlbID: 000876982
//NetID: MW884997
//Assignment 1

public class validator2 {
	static boolean success;
	
	public static void main(String args[]) 
	 {
		Path pathSm = Paths.get(args[0]);  //get path for state machine file
		Path pathIp = Paths.get(args[1]);  //get path for input file
		List<String> strAllLinessm = null;                                                  //create a list with string data type called strAllLinessm  (for state machine file)
		List<String> strAllLinesip = null;                                                  //create a list with string data type called strAllLinesip  (for input file)
		HashMap<String, String> hm = new HashMap<String, String>();                         //Create a hashmap named "hm" that takes a string for its key and a string for its value.
		//Begin reading state machine file
		try
		{			
			strAllLinessm = Files.readAllLines(pathSm);               
		}
		catch (Exception e) 
		{
			System.out.print("File IO issue");
			System.exit(1);
		}
		for(String line : strAllLinessm)                                                   
			{
			String[] strLinePartssm = line.split(" ");                                      //split each line with at every space and separate into a string called strLineParts
			hm.put(strLinePartssm[0] + strLinePartssm[1], strLinePartssm[2]);		        //now put the concatenation of the first two parts of each line into the key for the hashmap (the current state and input) 
			}	                                                               //and the third part(the transition state) as the value associated with each key. Successfully populating a hash map to hold the State Machine in.
	
		//Begin reading input file
			try {		
				strAllLinesip = Files.readAllLines(pathIp);
			}
				catch (Exception e) {
					System.out.print("File IO issue");
					System.exit(1);
	}
	        
			String strCurrentState = "0";
			String strNewState = ""; 
			String strInputChar = "";
			int intLineCounter = 0;
			for(String lineip : strAllLinesip)
				{
					intLineCounter = intLineCounter + 1;  //increment counter to keep track of which line is currently being read
					String[] strLinePartsip = lineip.split("");   //split the lines into a string named strLinePartsip separating each character.
	
						for(int i=0; i <= (strLinePartsip.length-1); i++)
							{
		
							strInputChar = strLinePartsip[i];                                             //string variable to keep track of which input is being validated
							boolean boolKeyExists = hm.containsKey(strCurrentState + strInputChar);   //test to make sure the current state and input(Key) exists in the hashmap.
								if( (boolKeyExists))                                                     //if it does not exist throw an error message displaying what line/position and which input character it occurred at and exit.
								{
									strNewState = hm.get(strCurrentState + strInputChar);  //set strNewState to the value(state transition) associated with the concatenated key 
									strCurrentState = strNewState;                        //set strCurrentState to strNewState
										if(strCurrentState.equals("999"))                //if strCurrentState is 999 print success and exit
										{
											System.out.println("Success");
											success = true;
										}
								}
								else {
									System.out.println("Failure at line:" + intLineCounter + " position:" + (i+1) + ", found character:" + strInputChar);
								}
								}
								 
							}
			if (!(success)) {
				
				
		           System.out.println("Input string ended before success transition."); //if the program makes it this far it has ended without a successful transition
					}

				
			
	 }}


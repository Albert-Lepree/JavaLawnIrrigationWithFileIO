import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

// sorry i didnt add comments but its 10pm and im exhausted
// please add comments later Finn
// Created by Albert Finnegan Lepree :)

public class Main {
	static Scanner fileInput = null;
	static String[] dataInput = new String[20];
	static ArrayList<Integer> cData = new ArrayList<Integer>();
	static ArrayList<Double> tData = new ArrayList<Double>();
	static ArrayList<Double> totZoneWater = new ArrayList<Double>();
	static ArrayList<Double> totHouseWater = new ArrayList<Double>();
	
	static int numHouses = 0;
	static int e = 0;
	static int f = 0;
	static String community;
	static String address;
	static double vCommunity = 0.0;
	
	public static void main(String[] args) {
		setTime();
		setWater();

	}
	
	public static void beginFileValues() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.print("{");
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void endFileValues() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.print("}");
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void writeFileValues() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.printf("%.2f, " ,totZoneWater.get(e));
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void writeHouseValues() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.printf("(%.2f)\n" ,totHouseWater.get(f));
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void writeCommunity() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.printf("%S, " , community);
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void writeCommunityValues() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.printf("(%.2f)\n\n" , vCommunity);
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void writeAddress() {
		try {
			  FileOutputStream appendFile = new FileOutputStream( new File("src/write.txt"), true);
		      PrintWriter fileWriter = new PrintWriter(appendFile);
		      fileWriter.printf("%S " , address);
		      fileWriter.close();
		      appendFile.close();
		}
		catch (IOException e) {
			 System.out.println ("FileWriter: File I/O Error");
			 System.exit(1);
		}
	}
	
	public static void setWater() {
		try {
			fileInput = new Scanner(new File("src/input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("could not find file");
		}

		// paste from here

		int b = 0;
		int c = 0;
		while (fileInput.hasNext()) {
			dataInput = fileInput.nextLine().split("[{(,)]");// pulls data from a file and puts it into an array
			community = dataInput[0];
			writeCommunity();
			address = dataInput[1];
			writeAddress();
			
			int numZones = 0;
			beginFileValues();
			for (int i = 14; i < dataInput.length; i += 13) {
				numZones++;
// test	    		System.out.println(numZones);
			}

// test	    	System.out.println("house" + ++b);
			for (int i = 0; i < dataInput.length; i++) {
// test				System.out.println(i + ": " + dataInput[i]);
				
				
				dataInput[i] = dataInput[i].replaceAll("[^0-9]", "");
				dataInput[1] = "new House";

				if (dataInput[i].length() != 0) {
// test					System.out.println(i + ": " + dataInput[i]);

					if (Character.isLetter(dataInput[i].charAt(0))) {
						numHouses++;
					} else {
						int tmp = Integer.parseInt(dataInput[i]);
						cData.add(tmp);
					}
				}
			}
			int n = 0;
			System.out.println(); // prints new line
			System.out.println("\nZone Water: "); // testing purposes
			double totHWater = 0.0;
			for (int i = 0; i < numZones; i++) {
				int totZWater = (cData.get(n) * cData.get(n + 1)) + (cData.get(n+2) * cData.get(n + 3)) + (cData.get(n+4) * cData.get(n + 5)); //gets total GPH of array
				double tmp2 = tData.get(e); // gets time and assigns it to a value
				int hour = 60; // assigns an 60 minutes to a variable to be divided
				double time = (tmp2/hour); // divides time by 60 to better multiply value
				double tmp = totZWater * time;
				
				totZoneWater.add(tmp); // want to add zone * time
				System.out.println("zone " + i + ": " + totZoneWater.get(e));
				
				writeFileValues(); // writes the value to the file each time
				
				totHWater += totZoneWater.get(e);
				
				e++; // adds 1 every time so it can accurately pick which times to multiply
				n+=6;// adds 6 so it can pick values out of array accurately
			}
			cData.clear(); // clears so you dont have to change the position of things in array
			endFileValues();// adds a curly bracket to the end of the zone values
			
			totHouseWater.add(totHWater);// adds total house water to an array to be summed
			writeHouseValues();// writes total house water to file
			f++; // adds 1 to f so it can pick which value in array to write to file
		}
		
		for (int i = 0; i<totHouseWater.size(); i++) {
			vCommunity += totHouseWater.get(i);
		}
		writeCommunity();
		writeCommunityValues();
		
		
		fileInput.close();
		// end of file
	}

	public static void setTime() {
		try {
			fileInput = new Scanner(new File("src/time.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("lol");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("I0Exception");
			System.exit(1);
		}

		while (fileInput.hasNextLine()) {
			String[] dataInput = fileInput.nextLine().split("[{,]");
			for (int i = 0; i < dataInput.length; i++) {
// test					System.out.println(i + ": " + dataInput[i] + "  ");
				dataInput[1] = "new House";
				dataInput[i] = dataInput[i].replaceAll("[^0-9]", "");

				if (dataInput[i].length() != 0) {
// test					System.out.println(i + ": " + dataInput[i]);
					Double tmp = Double.parseDouble(dataInput[i]);
					tData.add(tmp);
				}
			}
		}
		for (int i = 0; i < tData.size(); i++) { // for testing purposes
			System.out.println(tData.get(i));
		}
		System.out.println();
		fileInput.close();
	}
}

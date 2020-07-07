package datageneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

	public static void main(String[] args) {

		dataGenerator(100, "DEs");
	}

	public static void dataGenerator(int length, String filename) {
		String fileLine = "";
		ArrayList<Integer> values = new ArrayList<Integer>();

		// Create a shuffled list of specified range
		// Each list contains only unique values.
//		for (int i = 1; i <= length; i++) {
//			values.add(new Integer(i));
//		}
//		Collections.shuffle(values);

		//For Testing Values
//		int randomNum;
//		for (int i = 1; i <= length; i++) {
//			randomNum = ThreadLocalRandom.current().nextInt(1, 100000 + 1);
//			values.add(randomNum);
//		}
		
		
		
		// Create output file and FileWriter
		try {
			File myFile = new File(filename + ".txt");
			if (myFile.createNewFile()) {
				System.out.println("File created successfully: " + myFile.getName());
			} else {
				System.out.println("File name already exists.");
				throw new IOException();
			}
			FileWriter output = new FileWriter(myFile);

			// Write to file
			for (int i = 0; i < length; i++) {
				// No new line at end of file.
				if (i == length - 1) {
					fileLine += "DE";
					//fileLine += "PT " + "P" + values.get(i);
					output.write(fileLine);
				} else {
					fileLine += "DE" + "\n";
					//fileLine += "PT " + "P" + values.get(i) + "\n";
					output.write(fileLine);
					fileLine = "";
				}
			}
			output.close();

		} catch (IOException e) {
			System.out.println("Error: IO Exception.");
			e.printStackTrace();
		}
	}
}

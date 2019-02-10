package com.justinschaaf.yaosja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The primary class for managing CSV (Comma Separated Values) data
 * 
 * @author Justin Schaaf
 * @since 1.0.0
 *
 */
public class CSVReader {

	File file;

	public CSVReader(File file) {
		this.file = file;
	}

	/**
	 * 
	 * Read a CSV file and return the data in the form of a List<ArrayList<String>>
	 * 
	 * @return The CSV's as a List<ArrayList<String>> in the format {{tag, value, value, ...}}
	 * 
	 */
	public List<ArrayList<String>> readCSV() {
		
		try {
			
			InputStream inputStream = new FileInputStream(file);
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		    List<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
		    String line;
		    
		    while((line = bufferedReader.readLine()) != null) {
		    	input.add(formatCSVObject(line));
		    }
		    
		    bufferedReader.close();
		    return input;
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * Format a CSV line as a CSV object formatted as {tag, values}
	 * 
	 * @param csvLine The CSV line to convert into a CSV object, formatted as "tag,value,value..."
	 * @return ArrayList<String> CSV object formatted as {tag, value, ...}
	 * 
	 */
	public ArrayList<String> formatCSVObject(String csvLine) {
		
		ArrayList<String> csvObjectArray = new ArrayList<String>();
		String value = "";
		
		for (int i = 0; i < csvLine.length(); i++) {
			
			char character = csvLine.charAt(i);
			
			if (character == ',') {
				csvObjectArray.add(value);
				value = "";
			} else {
				value += Character.toString(character);
			}
			
		}
		
		csvObjectArray.add(value);
		return csvObjectArray;
		
	}

	/**
	 *
	 * Gets the values of a specified CSV tag
	 *
	 * @param file The file to get the value from
	 * @param tag The tag to get the value of
	 * @return Values of the CSV tag as an ArrayList<String>
	 *
	 */
	public ArrayList<String> getValue(File file, String tag) {

		int index = getIndex(file, tag);
		List<ArrayList<String>> csv = readCSV();
		ArrayList<String> line = csv.get(index);
		line.remove(0);

		return line;

	}
	
	/**
	 * 
	 * Gets the index of a specified CSV tag
	 * 
	 * @param file The file to get the index from
	 * @param tag The tag to look for the index of
	 * @return Index of the item in the csv file
	 * 
	 */
	public int getIndex(File file, String tag) {
		
		List<ArrayList<String>> csv = readCSV();
		int index = 0;
		
		for (int i = 0; i < csv.size(); i++) {
			
			ArrayList<String> line = csv.get(i);
			if(line.get(0).equalsIgnoreCase(tag)) {
				index = i;
				break;
			}
			
		}
		
		return index;
		
	}
	
	/**
	 * 
	 * Check if data exists for the given tag
	 * 
	 * @param file The file to look through
	 * @param tag The tag to look for
	 * 
	 * @return True if data is found -- False if data is not found
	 * 
	 */
	public boolean exists(File file, String tag) {
		
		boolean exists = false;
		List<ArrayList<String>> csv = readCSV();
		
		for (int i = 0; i < csv.size(); i++) {
			
			ArrayList<String> line = csv.get(i);
			if(line.get(0).equalsIgnoreCase(tag)) {
				exists = true;
				break;
			}
			
		}
		
		return exists;
		
	}
	
}

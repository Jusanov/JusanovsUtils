package com.justinschaaf.utils.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * The primary class for managing CSV (Comma Separated Values) data
 * 
 * @author Justin Schaaf
 * @since 1.0.0
 *
 */
public class CSVManager {

	/**
	 * 
	 * Read a CSV file and return the data in the form of a List<ArrayList<String>>
	 * 
	 * @param file The file to read the csv's from
	 * 
	 * @return The CSV's as a List<ArrayList<String>> in the format {{tag, value, value, ...}}
	 * 
	 */
	public static List<ArrayList<String>> readCSV(File file) {
		
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
	 * Build a CSV file from the given CSV data
	 * 
	 * @param csv The csv data to be writing to the file as a List<ArrayList<String>> in the format {{tag, value, value, ...}}
	 * @param temp The temporary file to place the objects in
	 * @param dest The final destination of the objects
	 * 
	 */
	public static void buildCSV(List<ArrayList<String>> csv, File temp, File dest) {
		
		try {
			
			OutputStream outputStream = new FileOutputStream(temp);
		    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		    Iterator<ArrayList<String>> inputIterator = csv.iterator();
		    
		    while (inputIterator.hasNext()) {
		    	ArrayList<String> line = inputIterator.next();
		    	bufferedWriter.write(formatCSVLine(line) + "\n");
		    }
		    
		    bufferedWriter.close();
		    FileManager.moveFile(temp, dest);
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Format an individual CSV object as {tag, values} to a CSV line
	 * 
	 * @param csvObject The CSV object to convert to line form, formatted as {tag, value, value, ...}
	 * 
	 * @return The CSV line, not including "\n"
	 * 
	 */
	public static String formatCSVLine(ArrayList<String> csvObject) {
		String string = "";
		for(int i = 0; i < csvObject.size(); i++) {
			if (i == csvObject.size() - 1) {
				string = string + csvObject.get(i);
			} else {
				string = string + csvObject.get(i) + ",";
			}
		}
		return string;
	}
	
	/**
	 * 
	 * Format a CSV line as a CSV object formatted as {tag, values}
	 * 
	 * @param csvLine The CSV line to convert into a CSV object, formatted as "tag,value,value..."
	 * @return ArrayList<String> CSV object formatted as {tag, value, ...}
	 * 
	 */
	public static ArrayList<String> formatCSVObject(String csvLine) {
		
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
	 * Gets the index of a specified CSV tag
	 * 
	 * @param file The file to get the index from
	 * @param tag The tag to look for the index of
	 * @return Index of the item in the csv file
	 * 
	 */
	public static int getIndex(File file, String tag) {
		
		List<ArrayList<String>> csv = readCSV(file);
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
	 * Gets the values of a specified CSV tag
	 * 
	 * @param file The file to get the value from
	 * @param tag The tag to get the value of
	 * @return Values of the CSV tag as an ArrayList<String>
	 * 
	 */
	public static ArrayList<String> getValue(File file, String tag) {
		
		int index = getIndex(file, tag);
		List<ArrayList<String>> csv = readCSV(file);
		ArrayList<String> line = csv.get(index);
		line.remove(0);
		
		return line;
		
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
	public static boolean exists(File file, String tag) {
		
		boolean exists = false;
		List<ArrayList<String>> csv = readCSV(file);
		
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

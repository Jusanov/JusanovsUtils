package com.justinschaaf.javautils;

import java.io.*;
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
public class CSV {

	File file;

	public CSV(File file) {
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
	 * Build a CSV file from the given CSV data
	 *
	 * @param csv The csv data to be writing to the file as a List<ArrayList<String>> in the format {{tag, value, value, ...}}
	 *
	 */
	public void buildCSV(List<ArrayList<String>> csv) {

		try {

			File temp = new File(file.getName() + Math.round(Math.random() * Math.random() * 10000));

			OutputStream outputStream = new FileOutputStream(temp);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
			Iterator<ArrayList<String>> inputIterator = csv.iterator();

			while (inputIterator.hasNext()) {
				ArrayList<String> line = inputIterator.next();
				bufferedWriter.write(formatCSVLine(line) + "\n");
			}

			bufferedWriter.close();
			FileManager.moveFile(temp, file);
			temp.delete();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	 * Format an individual CSV object as {tag, values} to a CSV line
	 *
	 * @param csvObject The CSV object to convert to line form, formatted as {tag, value, value, ...}
	 *
	 * @return The CSV line, not including "\n"
	 *
	 */
	public String formatCSVLine(ArrayList<String> csvObject) {
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
	 * Gets the values of a specified CSV tag
	 *
	 * @param tag The tag to get the value of
	 * @return Values of the CSV tag as an ArrayList<String>
	 *
	 */
	public ArrayList<String> getValue(String tag) {

		int index = getIndex(tag);
		List<ArrayList<String>> csv = readCSV();
		ArrayList<String> line = csv.get(index);
		line.remove(0);

		return line;

	}

    /**
     *
     * Gets the values of a specified CSV tag
     *
     * @param index The index to get the value of
     * @return Values of the CSV tag as an ArrayList<String>, including the tag itself!
     *
     */
    public ArrayList<String> getValue(int index) {

        List<ArrayList<String>> csv = readCSV();
        ArrayList<String> line = csv.get(index);

        return line;

    }

    /**
     *
     * Create a new CSV object and add it to the CSV
     *
     * @param csvObject The CSV object to add, with the element at index 0 being the tag
     *
     */
	public void createValue(ArrayList<String> csvObject) {
		List<ArrayList<String>> csv = readCSV();
		csv.add(csvObject);
		buildCSV(csv);
	}

    /**
     *
     * Sets the value of the given tag
     *
     * @param tag The tag to set the new value to
     * @param value The new value for the tag, not including the tag itself
     *
     */
    public void setValue(String tag, ArrayList<String> value) {
        setValue(tag, value, false);
    }

    /**
     *
     * Sets the value of the given tag
     *
     * @param tag The tag to set the new value to
     * @param value The new value for the tag, not including the tag itself
     * @param tagIncluded Whether or not the tag is included in the new value
     *
     */
	public void setValue(String tag, ArrayList<String> value, Boolean tagIncluded) {
	    int index = getIndex(tag);
        List<ArrayList<String>> csv = readCSV();
        if (!tagIncluded) value.add(0, tag);
	    csv.set(index, value);
        buildCSV(csv);
    }
	
	/**
	 * 
	 * Gets the index of a specified CSV tag
	 *
	 * @param tag The tag to look for the index of
	 * @return Index of the item in the csv file
	 * 
	 */
	public int getIndex(String tag) {
		
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
	 * @param tag The tag to look for
	 * 
	 * @return True if data is found -- False if data is not found
	 * 
	 */
	public boolean exists(String tag) {
		
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

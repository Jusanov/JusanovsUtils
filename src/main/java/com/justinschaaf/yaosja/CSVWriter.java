package com.justinschaaf.yaosja;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class CSVWriter {

    File file;

    public CSVWriter(File file) {
        this.file = file;
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

}

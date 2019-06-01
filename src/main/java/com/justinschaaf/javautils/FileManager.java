package com.justinschaaf.javautils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * 
 * The primary class for managing files
 * 
 * @author Justin Schaaf
 * @since 1.0.0
 *
 */
public class FileManager {

	/**
	 * 
	 * Move the defined source file to the defined destination file
	 * 
	 * @param source The source file to move
	 * @param dest The file to move the source file to
	 * 
	 */
	public static void moveFile(File source, File dest) {
		try {
			Files.copy(FileSystems.getDefault().getPath(source.getPath()), new FileOutputStream(dest));
			Files.deleteIfExists(FileSystems.getDefault().getPath(source.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

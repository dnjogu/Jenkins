package com.account.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JsonUtil {
	private String fileName = "";
	
	public JsonUtil(String fName){
		this.fileName = fName;
	}
	
	public String  getJsonString() throws IOException{
		File file = new File(fileName);
		return readFile(file.getAbsolutePath());
	}

	
	public String deleteJsonFile() throws IOException {
		File file = new File(fileName);
		return file.delete()?file.getAbsolutePath():"";
		
	}
		
	private String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        scanner.close();
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}

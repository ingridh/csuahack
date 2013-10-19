package com.mkyong;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 

Public class Recovery {
	public static void saveFile(Config g) {
		File f = new File("config");
	        try {
	            BufferedWriter out = new BufferedWriter(new FileWriter(f, false));
	            out.newLine();
	            out.flush();
	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
	public static Config openFile() {
			File f = new File("config");
			Congig c = new Config();
	        try {
		
	            BufferedReader in = new BufferedReader(new FileReader(f));
	            String s = null;
	            while ((s=out.readline()) != null) {

	            }
	            out.close();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } return c;
	}
}


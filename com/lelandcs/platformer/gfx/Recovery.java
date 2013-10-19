package com.leland.cs.platformer.gfx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
 

public class Recovery {

	public static void saveFile(FishManager g) {
	
		File f = new File("config");
	        try {
	            BufferedWriter out = new BufferedWriter(new FileWriter(f, false));
	            for (Fish fish: g.getFish()) {
	            	out.write(f.getName()); //get name
	            	out.write(f.getDate());
	            }

	            out.write();
	            out.newLine();
	            out.flush();
	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}

	public static void openFile(FishManger manager) {
			File f = new File("config");
	        try {
		
	            BufferedReader in = new BufferedReader(new FileReader(f));
	            String s = null;
	            while (( s = in.readline()) != null) {
	            	manager.fishes.add(new Fish(s, new Date(in.readLine()), manager));
	            }
	            in.close();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}

package com.leland.cs.platformer.gfx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 

Public class Recovery {

	public static void saveFile(FishManager g) {
	
		File f = new File("config");
	        try {
	            BufferedWriter out = new BufferedWriter(new FileWriter(f, false));
	            for (Fish f: g.getFish()) {
	            	out.write(f.getName()); //get name
	            	out.write(f.getDate());
	            }

	            out.right();
	            out.newLine();
	            out.flush();
	            out.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}

	public static FishManager openFile() {
			File f = new File("config");
			FishManager c = new FishManager();
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


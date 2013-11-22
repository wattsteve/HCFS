package org.hcfs;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FSWriteTest {

	public static void main(String[] args) throws Exception{
		
		// Catch incorrect input parameters
		if(args.length<4){
			System.out.println("\n Usage:      FSWriteTest fileSize writeSize fileName fileSystem");
			System.out.println("\n Example:    FSWriteTest 1099511627776 100 outputstream.txt local");
			System.out.println("\n fileSize:   Total amount to write to File in bytes");
			System.out.println("\n writeSize:  Amount of bytes to write within each write operation");
			System.out.println("\n fileName:   The name of the file to be created to write data to under the FSWriteTest directory");
			System.out.println("\n fileSystem: Local or Distributed");
			System.exit(0);
		}
		
		// Parse Input Parameters
		long fileSize = Long.parseLong(args[0]);  // Amount of Data to be written
		int writeSize = Integer.parseInt(args[1]); // Amount to be written per write
		String fileName = args[2];  // Name of File to write to
		boolean local = args[3].toUpperCase().trim().equals("LOCAL");
		
		long start = 0;
		long finish = 0;
		
		
		if (local){ 	// Write the File to Local FileSystem
			writeToLocal(fileSize, fileName);
		} 
		else {	
		    writeToDFS(fileSize, fileName);
		}
		
	}

    public static void writeToLocal(long fileSize,String fileName){
        long start;
        long finish;
        File aFile = createFile(fileName);
        start = System.currentTimeMillis();
        try {
        	FileOutputStream os = new FileOutputStream(aFile);
        	writeToOutputStream(os,fileSize);
        	os.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        finish = System.currentTimeMillis();
        System.out.println("DONE : Total ms run:" + (finish - start) + " file out size: " + aFile.length() );
    }

    public static void writeToDFS(long fileSize,String fileName) throws IOException{
        long start;
        long finish;
        // Write the File to the Distributed File System
		FileSystem dfs = null;
		Configuration conf = new Configuration();
		Path outfile = new Path(fileName);
		FSDataOutputStream os = null;
		dfs = FileSystem.get(conf);
		start = System.currentTimeMillis();
		os = dfs.create(outfile);
		writeToOutputStream(os,fileSize);
		os.close();
		finish = System.currentTimeMillis();
		System.out.println("DONE : Total ms run:" + (finish - start) + " file out size: " + dfs.getFileStatus(outfile).getLen());
    }
	
	// Writes the data to the OutputStream provided
	private static void writeToOutputStream(OutputStream os, long size) throws IOException{
		for(int i=0;i<size;i++){
			os.write(0);
		}
	}
	
	private static File createFile(String fileName){
	
	  File theDir = new File("FSWriteTest");

	  if (!theDir.exists()){
		  boolean success = theDir.mkdir();  
		  return new File("FSWriteTest" + "/" + fileName);
	  } else {
		  return new File("FSWriteTest" + "/" + fileName);
	  }
	}
	  
}

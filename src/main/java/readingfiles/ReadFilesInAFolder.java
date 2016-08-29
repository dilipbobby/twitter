package readingfiles;

import java.io.File;

public class ReadFilesInAFolder {
	
	  public static File folder = new File("/home/storm/Documents/datasetsTopics");
	  static String temp = "";

	  public static void main(String[] args) {
		  
		  String path="/home/storm/Documents/datasetsTopics";
		  ReadFilesInAFolder.getFiles(path);
	    
		  
		  
		  // TODO Auto-generated method stub
	    //System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
	    //listFilesForFolder(folder);
	  }

	  static /*public static void listFilesForFolder(final File folder) {

	    for (final File fileEntry : folder.listFiles()) {
	      if (fileEntry.isDirectory()) {
	        // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
	        listFilesForFolder(fileEntry);
	      } else {
	        if (fileEntry.isFile()) {
	          temp = fileEntry.getName();
	          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("csv"))
	            System.out.println("File= " + folder.getAbsolutePath()+ "/" + fileEntry.getName());
	        }

	      }
	    }
	  }*/

	  void getFiles(String dirpath){
	        //String dirPath = "E:/folder_name";
	        File dir = new File(dirpath);
	        String[] files = dir.list();
	        if (files.length == 0) {
	            System.out.println("The directory is empty");
	        } else {
	            for (String aFile : files) {
	                System.out.println(aFile);
	            }
	        }
	    }
	  
}

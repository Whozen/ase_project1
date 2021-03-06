import java.util.ArrayList;
import java.util.Iterator;

public class Directories extends FileSystem {
	//Initialize an array to store all the FileSystems that will be added to the Directory
	ArrayList fileSystems = new ArrayList();
	String directoryName;
	
	//Constructor
	public Directories(String newDirectoryName) {
		directoryName = newDirectoryName;
	}
	
	//Return Directory Name
	public String getName() { 
		return directoryName;
	}


    //Add new FileSystem passed from parameter to the current Directory
	public void add(FileSystem newFileSystem) {
		fileSystems.add(newFileSystem);
	}
	
	
	//Delete the FileSystem passed from parameter from the current FileSystem
	public void remove(FileSystem newFileSystem) {
		fileSystems.remove(newFileSystem);
	}


	//Iterate through the current FileSystem to get the FileSystem with name given in parameter
	public FileSystem getFileSystem(String name) {	
		Iterator fileIterator = fileSystems.iterator();
		
		while(fileIterator.hasNext()) { 
			FileSystem fileInfo = (FileSystem)fileIterator.next();
			String fName = fileInfo.getName();
			if( fName.equals(name) ) {
				return fileInfo;
			}
		}

		throw new UnsupportedOperationException();
	}
	

	//Iterate through the current FileSystem to display the FileSystems in it
	//It will call displayFileInfo function again for each FileSystem in it.
	public void displayFileInfo() {
		System.out.println("Ls: " + directoryName + "\n");		
		Iterator fileIterator = fileSystems.iterator();
	
		while(fileIterator.hasNext()) {
			FileSystem fileInfo = (FileSystem) fileIterator.next();
			if(fileInfo instanceof File) {
				fileInfo.displayFileInfo();
			}
		}
	}


	//Iterate through the current FileSystem to display the size of all the FileSystems in it
	//It will call printSize function again for each FileSystem in it
	public void printSize(){
		System.out.println("Size inside " + directoryName + "\n");	

		Iterator fileIterator = fileSystems.iterator();
		
		while(fileIterator.hasNext()) { 
			FileSystem fileInfo = (FileSystem) fileIterator.next();
			fileInfo.printSize();
		}
	}

}
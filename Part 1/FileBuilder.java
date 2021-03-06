import java.util.ArrayList;
import java.util.Iterator;

public class FileBuilder implements FileBuilderFactory { 
    private FileSystem mainDir;
    private FileSystem subDir;
    private FileSystem subFile;

    //FileIterators are used to iterate through the FileSystem and display the contents in it.
    //showAll is used to display current FileSystems contents
    private FileIterator showAll;
    //tempShowAll is used to display contents of FileSystem inside the current FileSystem
    private FileIterator tempShowAll;

    //A FileSystem array used to store path of the current FileSystem
    private ArrayList<FileSystem> myPath = new ArrayList<FileSystem>();
    
    //Pointer of the current path of FileSystem
    private int pathPointer;
    

    //Constructor
    //Initializes the FileSystem with Root Directory
    //Adds the Root Directory to the path array and updates pathPointer
    public FileBuilder() { 
        this.mainDir = new Directories("Root");
        this.showAll = new FileIterator(this.mainDir);
        //this.path = new FileSystem[10];
        //this.path[0] = this.mainDir;
        this.myPath.add(this.mainDir);
        this.pathPointer = 0;

    }
    

    //Create a new directory in the current directory with directory name given in parameter
    public void mkdir(String dirName) {
        this.mainDir.add( new Directories(dirName) );
        System.out.println( "Mkdir: " + dirName + " Directory created\n" );
        System.out.println("------------------------------------\n");
    } 


    //Create a new file in the current directory with file name and size given in parameter 
    public void create(String fileName, int fileSize) {
        this.mainDir.add( new File(fileName, fileSize ) );
        System.out.println( "Create: " + fileName + " file created\n" );
        System.out.println("------------------------------------\n");
    } 
    

    //Get the FileSystem with name provided in parameter and remove a file or directory from 
    //the current directory
    public void del(String fileName) {   
        try {
            this.subFile = this.mainDir.getFileSystem(fileName);
            this.mainDir.remove(this.subFile);
            System.out.println( "Del: " + fileName + " has successfully been removed.\n" );
        } catch (UnsupportedOperationException e) {
            System.out.println( "Del:" + fileName + " not found.\n" );
        }
        System.out.println("------------------------------------\n");
    }


    //Get the FileSystem with name provided in parameter and call printSize() function
    public void size(String fileName) {
        try {
            this.subDir = this.mainDir.getFileSystem(fileName);
            this.subDir.printSize();
        } catch (UnsupportedOperationException e) {
            System.out.println( "Size: " + fileName + " not found.\n" );
        }
        System.out.println("------------------------------------\n");
    }


    //If the parameter given is "..", remove the current FileSystem pointed by pathPointer and
    //set the pathPointer to the previous FileSystem which sets new path in the FileSystem. 
    //If parameter is a filename, get the FileSystem in the current FileSystem using name given
    //in parameter and add it to the path.
    //Display the current path in either case using path array and update the showAll Iterator 
    //with the current FileSystem
    public void cd(String fileName) {
        if( fileName.equals("..") ) {
            this.myPath.remove(this.pathPointer);
            this.pathPointer--;
            this.mainDir = this.myPath.get(this.pathPointer);
            this.showAll = new FileIterator(this.mainDir);
            System.out.println("Cd: Directory Changed to " + this.mainDir.getName() + "\n");
        } else {
            try {
                this.subDir = this.mainDir.getFileSystem(fileName);
                this.mainDir = this.subDir;
                this.showAll = new FileIterator(this.mainDir);
                this.pathPointer++;
                this.myPath.add(this.mainDir);
                System.out.println("Cd: Directory Changed to " + fileName + '\n');
            } catch (UnsupportedOperationException e) {
                System.out.println( fileName + " not found.\n" );
            }
        }
        //Display the new path of the FileSystem
        System.out.println( "Current Path:" );
        for (int i = 0; i < myPath.size(); i++) {
            System.out.print( myPath.get(i).getName() + "/" );
        }
        System.out.println("\n\n------------------------------------\n");
    }


    //Get the fileSystem from the current Directory using the fileName passed in the parameter 
    //Update the tempShowAll iterator with the FileSystems instance and 
    //call getFileList() function
    public void ls(String fileName) {
        try {
            this.subDir = this.mainDir.getFileSystem(fileName);
            this.tempShowAll = new FileIterator(this.subDir);
            this.tempShowAll.getFileList();
        } catch (UnsupportedOperationException e) {
            System.out.println( fileName + " not found.\n" );
        }
        System.out.println("------------------------------------\n");
    }


    //Display all the contents in the current FileSystem by calling getFileList() function
    public void ls() {
        this.showAll.getFileList();
        System.out.println("------------------------------------\n");
    }


    //Display appropriate message and exit the program
    public int exit() {
        System.out.println( "Exiting the system\n" );
        System.out.println("------------------------------------\n");
        return 0;
    }
} 
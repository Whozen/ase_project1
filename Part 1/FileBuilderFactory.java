public interface FileBuilderFactory 
{ 	
    //Create directory in the current directory
    public void mkdir(String fileName); 

    //Create a new file in the current directory
    public void create(String fileName, int fileSize); 

    //Change directory to the given filename
    public void cd(String fileName); 

    //Remove a file or directory in the current directory
    public void del(String fileName); 

    //Get size of file or directory
    public void size(String fileName);

    //Display the file info or display all the files in the Directory 
    public void ls(String fileName); 

    //Display all the files in the current Directory
    public void ls(); 

    //Exit the program
    public int exit(); 
} 
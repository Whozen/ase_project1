public class ExitVisitor implements Visitor { 
    private DeleteVisitor delVisitor;

    public void visit(Directory directory) { 
        throw new UnsupportedOperationException();
    }

    //Initialize DeleteVisitors object if it hasn't been initialized
    //Call the function visit from the DeleteVisitor and pass directory, fs and 1 which indicates 
    //real delete
    public void visit(Directory directory, FileSystem fs) {
    	if(delVisitor == null) {
            delVisitor = new DeleteVisitor();
        }

        delVisitor.visit(directory, fs, 1);
    }

    public void visit(Directory directory, FileSystem fs, int real) { 
        throw new UnsupportedOperationException();
    }

    public void visit(File file) { 
        throw new UnsupportedOperationException();
    }

    public void visit(File file, int newSize) { 
        throw new UnsupportedOperationException();
    }
   
} 
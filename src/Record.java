import java.io.*;
import java.util.*;
public class Record extends Tetris{
	
	
	public void write(int score) {
		try {

	int number;
    number =score;
    PrintWriter pw = new PrintWriter(new FileWriter( "D:\\record.txt" ,true) );
	//System.out.println(""+number);
	pw.println(number);
	pw.close();
		}catch(IOException ioe) {
			System.out.println(ioe);
			
		}
	}

	

	public void read()  {
    
  try {
	  BufferedReader reader = new BufferedReader(new FileReader("D:\\record.txt"));

	    int i = 0;
	   
	    String line = null;
	    
	    while ((line = reader.readLine()) != null) {
	        String[] vStrs = line.split(" ");
	        for (String str : vStrs) {
	        	if(i<100) {
	        		result[i] = Integer.parseInt(str);
	        		i++;
	        		
	        	}
	        	break;
	        }
	        
	    }
	    Arrays.sort(result);
	    int n = result.length;
	    for (int k = 0; k < n - 1; k++) {   
	    	  
	        for (int j = 0; j < n - 1; j++) {   
	    
	          if (result[j] < result[j + 1]) {   
	    
	            int temp = result[j];   
	    
	            result[j] = result[j + 1];   
	    
	            result[j + 1] = temp;   
	    
	          }   
	    
	        }   
	    
	      }   
	    //System.out.println(result[0]);
	    reader.close();

} 
   catch (IOException e) {
        System.out.println("Uh oh, got an IOException error!");
        e.printStackTrace();
    
}

    
  
	}
	

	
	
	
	
	
	
	
	
	}
	
	
	
	

	
	



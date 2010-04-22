package kth.csc.inda;

import java.io.*;
import java.net.*;

/**
 * 
 * @author Mest lucas inte så mycket jonas...
 * @version £17$18
 * Good class. Better than bad class.
 */
public class ChatServer {
	private int portNumber;
	private ServerSocket myServerSocket;
	private Socket mySocket;
	
	public ChatServer(){		
	}
	
	public void initialize(){
		portNumber = 5555;
	}
}

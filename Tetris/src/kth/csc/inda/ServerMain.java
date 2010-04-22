package kth.csc.inda;

import java.net.*;
import java.io.*;

public class ServerMain {
	private final static int portNumber = 5555;

	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		boolean listening = true;
		
		try{
			serverSocket = new ServerSocket(portNumber);
		}catch(IOException e){
			System.err.println("Could not listen to port: " + portNumber);
			System.exit(-1);
		}
		
		while(listening){
		//	new ServerThread(serverSocket.accept()).start();
		}
	}
}

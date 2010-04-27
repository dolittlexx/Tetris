package kth.csc.inda;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Mest lucas inte så mycket jonas...
 * @version £17$18
 * Good class. Better than bad class.
 */
public class ChatServer {
	private ServerSocket myServerSocket;
	private int portNumber;
	private HashMap<String, String> serverList;
	private HashSet<ChatServerThread> threadList;
	
	public static void main(String[] args){
		ChatServer myServer = new ChatServer();
		myServer.initialize();
		try{
			myServer.listen();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public ChatServer(){
		serverList = new HashMap<String, String>();
		threadList = new HashSet<ChatServerThread>();
	}
	
	public void initialize(){
		portNumber = 5555;
		try{
			myServerSocket = new ServerSocket(portNumber);
		} catch(IOException e){
			System.out.println("Could not listen to port " + portNumber);
		}
	}
	
	public void listen() throws IOException{
		boolean listening = true;
		while(listening){
			Socket myNewConnectionSocket = myServerSocket.accept();
			ChatServerThread myThread = new ChatServerThread(
					myNewConnectionSocket, serverList, this);
			myThread.start();
			threadList.add(myThread);
		}
	}
	
	public void broadcastMessage(String message){
		Iterator<ChatServerThread> it = threadList.iterator();
		while(it.hasNext()){
			ChatServerThread myThread = it.next();
			myThread.sendMessage(message);
		}
	}
}
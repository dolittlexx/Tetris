package kth.csc.inda;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class ChatClient {
	private Socket mySocket;
	private PrintWriter out;
	private BufferedReader in;
	private String nickName;
	private JTextArea chatDisplay;
	private ServerThread serverThread;
	
	public ChatClient(){
	}
	
	public void initialize(JTextArea chatDisplay, String name, int port, String server){
    	//Initializing statics
    	this.chatDisplay = chatDisplay;
    	nickName = name;
    	//Initializing sockets and readers
    	if(!connect(port, server)){
    		System.exit(1);
    	}
	}
	
	public boolean connect(int portNumber, String serverName){
		try {
	        mySocket = new Socket(serverName, portNumber);
	        out = new PrintWriter(mySocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
	        if(serverThread != null)
	        	serverThread.stop();
	        serverThread = new ServerThread(mySocket, this);
	        serverThread.start();
	    } catch (UnknownHostException e) {
	        System.err.println("Couldn't find host: " + serverName);
	        return false;
	    } catch (IOException e) {
	    	System.err.println(e);
	        System.err.println("Couldn't get I/O for " + serverName);
	        return false;
	    }
		return true;
	}
    
    public void printMsg(String myMessage){
    	chatDisplay.append(myMessage + "\n");
    	chatDisplay.setCaretPosition(chatDisplay.getDocument().getLength());
    }
    
    public void sendMsg(String myMessage){
    	out.println(nickName + ": " + myMessage);
    }
    
    public void setNickName(String newNick){
    	nickName = newNick;
    }
}
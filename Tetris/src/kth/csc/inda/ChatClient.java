package kth.csc.inda;

import java.io.*;
import java.net.*;

import javax.swing.JTextArea;

public class ChatClient {
	private String serverName;
	private int portNumber;
	private ServerSocket myServerSocket;
	private Socket mySocket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean isServer;
	private String nickName;
	private JTextArea chatDisplay;
	
	public void initialize(boolean isServer, JTextArea chatDisplay){
    	//Initializing statics
    	serverName = "127.0.0.1";
    	portNumber = 5555;
    	this.chatDisplay = chatDisplay; 
    	//Initializing sockets and readers
    	myServerSocket = null;
        mySocket = null;
        out = null;
        in = null;
        this.isServer = isServer;
        nickName = "Client";
        
        try {
            if(isServer){
            	WaitingWindow waitingWindow = new WaitingWindow();
            	myServerSocket = new ServerSocket(portNumber);
            	mySocket = myServerSocket.accept();
            	waitingWindow.dispose();
            }
            else
            	mySocket = new Socket(serverName, portNumber);
            out = new PrintWriter(mySocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            new ServerThread(mySocket, this).start();
        } catch (UnknownHostException e) {
            System.err.println("Couldn't find host: " + serverName);
            System.exit(1);
        } catch (IOException e) {
        	System.err.println(e);
            System.err.println("Couldn't get I/O for " + serverName);
            System.exit(1);
        }
	}
	
	public void run() throws IOException{
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        System.out.println("Thread called.");
        System.out.println("Thread finished calling.");
        while ((fromUser = stdIn.readLine()) != null){
        	sendMsg(fromUser);
        }
        
        out.close();
        in.close();
        stdIn.close();
        mySocket.close();
	}
	
    public static void main(String[] args) throws IOException {
		ChatClient myClient = new ChatClient();
		myClient.initialize(true, null);
		myClient.run();
    }
    
    public void printMsg(String myMessage){
    	chatDisplay.append("Other: " + myMessage + "\n");
    }
    
    public void sendMsg(String myMessage){
    	out.println(myMessage);
    	chatDisplay.append("You: " + myMessage + "\n");
    }
    
    public void setServer(String myServer){
    	serverName = myServer;
    }
    
    public void setPort(int newPort){
    	portNumber = newPort;
    }
    
    public void setNickName(String newNick){
    	nickName = newNick;
    }
    
    
    
    
}
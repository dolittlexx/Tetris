package kth.csc.inda;

import java.net.*;
import java.util.HashMap;
import java.io.*;

public class ChatServerThread extends Thread{
	private Socket socket = null;
	private ChatServer server;
	ChatClient client;
	PrintWriter out;
	BufferedReader in;
	
	public ChatServerThread(Socket socket, HashMap<String, String> ServerList, 
			ChatServer server) throws IOException{
		super("ChatServerThread");
		this.server = server;
		this.socket = socket;
		out = new PrintWriter(this.socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}
	
	public void run(){
		try{
			String myInput;
			while ((myInput = in.readLine()) != null){
				server.broadcastMessage(myInput);
			}
			out.close();
			in.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message){
		out.println(message);
	}
}

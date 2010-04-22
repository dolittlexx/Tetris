package kth.csc.inda;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
	private Socket socket = null;
	ChatClient client;
	
	public ServerThread(Socket socket, ChatClient client){
		super("ServerThread");
		this.socket = socket;
		this.client = client;
	}
	
	public void run(){
		try{
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String myInput;
			String myOutput;
			while ((myInput = in.readLine()) != null){
				System.out.println("Received a message!");
				client.printMsg(myInput);
			}
			out.close();
			in.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

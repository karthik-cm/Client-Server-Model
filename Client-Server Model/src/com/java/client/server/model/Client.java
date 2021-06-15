package com.java.client.server.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	
	private static Socket socket = null;
	private static DataInputStream input = null;
	private static DataOutputStream output = null;
	
	private static final String IP_ADDRESS = "127.0.0.1";
	private static final int PORT = 8000;
	
	
	public Client(String ip, int port) {
		try {
			// Establish connection
			socket = new Socket(ip, port);
			System.out.println("Connection established successfully !");
			
			// Send message : Client -> Server
			sendMessage(socket);
		}
		catch(IOException e) {
			System.out.println("IOException occurred inside Client :::: Client() "+e);
		}
		catch(Exception e) {
			System.out.println("Exception occurred inside Client :::: Client() "+e);
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void sendMessage(Socket socket) {
		try {
			// Initialize input stream
			input = new DataInputStream(System.in);
			
			// Initialize output stream
			output = new DataOutputStream(socket.getOutputStream());
			
			String msg = "";
			while(!msg.equalsIgnoreCase("exit")) {
				msg = input.readLine(); // Read from console
				output.writeUTF(msg); // Write to socket stream
			}
			
			closeSocketConnection();
		}
		catch(IOException e) {
			System.out.println("IOException occurred inside Client :::: sendMessage() "+e);
		}
	}
	
	private static void closeSocketConnection() {
		try {
			if(output != null) {
				output.close();
			}
			if(input != null) {
				input.close();
			}
			if(socket != null) {
				socket.close();
			}
		}
		catch(IOException e) {
			System.out.println("IOException occurred inside Client :::: closeSocketConnection() "+e);
		}
	}

	public static void main(String[] args) {
		new Client(IP_ADDRESS, PORT);
	}

}


package com.java.client.server.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static Socket socket = null;
	private static ServerSocket serverSocket = null;
	private static DataInputStream input = null;
	
	private static final int PORT = 8000;
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started, Waiting for a Client...");
			
			// Receive message : Server <- Client
			receiveMessage(serverSocket);
		}
		catch (IOException e) {
			System.out.println("IOException occurred inside Server :::: Server() "+e);
		}
		catch (Exception e) {
			System.out.println("Exception occurred inside Server :::: Server() "+e);
		}
	}
	
	private void receiveMessage(ServerSocket serverSocket) {
		try{
			socket = serverSocket.accept();
			System.out.println("Client connection accepted !");
			
			// Initialize input stream
			input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			
			String msg = "";
			while(!msg.equalsIgnoreCase("exit")) {
				msg = input.readUTF(); // Read the message from Client
				System.out.println(msg); // Print the message to Console
			}
			
			closeSocketConnection();
		}
		catch (IOException e) {
			System.out.println("IOException occurred inside Server :::: receiveMessage() "+e);
		}
	}

	private static void closeSocketConnection() {
		try {
			if(input != null) {
				input.close();
			}
			if(serverSocket != null) {
				serverSocket.close();
			}
			if(socket != null) {
				socket.close();
			}
		}
		catch(IOException e) {
			System.out.println("IOException occurred inside Server :::: closeSocketConnection() "+e);
		}
	}

	public static void main(String[] args) {
		new Server(PORT);
	}

}


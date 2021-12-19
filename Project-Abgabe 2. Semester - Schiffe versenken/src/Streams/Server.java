package Streams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import ConsoleInterface.MainGame;
import SchiffeVersenken.Field;
import SchiffeVersenken.SchiffeVersenken;

public class Server {

	private SchiffeVersenken p1;
	private MainGame game;

	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;

	ServerSocket serverSocket;
	Socket socket;
	int PORT = 1210;
	private String ipAdress = "127.0.0.1";

	public Server(SchiffeVersenken p1, MainGame game) throws ClassNotFoundException {
		this.p1 = p1;
		this.game = game;

		try {
			buildServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buildServer() throws IOException, ClassNotFoundException {
		System.out.println("benötigte IPAdresse:" + ipAdress);
		serverSocket = new ServerSocket(PORT);
		socket = serverSocket.accept();
		inStream = new ObjectInputStream(socket.getInputStream());
		outStream = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("connnection successfull");

		// bei gelungener Verbindung
		setPhase();
	}

	void setPhase() throws IOException, ClassNotFoundException {
		System.out.println("player 1:");

		Packet serverPacket = game.settingPhase(p1);

		// just p1 is initialzied, the rest is null
		// recieve first sent
		Packet clientPacket = (Packet) inStream.readObject();
		outStream.writeObject(serverPacket);
		System.out.println(clientPacket.getP1().toString() + "first sent from Client recieved");  //debug
		// shooting phase geht nicht mehr im maingame, sondern in client||server
		shotingPhase(serverPacket.getP1(), clientPacket.getP1()); // // clientPacket.getP1 because the rest of the
																	// packet is empty, no more information needed in
																	// this state

	}

	void shotingPhase(SchiffeVersenken p1, SchiffeVersenken p2) throws IOException, ClassNotFoundException {
		SchiffeVersenken temp1 = p1;
		SchiffeVersenken temp2 = p2;

		Packet serverPacket, clientPacket;
		// boolean firstIteration = true;
		while (true) {
			// first shot
			serverPacket = game.shotingPhase(temp1, temp2, true);
			// send each shot
			outStream.writeObject(serverPacket);
			// recieve enemys shot
			clientPacket = (Packet) inStream.readObject(); 
			temp1 = clientPacket.getP1();
			temp2 = clientPacket.getP2();
	

		}
		// serverSocket.close();
	}

}
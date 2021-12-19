package Streams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import ConsoleInterface.MainGame;
import SchiffeVersenken.*;

public class Client {

	String ipAdress;
	int PORT = 1210;
	private SchiffeVersenken p2;

	private MainGame game;

	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	Socket socket;

	public Client(SchiffeVersenken p2, MainGame game) throws IOException, ClassNotFoundException {
		this.p2 = p2;
		this.game = game;
		buildClient();
	}

	private void buildClient() throws IOException, ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("ipAdress requiered\n" + "enter: ");
		ipAdress = scan.nextLine();
		socket = new Socket(ipAdress, PORT);
		System.out.println("connection successfull");
		// erst output stream dann input
		// andersherum gehts nich
		outStream = new ObjectOutputStream(socket.getOutputStream());
		inStream = new ObjectInputStream(socket.getInputStream());

		// wenn succesfull
		setPhase();
	}

	void setPhase() throws IOException, ClassNotFoundException {
		System.out.println("player 2:");
		Packet clientPacket = game.settingPhase(p2);
		// first write and send
		outStream.writeObject(clientPacket);
		Packet serverPacket = (Packet) inStream.readObject();
		System.out.println(clientPacket.getP1().toString() + "first sent from server recieved"); //debug
		shotingPhase(serverPacket.getP1(), clientPacket.getP1()); // just in this
		// case its p1, otherwise its p2, half of the packet is empty

	}

	void shotingPhase(SchiffeVersenken p1, SchiffeVersenken p2) throws ClassNotFoundException, IOException {

		SchiffeVersenken temp1 = null;
		SchiffeVersenken temp2 = null;
		Packet clientPacket, serverPacket;
		while (true) {
		

			serverPacket = (Packet) inStream.readObject();
			temp1 = serverPacket.getP1();
			temp2 = serverPacket.getP2();
			clientPacket = game.shotingPhase(temp1, temp2, false);
			// send this
			outStream.writeObject(clientPacket);
			
		}

	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import rs.ac.bg.fon.ps.domain.Magacioner;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ServerThread extends Thread {

	private ServerSocket serverSocket;
	private List<HandleClientThread> clients;

	public ServerThread() throws IOException {
		serverSocket = new ServerSocket(9000);
		clients = new ArrayList<>();
	}

	@Override
	public void run() {
		while (!serverSocket.isClosed()) {
			try {
				System.out.println("Cekam klijenta...");
				Socket socket = serverSocket.accept();
				HandleClientThread client = new HandleClientThread(socket);
				client.start();
				clients.add(client);
				System.out.println("Klijent se povezao.");
			} catch (IOException ex) {
				try {
					serverSocket.close();
					System.out.println("Server je zaustavljen.");
				} catch (IOException ioex) {

				}
			}
		}
		stopAllThreads();
	}

	private void stopAllThreads() {
		for (HandleClientThread client : clients) {
			try {
				client.getSocket().close();
			} catch (IOException ex) {

			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public List<Magacioner> vratiPrijavljeneKorisnike() {
		List<Magacioner> magacioneri = new ArrayList<>();
		for (HandleClientThread client : clients) {
			if (client.isAlive() && !client.getSocket().isClosed()) {
				magacioneri.add(client.getMagacioner());
			}
		}
		return magacioneri;
	}

}

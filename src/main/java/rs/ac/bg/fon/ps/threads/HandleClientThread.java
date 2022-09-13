/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.threads;

import rs.ac.bg.fon.ps.communication.Operation;
import rs.ac.bg.fon.ps.communication.Receiver;
import rs.ac.bg.fon.ps.communication.Request;
import rs.ac.bg.fon.ps.communication.Response;
import rs.ac.bg.fon.ps.communication.ResponseType;
import rs.ac.bg.fon.ps.communication.Sender;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Magacioner;
import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author nikolinatomasevic
 */
public class HandleClientThread extends Thread {

	private Socket socket;
	private Magacioner magacioner;

	public HandleClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while (!socket.isClosed()) {
			try {
				Request request = (Request) new Receiver(socket).receive();
				Response response = handleClient(request);
				new Sender(socket).send(response);
			} catch (Exception ex) {
				try {
					socket.close();
				} catch (IOException ioex) {

				}
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	private Response handleClient(Request request) {
		int operacija = request.getOperacija();
		switch (operacija) {
		case Operation.LOGIN:
			return login(request);
		case Operation.PROMENI_MASINU_ILI_ALAT:
			return promeniMasinuIliAlat(request);
		case Operation.UCITAJ_LISTU_MASINA_ILI_ALATA:
			return ucitajListuMasinaIliAlata(request);
		case Operation.ZAPAMTI_MASINU_ILI_ALAT:
			return zapamtiMasinuIliAlat(request);
		case Operation.PROMENI_RADNIKA:
			return promeniRadnika(request);
		case Operation.UCITAJ_LISTU_RADNIKA:
			return ucitajListuRadnika(request);
		case Operation.ZAPAMTI_RADNIKA:
			return zapamtiRadnika(request);
		case Operation.UCITAJ_LISTU_RADNIH_MESTA:
			return ucitajListuRadnihMesta(request);
		case Operation.PROMENI_STAVKU_REVERSA:
			return promeniStavkuReversa(request);
		case Operation.UCITAJ_LISTU_REVERSA:
			return ucitajListuReversa(request);
		case Operation.ZAPAMTI_REVERS:
			return zapamtiRevers(request);
		case Operation.NADJI_MASINE_ILI_ALAT:
			return nadjiMasineIliAlat(request);
		case Operation.NADJI_RADNIKE:
			return nadjiRadnike(request);
		case Operation.NADJI_STAVKE_REVERSA:
			return nadjiStavkeReversa(request);
		}
		return null;
	}

	public Magacioner getMagacioner() {
		return magacioner;
	}

	private Response login(Request request) {
		Response response = new Response();
		Magacioner m = (Magacioner) request.getArgument();
		try {
			Magacioner magacioner = Controller.getInstance().login(m.getMejl(), m.getSifra());
			System.out.println("Uspesna prijava na sistem");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(magacioner);
			this.magacioner = magacioner;
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response ucitajListuMasinaIliAlata(Request request) {
		Response response = new Response();
		try {
			List<MasinaIliAlat> masineIliAlat = Controller.getInstance().ucitajListuMasinaIliAlata();
			System.out.println("Uspesno ucitana lista masina i alata.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(masineIliAlat);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response zapamtiMasinuIliAlat(Request request) {
		Response response = new Response();
		MasinaIliAlat masinaIliAlat = (MasinaIliAlat) request.getArgument();
		try {
			Controller.getInstance().zapamtiMasinuIliAlat(masinaIliAlat);
			System.out.println("Sistem je zapamtio masinu ili alat.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response zapamtiRadnika(Request request) {
		Response response = new Response();
		Radnik radnik = (Radnik) request.getArgument();
		try {
			Controller.getInstance().zapamtiRadnika(radnik);
			System.out.println("Sistem je zapamtio radnika.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response ucitajListuRadnihMesta(Request request) {
		Response response = new Response();
		try {
			List<RadnoMesto> radnaMesta = Controller.getInstance().ucitajListuRadnihMesta();
			System.out.println("Uspesno ucitana lista radnih mesta.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(radnaMesta);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response ucitajListuRadnika(Request request) {
		Response response = new Response();
		try {
			List<Radnik> radnici = Controller.getInstance().ucitajListuRadnika();
			System.out.println("Uspesno ucitana lista radnika.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(radnici);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response zapamtiRevers(Request request) {
		Response response = new Response();
		Revers revers = (Revers) request.getArgument();
		try {
			Controller.getInstance().zapamtiRevers(revers);
			System.out.println("Sistem je zapamtio revers.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response promeniRadnika(Request request) {
		Response response = new Response();
		Radnik radnik = (Radnik) request.getArgument();
		try {
			Controller.getInstance().promeniRadnika(radnik);
			System.out.println("Sistem je promenio radnika.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response promeniMasinuIliAlat(Request request) {
		Response response = new Response();
		MasinaIliAlat masinaIliAlat = (MasinaIliAlat) request.getArgument();
		try {
			Controller.getInstance().promeniMasinuIliAlat(masinaIliAlat);
			System.out.println("Sistem je promenio masinu ili alat.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response ucitajListuReversa(Request request) {
		Response response = new Response();
		try {
			List<Revers> reversi = Controller.getInstance().ucitajListuReversa();
			System.out.println("Uspesno ucitana lista reversa.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(reversi);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response promeniStavkuReversa(Request request) {
		Response response = new Response();
		StavkaReversa stavka = (StavkaReversa) request.getArgument();
		try {
			Controller.getInstance().promeniStavkuReversa(stavka);
			System.out.println("Sistem je promenio stavku.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(null);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response nadjiRadnike(Request request) {
		Response response = new Response();
		try {
			List<Radnik> trazeniRadnici = Controller.getInstance().nadjiRadnike((Radnik) request.getArgument());
			System.out.println("Sistem je nasao radnike po zadatoj vrednosti.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(trazeniRadnici);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response nadjiMasineIliAlat(Request request) {
		Response response = new Response();
		try {
			List<MasinaIliAlat> trazeneMasineIliAlat = Controller.getInstance()
					.nadjiMasineIliAlat((MasinaIliAlat) request.getArgument());
			System.out.println("Sistem je nasao masine ili alat po zadatoj vrednosti.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(trazeneMasineIliAlat);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

	private Response nadjiStavkeReversa(Request request) {
		Response response = new Response();
		try {
			List<StavkaReversa> stavke = Controller.getInstance().nadjiStavkeReversa((Revers) request.getArgument());
			System.out.println("Sistem je nasao reverse po zadatoj vrednosti.");
			response.setResponseTip(ResponseType.SUCCES);
			response.setRezultat(stavke);
		} catch (Exception ex) {
			response.setResponseTip(ResponseType.ERROR);
			response.setException(ex);
		}
		return response;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.radnik;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author ACER
 */
public class UcitajRadnikaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof Radnik)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		String message = "";
		Radnik radnik = (Radnik) param;
		if (radnik.getIme().isEmpty()) {
			message += "Polje 'ime' radnika ne sme biti prazno!\n";
		}
		if (radnik.getPrezime().isEmpty()) {
			message += "Polje 'prezime' radnika ne sme biti prazno!\n";
		}
		if (!radnik.getBrojTelefona().isEmpty()) {
			String brojTelefona = "";
			if (radnik.getBrojTelefona().startsWith("+")) {
				brojTelefona = radnik.getBrojTelefona();
			} else {
				brojTelefona = "+381" + radnik.getBrojTelefona().substring(1);
			}
			String apikey = "lwMAnEI9xBpMp5pyL3fjzGvoDw6TVfSnNWuEdEDn";

			URL url = new URL("https://api.numlookupapi.com/v1/validate/" + brojTelefona + "?apikey=" + apikey);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			Gson gson = new Gson();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			JsonObject rezultat = gson.fromJson(br, JsonObject.class);
			if (!rezultat.get("valid").getAsBoolean() || !rezultat.get("country_code").getAsString().equals("RS")) {
				message += "Polje 'broj telefona' radnika mora biti broj domace mreze!\n";
			}
		}
		if (radnik.getMejl().isEmpty()) {
			message += "Polje 'mejl' radnika ne sme biti prazno!\n";
		} else {
			if (!radnik.getMejl().endsWith("@ps.fon.bg.ac.rs")) {
				message += "Polje 'mejl' radnika mora biti u formatu nesto@ps.fon.bg.ac.rs!\n";
			}
		}
		if (radnik.getRadnoMesto() == null) {
			message += "Polje 'radno mesto' radnika ne sme biti prazno\n!";
		}
		if (!message.equals("")) {
			throw new Exception(message);
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		broker.promeni((Radnik) param);
	}

}

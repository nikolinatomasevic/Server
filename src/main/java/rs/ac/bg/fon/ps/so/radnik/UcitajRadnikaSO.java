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
 * Predstavlja sistemsku operaciju koja menja radnika u bazi podataka.
 *
 * @author nikolinatomasevic
 */
public class UcitajRadnikaSO extends AbstractSO {

	/**
	 * Proverava da li su ispunjeni svi preduslovi za izmenu radnika u bazi podataka.
	 * 
	 * <ul>
	 * <li>ulazni parametar ne sme biti null</li>
	 * <li>ulazni parametar mora biti instanca klase Radnik</li>
	 * <li>ime radnika ne sme biti prazan String</li>
	 * <li>prezime radnika ne sme biti prazan String</li>
	 * <li>broj telefona radnika mora biti broj domace mreze</li>
	 * <li>mejl radnika mora biti u formatu nesto@ps.fon.bg.ac.rs</li>
	 * <li>radno mesto radnika ne sme biti null</li>
	 * </ul>
	 * 
	 * @param param radnik kojeg je potrebno izmeniti
	 * @throws Exception u slucaju da bilo koji od preduslova nije ispunjen
	 */
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
		if (!radnik.getMejl().endsWith("@ps.fon.bg.ac.rs")) {
			message += "Polje 'mejl' radnika mora biti u formatu nesto@ps.fon.bg.ac.rs!\n";
		}
		if (radnik.getRadnoMesto() == null) {
			message += "Polje 'radno mesto' radnika ne sme biti prazno\n!";
		}
		if (!message.equals("")) {
			throw new Exception(message);
		}
	}

	/**
	 * Menja radnika koji je proslednjen kao ulazni parametar u bazi podataka.
	 * 
	 * @param param radnik kojeg je potrebno izmeniti
	 * @throws Exception u slucaju da dodje do greske prilikom izmene radnika
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		broker.promeni((Radnik) param);
	}

}

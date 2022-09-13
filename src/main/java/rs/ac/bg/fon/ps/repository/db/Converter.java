/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.ResultSet;
import rs.ac.bg.fon.ps.domain.Magacioner;
import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;

/**
 * Predstavlja konvertor koji rezultat iz baze podataka tipa ResultSet pretvara u konkretnu implementaciju interfejsa OpstiDomenskiObjekat.
 * 
 * @author nikolinatomasevic
 */
public class Converter {

	/**
	 * Konvertuje rezultat iz baze podataka u konkretnu implementaciju interfejsa OpstiDomenskiObjekat.
	 * 
	 * @param odo na osnovu cije konkretne implementacije se odredje tip objekta koji se vraca kao rezultat
	 * @param rs rezultat iz baze podataka koji je potrebno konvertovati
	 * @return objekat popunjen vrednostima koje su dobijene iz baze podataka
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja vredosti iz baze podataka
	 */
	public static OpstiDomenskiObjekat convert(OpstiDomenskiObjekat odo, ResultSet rs) throws Exception {

		if (odo instanceof Magacioner) {
			Magacioner magacioner = new Magacioner();
			magacioner.setRadnikID(rs.getLong("r.radnikID"));
			magacioner.setIme(rs.getString("r.ime"));
			magacioner.setPrezime(rs.getString("r.prezime"));
			magacioner.setBrojTelefona(rs.getString("r.brojTelefona"));
			magacioner.setMejl(rs.getString("r.mejl"));
			RadnoMesto radnoMesto = new RadnoMesto();
			radnoMesto.setSifraRM(rs.getLong("rm.sifraRM"));
			radnoMesto.setNazivRM(rs.getString("rm.nazivRM"));
			magacioner.setRadnoMesto(radnoMesto);
			magacioner.setSifra(rs.getString("m.sifra"));
			return magacioner;
		} else if (odo instanceof Radnik) {
			Radnik radnik = new Radnik();
			radnik.setRadnikID(rs.getLong("r.radnikID"));
			radnik.setIme(rs.getString("r.ime"));
			radnik.setPrezime(rs.getString("r.prezime"));
			radnik.setBrojTelefona(rs.getString("r.brojTelefona"));
			radnik.setMejl(rs.getString("r.mejl"));
			RadnoMesto radnoMesto = new RadnoMesto();
			radnoMesto.setSifraRM(rs.getLong("rm.sifraRM"));
			radnoMesto.setNazivRM(rs.getString("rm.nazivRM"));
			radnik.setRadnoMesto(radnoMesto);
			return radnik;
		} else if (odo instanceof RadnoMesto) {
			RadnoMesto radnoMesto = new RadnoMesto();
			radnoMesto.setSifraRM(rs.getLong("sifraRM"));
			radnoMesto.setNazivRM(rs.getString("nazivRM"));
			return radnoMesto;
		} else if (odo instanceof MasinaIliAlat) {
			MasinaIliAlat masinaIliAlat = new MasinaIliAlat();
			masinaIliAlat.setMasinaIliAlatID(rs.getLong("masinaIliAlatID"));
			masinaIliAlat.setNaziv(rs.getString("naziv"));
			masinaIliAlat.setKolicinaUMagacinu(rs.getInt("kolicinaUMagacinu"));
			masinaIliAlat.setKolicinaNaZaduzenju(rs.getInt("kolicinaNaZaduzenju"));
			return masinaIliAlat;
		} else if (odo instanceof Revers) {
			Revers revers = new Revers();
			revers.setBrojReversa(rs.getLong("r.brojReversa"));
			revers.setDatumIzdavanja(rs.getDate("r.datumIzdavanja"));
			Radnik radnik = new Radnik();
			radnik.setRadnikID(rs.getLong("rad.radnikID"));
			radnik.setIme(rs.getString("rad.ime"));
			radnik.setPrezime(rs.getString("rad.prezime"));
			radnik.setBrojTelefona(rs.getString("rad.brojTelefona"));
			radnik.setMejl(rs.getString("rad.mejl"));
			RadnoMesto radnoMesto = new RadnoMesto();
			radnoMesto.setSifraRM(rs.getLong("rm.sifraRM"));
			radnoMesto.setNazivRM(rs.getString("rm.nazivRM"));
			radnik.setRadnoMesto(radnoMesto);
			revers.setRadnik(radnik);
			return revers;
		} else if (odo instanceof StavkaReversa) {
			StavkaReversa stavkaReversa = new StavkaReversa();
			Revers revers = new Revers();
			revers.setBrojReversa(rs.getLong("r.brojReversa"));
			revers.setDatumIzdavanja(rs.getDate("r.datumIzdavanja"));
			Radnik radnik = new Radnik();
			radnik.setRadnikID(rs.getLong("rad.radnikID"));
			radnik.setIme(rs.getString("rad.ime"));
			radnik.setPrezime(rs.getString("rad.prezime"));
			radnik.setBrojTelefona(rs.getString("rad.brojTelefona"));
			radnik.setMejl(rs.getString("rad.mejl"));
			RadnoMesto radnoMesto = new RadnoMesto();
			radnoMesto.setSifraRM(rs.getLong("rm.sifraRM"));
			radnoMesto.setNazivRM(rs.getString("rm.nazivRM"));
			radnik.setRadnoMesto(radnoMesto);
			revers.setRadnik(radnik);
			stavkaReversa.setRevers(revers);
			stavkaReversa.setRb(rs.getInt("sr.rb"));
			stavkaReversa.setZaduzenaKolicina(rs.getInt("sr.zaduzenaKolicina"));
			stavkaReversa.setDatumRazduzenja(rs.getDate("sr.datumRazduzenja"));
			stavkaReversa.setRazduzenaKolicina(rs.getInt("sr.razduzenaKolicina"));
			MasinaIliAlat masinaIliAlat = new MasinaIliAlat();
			masinaIliAlat.setMasinaIliAlatID(rs.getLong("m.masinaIliAlatID"));
			masinaIliAlat.setNaziv(rs.getString("m.naziv"));
			masinaIliAlat.setKolicinaUMagacinu(rs.getInt("m.kolicinaUMagacinu"));
			masinaIliAlat.setKolicinaNaZaduzenju(rs.getInt("m.kolicinaNaZaduzenju"));
			stavkaReversa.setMasinaIliAlat(masinaIliAlat);
			return stavkaReversa;
		}
		return null;
	}

}

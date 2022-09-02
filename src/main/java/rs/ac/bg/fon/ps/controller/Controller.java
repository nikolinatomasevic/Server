/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import rs.ac.bg.fon.ps.domain.Magacioner;
import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.so.AbstractSO;
import rs.ac.bg.fon.ps.so.masinaIliAlat.NadjiMasineIliAlatSO;
import rs.ac.bg.fon.ps.so.masinaIliAlat.UcitajListuMasinaIliAlataSO;
import rs.ac.bg.fon.ps.so.masinaIliAlat.UcitajMasinuIliAlatSO;
import rs.ac.bg.fon.ps.so.masinaIliAlat.ZapamtiMasinuIliAlatSO;
import rs.ac.bg.fon.ps.so.radnik.NadjiRadnikeSO;
import rs.ac.bg.fon.ps.so.radnik.UcitajListuRadnikaSO;
import rs.ac.bg.fon.ps.so.radnik.UcitajRadnikaSO;
import rs.ac.bg.fon.ps.so.radnik.ZapamtiRadnikaSO;
import rs.ac.bg.fon.ps.so.radnoMesto.UcitajListuRadnihMestaSO;
import rs.ac.bg.fon.ps.so.revers.NadjiReverseSO;
import rs.ac.bg.fon.ps.so.revers.UcitajListuReversaSO;
import rs.ac.bg.fon.ps.so.revers.UcitajReversSO;
import rs.ac.bg.fon.ps.so.revers.ZapamtiReversSO;

/**
 *
 * @author ACER
 */
public class Controller {

	private static Controller instance;

	private Controller() {

	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public Magacioner login(String username, String password) throws Exception {
		List<Object> param = new ArrayList<>();
		param.add(new Magacioner());
		List<Magacioner> magacioneri = new ArrayList<>();
		param.add(magacioneri);
		AbstractSO nadjiRadnikeSO = new NadjiRadnikeSO();
		nadjiRadnikeSO.execute(param);
		for (Magacioner magacioner : magacioneri) {
			if (magacioner.getMejl().equals(username) && magacioner.getSifra().equals(password)) {
				return magacioner;
			}
		}
		throw new Exception("Neispravni korisnicko ime i/ili lozinka. Pokusajte ponovo.");
	}

	public List<RadnoMesto> ucitajListuRadnihMesta() throws Exception {
		List<RadnoMesto> radnaMesta = new ArrayList<>();
		AbstractSO ucitajListuRadnihMestaSO = new UcitajListuRadnihMestaSO();
		ucitajListuRadnihMestaSO.execute(radnaMesta);
		return radnaMesta;
	}

	public void zapamtiRadnika(Radnik radnik) throws Exception {
		AbstractSO zapamtiRadnikaSO = new ZapamtiRadnikaSO();
		zapamtiRadnikaSO.execute(radnik);
	}

	public List<Radnik> ucitajListuRadnika() throws Exception {
		List<Radnik> radnici = new ArrayList<>();
		AbstractSO nadjiRadnikeSO = new UcitajListuRadnikaSO();
		nadjiRadnikeSO.execute(radnici);
		return radnici;
	}

	public void zapamtiMasinuIliAlat(MasinaIliAlat masinaIliAlat) throws Exception {
		AbstractSO zapamtiMasinuIliAlatSO = new ZapamtiMasinuIliAlatSO();
		zapamtiMasinuIliAlatSO.execute(masinaIliAlat);
	}

	public List<MasinaIliAlat> ucitajListuMasinaIliAlata() throws Exception {
		List<MasinaIliAlat> masineIliAlat = new ArrayList<>();
		AbstractSO nadjiMasineIliAlatSO = new UcitajListuMasinaIliAlataSO();
		nadjiMasineIliAlatSO.execute(masineIliAlat);
		return masineIliAlat;
	}

	public void zapamtiRevers(Revers revers) throws Exception {
		AbstractSO zapamtiReversSO = new ZapamtiReversSO();
		zapamtiReversSO.execute(revers);
	}

	public void promeniRadnika(Radnik radnik) throws Exception {
		AbstractSO ucitajRadnikaSO = new UcitajRadnikaSO();
		ucitajRadnikaSO.execute(radnik);
	}

	public void promeniMasinuIliAlat(MasinaIliAlat masinaIliAlat) throws Exception {
		AbstractSO ucitajMasinuIliAlatSO = new UcitajMasinuIliAlatSO();
		ucitajMasinuIliAlatSO.execute(masinaIliAlat);
	}

	public List<Revers> ucitajListuReversa() throws Exception {
		List<Revers> reversi = new ArrayList<>();
		AbstractSO nadjiReverseSO = new UcitajListuReversaSO();
		nadjiReverseSO.execute(reversi);
		return reversi;
	}

	public void promeniStavkuReversa(StavkaReversa stavka) throws Exception {
		AbstractSO ucitajReversSO = new UcitajReversSO();
		ucitajReversSO.execute(stavka);
	}

	public List<Radnik> nadjiRadnike(Radnik r) throws Exception {
		List<Object> param = new ArrayList<>();
		param.add(r);
		List<Radnik> trazeniRadnici = new ArrayList<>();
		param.add(trazeniRadnici);
		AbstractSO nadjiRadnikeSO = new NadjiRadnikeSO();
		nadjiRadnikeSO.execute(param);
		return trazeniRadnici;
	}

	public List<MasinaIliAlat> nadjiMasineIliAlat(MasinaIliAlat m) throws Exception {
		List<Object> param = new ArrayList<>();
		param.add(m);
		List<MasinaIliAlat> trazeneMasineIliAlat = new ArrayList<>();
		param.add(trazeneMasineIliAlat);
		AbstractSO nadjiMasineIliAlatSO = new NadjiMasineIliAlatSO();
		nadjiMasineIliAlatSO.execute(param);
		return trazeneMasineIliAlat;
	}

	public List<StavkaReversa> nadjiStavkeReversa(Revers r) throws Exception {
		List<Object> param = new ArrayList<>();
		param.add(r);
		List<StavkaReversa> trazeneStavke = new ArrayList<>();
		param.add(trazeneStavke);
		AbstractSO nadjiReverseSO = new NadjiReverseSO();
		nadjiReverseSO.execute(param);
		return trazeneStavke;
	}

}

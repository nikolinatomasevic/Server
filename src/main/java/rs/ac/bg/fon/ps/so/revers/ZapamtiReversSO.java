/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.revers;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author ACER
 */
public class ZapamtiReversSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof Revers)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		String message = "";
		Revers revers = (Revers) param;
		if (revers.getDatumIzdavanja() == null) {
			message += "Polje 'datum izdavanja' reversa ne sme biti prazno!\n";
		}
		if (revers.getRadnik() == null) {
			message += "Polje 'radnik' reversa ne sme biti prazno!\n";
		}
		if (revers.getStavke().isEmpty()) {
			message += "Revers mora sadrzati barem jednu stavku!\n";
		}
		if (!message.equals("")) {
			throw new Exception(message);
		}

		for (StavkaReversa stavka : revers.getStavke()) {
			if (stavka.getRevers() == null) {
				message += "Polje 'revers' stavke reversa ne sme biti prazno!\n";
			}
			if (stavka.getRb() == null) {
				message += "Polje 'rb' stavke reversa ne sme biti prazno!\n";
			}
			if (stavka.getZaduzenaKolicina() == null) {
				message += "Polje 'zaduzena kolicina' (stavka br." + stavka.getRb() + ") ne sme biti prazno!\n";
			} else {
				if (stavka.getZaduzenaKolicina() <= 0) {
					message += "Polje 'zaduzena kolicina' (stavka br." + stavka.getRb()
							+ ") mora sadrzati vrednost koja je >0!\n";
				}
				int kolicinaUMagacinu = stavka.getMasinaIliAlat().getKolicinaUMagacinu();
				if (stavka.getZaduzenaKolicina() > kolicinaUMagacinu) {
					message += "Nedovoljna kolicina u magacinu za stavku br. " + stavka.getRb() + "!\n";
				}
			}
			if (stavka.getMasinaIliAlat() == null) {
				message += "Polje 'masina ili alat' (stavka br." + stavka.getRb() + ") ne sme biti prazno!\n";
			}

		}
		if (!message.equals("")) {
			throw new Exception(message);
		}

		for (StavkaReversa stavka : revers.getStavke()) {
			MasinaIliAlat masinaIliAlat = stavka.getMasinaIliAlat();
			int broj = 0;
			for (StavkaReversa s : revers.getStavke()) {
				if (s.getMasinaIliAlat().equals(masinaIliAlat)) {
					broj++;
				}
			}
			if (broj > 1) {
				throw new Exception("Dve ili vise stavki se ne mogu odnositi na istu masinu ili alat!\n");
			}
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		((Revers) param).setBrojReversa(broker.nadjiID(new Revers()));
		broker.zapamti((Revers) param);
		for (StavkaReversa stavka : ((Revers) param).getStavke()) {
			broker.zapamti(stavka);
			MasinaIliAlat masinaIliAlat = new MasinaIliAlat();
			masinaIliAlat.setMasinaIliAlatID(stavka.getMasinaIliAlat().getMasinaIliAlatID());
			masinaIliAlat.setNaziv(stavka.getMasinaIliAlat().getNaziv());
			int kolicinaUMagacinu = stavka.getMasinaIliAlat().getKolicinaUMagacinu();
			masinaIliAlat.setKolicinaUMagacinu(kolicinaUMagacinu - stavka.getZaduzenaKolicina());
			int kolicinaNaZaduzenju = stavka.getMasinaIliAlat().getKolicinaNaZaduzenju();
			masinaIliAlat.setKolicinaNaZaduzenju(kolicinaNaZaduzenju + stavka.getZaduzenaKolicina());
			broker.promeni(masinaIliAlat);
		}
	}

}

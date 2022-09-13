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
 * Predstavlja sistemsku operaciju koja pamti (cuva) novi revers sa svim stavkama u bazi podataka.
 *
 * @author ACER
 */
public class ZapamtiReversSO extends AbstractSO {

	/**
	 * Proverava da li su ispunjeni svi preduslovi za cuvanje novog reversa i njegovih stavki u bazi podataka.
	 * 
	 * @param param novi revers koji je potrebno sacuvati
	 * @throws Exception u slucaju da bilo koji od preduslova nije ispunjen
	 */
	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof Revers)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		String message = "";
		Revers revers = (Revers) param;
		if (!message.equals("")) {
			throw new Exception(message);
		}
		for (StavkaReversa stavka : revers.getStavke()) {
			int kolicinaUMagacinu = stavka.getMasinaIliAlat().getKolicinaUMagacinu();
			if (stavka.getZaduzenaKolicina() > kolicinaUMagacinu) {
				message += "Nedovoljna kolicina u magacinu za stavku br. " + stavka.getRb() + "!\n";
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

	/**
	 * Pamti (cuva) novi revers sa svim stavkama u bazi podataka.
	 * 
	 * @param param novi revers koji je potrebno sacuvati
	 * @throws Exception u slucaju da dodje do greske prilikom cuvanja novog reversa i njegovih stavki
	 */
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.revers;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.repository.db.BrokerDB;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author ACER
 */
public class UcitajReversSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof StavkaReversa)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		StavkaReversa stavka = (StavkaReversa) param;
		if (stavka.getZaduzenaKolicina() < stavka.getRazduzenaKolicina()) {
			throw new Exception("Ne mozete razduziti vise od prvobitne zaduzene kolicine!\n");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		int vecRazduzio = 0;
		vecRazduzio = ((BrokerDB) broker).vratiRazduzenuKolicinu((StavkaReversa) param);
		broker.promeni((StavkaReversa) param);
		MasinaIliAlat masinaIliAlat = new MasinaIliAlat();
		masinaIliAlat.setMasinaIliAlatID(((StavkaReversa) param).getMasinaIliAlat().getMasinaIliAlatID());
		masinaIliAlat.setNaziv(((StavkaReversa) param).getMasinaIliAlat().getNaziv());
		int kolicinaUMagacinu = ((StavkaReversa) param).getMasinaIliAlat().getKolicinaUMagacinu();
		masinaIliAlat
				.setKolicinaUMagacinu(kolicinaUMagacinu + ((StavkaReversa) param).getRazduzenaKolicina() - vecRazduzio);
		int kolicinaNaZaduzenju = ((StavkaReversa) param).getMasinaIliAlat().getKolicinaNaZaduzenju();
		masinaIliAlat.setKolicinaNaZaduzenju(
				kolicinaNaZaduzenju - ((StavkaReversa) param).getRazduzenaKolicina() + vecRazduzio);
		broker.promeni(masinaIliAlat);
	}

}

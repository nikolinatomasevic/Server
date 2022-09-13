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
 * Predstavlja sistemsku operaciju koja menja stavku reversa u bazi podataka.
 *
 * @author nikolinatomasevic
 */
public class UcitajReversSO extends AbstractSO {

	/**
	 * Proverava da li su ispunjeni svi preduslovi za izmenu stavke reversa u bazi podataka.
	 *
	 * @param param stavka reversa koju je potrebno izmeniti
	 * @throws Exception u slucaju da bilo koji od preduslova nije ispunjen
	 */
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

	/**
	 * Menja stavku reversa koja je proslednjena kao ulazni parametar u bazi podataka.
	 * 
	 * @param param stavka reversa koju je potrebno izmeniti
	 * @throws Exception u slucaju da dodje do greske prilikom izmene stavke reversa
	 */
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

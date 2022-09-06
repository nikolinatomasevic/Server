/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.revers;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava stavke reversa iz baze podataka koje ispunjavaju odredjene kriterijume pretrage.
 *
 * @author ACER
 */
public class NadjiReverseSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava stavke reversa iz baze podataka koji ispunjavaju odredjene kriterijume pretrage.
	 * 
	 * @param param lista objekata tipa Object koja sadrzi dva elementa.
	 * Prvi element predstavlja objekat tipa Revers koji sadrzi kriterijume pretrage,
	 * a drugi element predstavlja listu objekata tipa StavkaReversa u koju se ucitava lista iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja stavki reversa iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		Revers r = (Revers) ((List<Object>) param).get(0);
		List<StavkaReversa> trazeneStavke = (List<StavkaReversa>) ((List<Object>) param).get(1);
		List<Revers> reversi = (List<Revers>) broker.ucitajListu(new Revers());
		for (Revers revers : reversi) {
			StavkaReversa s = new StavkaReversa();
			s.setRevers(revers);
			revers.getStavke().addAll((List<StavkaReversa>) broker.ucitajListu(s));
		}
		for (Revers revers : reversi) {
			if (!revers.getRadnik().getIme().startsWith(r.getRadnik().getIme())) {
				continue;
			}
			if (!revers.getRadnik().getPrezime().startsWith(r.getRadnik().getPrezime())) {
				continue;
			}
			for (StavkaReversa stavka : revers.getStavke()) {
				if (!stavka.getMasinaIliAlat().getNaziv().toLowerCase()
						.contains(r.getStavke().get(0).getMasinaIliAlat().getNaziv().toLowerCase())) {
					continue;
				}
				trazeneStavke.add(stavka);
			}
		}
		if (trazeneStavke.isEmpty()) {
			throw new Exception("Sistem ne moze da nadje reverse po zadatoj vrednosti!");
		}
	}

}

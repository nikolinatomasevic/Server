/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.radnik;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Magacioner;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava radnike iz baze podataka koji ispunjavaju odredjene kriterijume pretrage.
 *
 * @author nikolinatomasevic
 */
public class NadjiRadnikeSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava radnike iz baze podataka koji ispunjavaju odredjene kriterijume pretrage.
	 * 
	 * @param param lista objekata tipa Object koja sadrzi dva elementa.
	 * Prvi element predstavlja objekat tipa Radnik koji sadrzi kriterijume pretrage,
	 * a drugi element predstavlja listu objekata tipa Radnik u koju se ucitava lista iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja radnika iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		if (((List<Object>) param).get(0) instanceof Magacioner) {
			Magacioner m = (Magacioner) ((List<Object>) param).get(0);
			List<Magacioner> trazeniRadnici = (List<Magacioner>) ((List<Object>) param).get(1);
			trazeniRadnici.addAll((List<Magacioner>) broker.ucitajListu(m));
			if (trazeniRadnici.isEmpty()) {
				throw new Exception("Sistem ne moze da nadje radnike po zadatoj vrednosti!");
			}
		} else if (((List<Object>) param).get(0) instanceof Radnik) {
			Radnik r = (Radnik) ((List<Object>) param).get(0);
			List<Radnik> trazeniRadnici = (List<Radnik>) ((List<Object>) param).get(1);
			List<Radnik> radnici = (List<Radnik>) broker.ucitajListu(new Radnik());
			for (Radnik radnik : radnici) {
				if (!radnik.getIme().startsWith(r.getIme())) {
					continue;
				}
				if (!radnik.getPrezime().startsWith(r.getPrezime())) {
					continue;
				}
				if (r.getRadnoMesto() != null) {
					if (!radnik.getRadnoMesto().equals(r.getRadnoMesto())) {
						continue;
					}
				}
				trazeniRadnici.add(radnik);
			}
			if (trazeniRadnici.isEmpty()) {
				throw new Exception("Sistem ne moze da nadje radnike po zadatoj vrednosti!");
			}
		}
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.masinaIliAlat;

import java.util.List;
import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava masine i alat iz baze podataka koji ispunjavaju odredjene kriterijume pretrage.
 *
 * @author nikolinatomasevic
 */
public class NadjiMasineIliAlatSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava masine i alat iz baze podataka koji ispunjavaju odredjene kriterijume pretrage.
	 * 
	 * @param param lista objekata tipa Object koja sadrzi dva elementa.
	 * Prvi element predstavlja objekat tipa MasinaIliAlat koji sadrzi kriterijume pretrage,
	 * a drugi element predstavlja listu objekata tipa MasinaIliAlat u koju se ucitava lista iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja masina i alata iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		MasinaIliAlat m = (MasinaIliAlat) ((List<Object>) param).get(0);
		List<MasinaIliAlat> trazeneMasineIliAlat = (List<MasinaIliAlat>) ((List<Object>) param).get(1);
		List<MasinaIliAlat> masineIliAlat = (List<MasinaIliAlat>) broker.ucitajListu(new MasinaIliAlat());
		for (MasinaIliAlat masinaIliAlat : masineIliAlat) {
			if (masinaIliAlat.getNaziv().toLowerCase().contains(m.getNaziv().toLowerCase())) {
				trazeneMasineIliAlat.add(masinaIliAlat);
			}
		}
		if (trazeneMasineIliAlat.isEmpty()) {
			throw new Exception("Sistem ne moze da nadje masine ili alat po zadatoj vrednosti!");
		}
	}

}

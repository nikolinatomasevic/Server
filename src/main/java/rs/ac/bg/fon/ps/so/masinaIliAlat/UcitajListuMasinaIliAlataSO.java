/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.masinaIliAlat;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import java.util.List;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava sve masine i alat iz baze podataka.
 *
 * @author nikolinatomasevic
 */
public class UcitajListuMasinaIliAlataSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava sve masine i alat iz baze podataka.
	 * 
	 * @param param lista objekata tipa MasinaIliAlat u koju se ucitava lista masina i alata iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja masina i alata iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		((List<MasinaIliAlat>) param).addAll((List<MasinaIliAlat>) broker.ucitajListu(new MasinaIliAlat()));
	}

}

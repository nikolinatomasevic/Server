/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.radnik;

import rs.ac.bg.fon.ps.domain.Radnik;
import java.util.List;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava sve radnike iz baze podataka.
 *
 * @author nikolinatomasevic
 */
public class UcitajListuRadnikaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava sve radnike iz baze podataka.
	 * 
	 * @param param lista objekata tipa Radnik u koju se ucitava lista radnika iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja radnika iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		((List<Radnik>) param).addAll((List<Radnik>) broker.ucitajListu(new Radnik()));
	}

}

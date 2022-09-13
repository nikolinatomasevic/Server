/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.revers;

import rs.ac.bg.fon.ps.domain.Revers;
import java.util.List;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava sve reverse sa njihovim stavkama iz baze podataka.
 *
 * @author nikolinatomasevic
 */
public class UcitajListuReversaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava sve reverse sa njihovim stavkama iz baze podataka.
	 * 
	 * @param param lista objekata tipa Revers u koju se ucitava lista reversa iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja reversa iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		List<Revers> reversi = (List<Revers>) broker.ucitajListu(new Revers());
		for (Revers revers : reversi) {
			StavkaReversa s = new StavkaReversa();
			s.setRevers(revers);
			revers.getStavke().addAll((List<StavkaReversa>) broker.ucitajListu(s));
		}
		((List<Revers>) param).addAll(reversi);
	}

}

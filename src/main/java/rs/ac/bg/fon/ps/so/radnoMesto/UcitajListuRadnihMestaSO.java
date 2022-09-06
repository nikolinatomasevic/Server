/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.radnoMesto;

import rs.ac.bg.fon.ps.domain.RadnoMesto;
import java.util.List;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja ucitava sva radna mesta iz baze podataka.
 *
 * @author ACER
 */
public class UcitajListuRadnihMestaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	/**
	 * Ucitava sva radna mesta iz baze podataka.
	 * 
	 * @param param lista objekata tipa RadnoMesto u koju se ucitava lista radnih mesta iz baze
	 * @throws Exception u slucaju da dodje do greske prilikom ucitavanja radnih mesta iz baze podataka
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		((List<RadnoMesto>) param).addAll((List<RadnoMesto>) broker.ucitajListu(new RadnoMesto()));
	}

}

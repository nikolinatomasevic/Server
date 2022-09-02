/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.radnik;

import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author ACER
 */
public class ZapamtiRadnikaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof Radnik)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		String message = "";
		Radnik radnik = (Radnik) param;
		if (radnik.getIme().isEmpty()) {
			message += "Polje 'ime' radnika ne sme biti prazno!\n";
		}
		if (radnik.getPrezime().isEmpty()) {
			message += "Polje 'prezime' radnika ne sme biti prazno!\n";
		}
		if (radnik.getMejl().isEmpty()) {
			message += "Polje 'mejl' radnika ne sme biti prazno!\n";
		} else {
			if (!radnik.getMejl().endsWith("@ps.fon.bg.ac.rs")) {
				message += "Polje 'mejl' radnika mora biti u formatu nesto@ps.fon.bg.ac.rs!\n";
			}
		}
		if (radnik.getRadnoMesto() == null) {
			message += "Polje 'radno mesto' radnika ne sme biti prazno\n!";
		}
		if (!message.equals("")) {
			throw new Exception(message);
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		((Radnik) param).setRadnikID(broker.nadjiID(new Radnik()));
		broker.zapamti((Radnik) param);
	}

}

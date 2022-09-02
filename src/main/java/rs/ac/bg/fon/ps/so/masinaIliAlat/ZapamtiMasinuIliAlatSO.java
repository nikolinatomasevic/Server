/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.masinaIliAlat;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 *
 * @author ACER
 */
public class ZapamtiMasinuIliAlatSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {
		if (param == null || !(param instanceof MasinaIliAlat)) {
			throw new Exception("Neodgovarajuci parametar!");
		}
		String message = "";
		MasinaIliAlat masinaIliAlat = (MasinaIliAlat) param;
		if (masinaIliAlat.getNaziv().isEmpty()) {
			message += "Polje 'naziv' masine ili alata ne sme biti prazno!\n";
		}
		if (masinaIliAlat.getKolicinaUMagacinu() < 0) {
			message += "Polje 'kolicina u magacinu' masine ili alata mora sadrzati vrednost koja je >=0!\n";
		}
		if (masinaIliAlat.getKolicinaNaZaduzenju() < 0) {
			message += "Polje 'kolicina na zaduzenju' masine ili alata mora sadrzati vrednost koja je >=0!\n";
		}
		if (!message.equals("")) {
			throw new Exception(message);
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		((MasinaIliAlat) param).setMasinaIliAlatID(broker.nadjiID(new MasinaIliAlat()));
		broker.zapamti((MasinaIliAlat) param);
	}

}

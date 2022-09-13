/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so.masinaIliAlat;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.so.AbstractSO;

/**
 * Predstavlja sistemsku operaciju koja menja masinu ili alat u bazi podataka.
 *
 * @author ACER
 */
public class UcitajMasinuIliAlatSO extends AbstractSO {

	/**
	 * Proverava da li su ispunjeni svi preduslovi za izmenu masine ili alata u bazi podataka.
	 *
	 * <ul>
	 * <li>ulazni parametar ne sme biti null</li>
	 * <li>ulazni parametar mora biti instanca klase MasinaIliAlat</li>
	 * <li>naziv masine ili alata ne sme biti prazan String</li>
	 * </ul>
	 * 
	 * @param param masina ili alat koji je potrebno izmeniti
	 * @throws Exception u slucaju da bilo koji od preduslova nije ispunjen
	 */
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
		if (!message.equals("")) {
			throw new Exception(message);
		}
	}

	/**
	 * Menja masinu ili alat koji je proslednjen kao ulazni parametar u bazi podataka.
	 * 
	 * @param param masina ili alat koji je potrebno izmeniti
	 * @throws Exception u slucaju da dodje do greske prilikom izmene masine ili alata
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		broker.promeni((MasinaIliAlat) param);
	}

}

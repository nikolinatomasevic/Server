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
 *
 * @author ACER
 */
public class NadjiMasineIliAlatSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

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

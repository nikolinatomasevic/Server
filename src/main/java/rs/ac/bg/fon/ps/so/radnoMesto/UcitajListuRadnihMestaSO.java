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
 *
 * @author ACER
 */
public class UcitajListuRadnihMestaSO extends AbstractSO {

	@Override
	protected void precondition(Object param) throws Exception {

	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		((List<RadnoMesto>) param).addAll((List<RadnoMesto>) broker.ucitajListu(new RadnoMesto()));
	}

}

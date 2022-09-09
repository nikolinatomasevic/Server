/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.BrokerDB;

/**
 * Predstavlja sistemsku operaciju.
 *
 * @author ACER
 */
public abstract class AbstractSO {

	/**
	 * Broker koji upravlja perzistencijom
	 */
	protected final Repository broker;

	/**
	 * Inicijalizuje brokera koji radi sa bazom podataka.
	 */
	public AbstractSO() {
		this.broker = new BrokerDB();
	}

	/**
	 * Izvrsava sistemsku operaciju.
	 * 
	 * @param param objekat nad kojim treba da se izvrsi sistemska operacija
	 * @throws Exception u slucaju da dodje do greske prilikom izvrsavanja sistemske operacije
	 */
	public void execute(Object param) throws Exception {
		try {
			precondition(param);
			startTransaction();
			executeOperation(param);
			commitTransaction();
			System.out.println("Uspesno izvrsena operacija.");
		} catch (Exception ex) {
			System.out.println("Neuspesno izvrsena operacija!");
			rollbackTransaction();
			throw ex;
		} finally {
			disconnect();
		}
	}

	/**
	 * Proverava da li su ispunjeni svi preduslovi za objekat koji je prosledjen kao ulazni parametar.
	 * 
	 * @param param objekat za koji se proveravaju preduslovi
	 * @throws Exception u slucaju da nisu ispunjeni svi preduslovi
	 */
	protected abstract void precondition(Object param) throws Exception;

	/**
	 * Zapocinje transakciju tako sto uspostavlja konekciju sa bazom podataka.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom uspostavljanja konekcije sa bazom podataka
	 */
	private void startTransaction() throws Exception {
		((BrokerDB) broker).connect();
	}

	/**
	 * Izvrsava operaciju nad objektom koji je prosledjen kao ulazni parametar.
	 * 
	 * @param param objekat nad kojim treba da se izvrsi operacija
	 * @throws Exception u slucaju da dodje do greske prilikom izvrsavanja operacije
	 */
	protected abstract void executeOperation(Object param) throws Exception;

	/**
	 * Vrsi commit transakcije.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom commit-a transakcije
	 */
	private void commitTransaction() throws Exception {
		((BrokerDB) broker).commit();
	}

	/**
	 * Vrsi rollback transakcije u slucaju da je doslo do greske prilikom izvrsavanja operacije.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom rollback-a transakcije
	 */
	private void rollbackTransaction() throws Exception {
		((BrokerDB) broker).rollback();
	}

	/**
	 * Prekida konekciju sa bazom podataka.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom prekidanja konekcije sa bazom podataka
	 */
	private void disconnect() throws Exception {
		((BrokerDB) broker).disconnect();
	}

}

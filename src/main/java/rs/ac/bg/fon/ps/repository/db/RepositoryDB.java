/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import rs.ac.bg.fon.ps.repository.Repository;

/**
 * Predstavlja genericki interfejs koji ima osnovne operacije koje omogucavaju
 * uspostavljanje i prekidanje konekcije sa bazom podataka, kao i commit i rollback transakcije.
 *
 * @see Repository
 * @author ACER
 */
public interface RepositoryDB<T> extends Repository<T> {

	/**
	 * Uspostavlja konekciju sa bazom podataka.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom uspostavljanja konekcije sa bazom podataka
	 */
	default public void connect() throws Exception {
		DBConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Prekida konekciju sa bazom podataka.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom prekidanja konekcije sa bazom podataka
	 */
	default public void disconnect() throws Exception {
		DBConnectionFactory.getInstance().getConnection().close();
	}

	/**
	 * Vrsi commit transakcije.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom commit-a transakcije
	 */
	default public void commit() throws Exception {
		DBConnectionFactory.getInstance().getConnection().commit();
	}

	/**
	 * Vrsi rollback transakcije.
	 * 
	 * @throws Exception u slucaju da dodje do greske prilikom rollback-a transakcije
	 */
	default public void rollback() throws Exception {
		DBConnectionFactory.getInstance().getConnection().rollback();
	}

}

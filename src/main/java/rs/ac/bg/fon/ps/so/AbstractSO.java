/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.BrokerDB;

/**
 *
 * @author ACER
 */
public abstract class AbstractSO {

	protected final Repository broker;

	public AbstractSO() {
		this.broker = new BrokerDB();
	}

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

	protected abstract void precondition(Object param) throws Exception;

	private void startTransaction() throws Exception {
		((BrokerDB) broker).connect();
	}

	protected abstract void executeOperation(Object param) throws Exception;

	private void commitTransaction() throws Exception {
		((BrokerDB) broker).commit();
	}

	private void rollbackTransaction() throws Exception {
		((BrokerDB) broker).rollback();
	}

	private void disconnect() throws Exception {
		((BrokerDB) broker).disconnect();
	}

}

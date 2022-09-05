/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Predstavlja klasu za uspostavljanje konekcije sa bazom podataka.
 * 
 * @author ACER
 */
public class DBConnectionFactory {

	/**
	 * Konekcija sa bazom podataka
	 */
	private Connection connection;
	/**
	 * Staticka instanca same klase
	 */
	private static DBConnectionFactory instance;

	/**
	 * Inicijalizuje novi objekat klase.
	 */
	private DBConnectionFactory() {

	}

	/**
	 * Vraca instancu klase.
	 * U slucaju da instanca nije vec kreirana tj. da ima vrednost null, kreira novu instancu klase.
	 * 
	 * @return instanca klase tipa DBConnectionFactory
	 */
	public static DBConnectionFactory getInstance() {
		if (instance == null) {
			instance = new DBConnectionFactory();
		}
		return instance;
	}

	/**
	 * Uspostavlja i vraca konekciju sa bazom podataka.
	 * 
	 * @return konekcija sa bazom podataka tipa Connection
	 * @throws Exception u slucaju da dodje do greske prilikom uspostavljanja konekcije sa bazom
	 */
	public Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			try {
				Properties properties = new Properties();
				properties.load(new FileInputStream("config/dbconfig.properties"));
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			} catch (Exception ex) {
				System.out.println("Neuspesno uspostavljanje konekcije: " + ex.getMessage());
				throw ex;
			}
		}
		return connection;
	}

}

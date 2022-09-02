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
 *
 * @author ACER
 */
public class DBConnectionFactory {

	private Connection connection;
	private static DBConnectionFactory instance;

	private DBConnectionFactory() {

	}

	public static DBConnectionFactory getInstance() {
		if (instance == null) {
			instance = new DBConnectionFactory();
		}
		return instance;
	}

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

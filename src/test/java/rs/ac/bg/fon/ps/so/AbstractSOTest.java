package rs.ac.bg.fon.ps.so;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.BrokerDB;
import rs.ac.bg.fon.ps.repository.db.DBConnectionFactory;

public abstract class AbstractSOTest {

	protected Repository broker;
	protected Connection connection;
	protected String upit;
	protected Statement statement;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("config/dbconfig.properties"));
			properties.setProperty("url", "jdbc:mysql://localhost:3306/projekattest?useSSL=false");
			properties.store(new FileOutputStream("config/dbconfig.properties"), null);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@BeforeEach
	protected void setUp() throws Exception {
		broker = new BrokerDB();
		((BrokerDB) broker).connect();
		connection = DBConnectionFactory.getInstance().getConnection();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		((BrokerDB) broker).disconnect();
		broker = null;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("config/dbconfig.properties"));
			properties.setProperty("url", "jdbc:mysql://localhost:3306/projekat?useSSL=false");
			properties.store(new FileOutputStream("config/dbconfig.properties"), null);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

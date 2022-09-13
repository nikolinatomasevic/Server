package rs.ac.bg.fon.ps.so.radnik;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.repository.db.Converter;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajRadnikaSOTest extends AbstractSOTest {

	UcitajRadnikaSO so;
	Radnik radnik;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajRadnikaSO();
		radnik = new Radnik();

		upit = "DELETE FROM Radnik";
		statement = connection.createStatement();
		statement.executeUpdate(upit);
		statement.close();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();

		so = null;
		radnik = null;
	}

	@Test
	void testPreconditionNull() {
		assertThrows(java.lang.Exception.class, () -> so.precondition(null));
	}

	@Test
	void testPreconditionInstancaDrugeKlase() {
		assertThrows(java.lang.Exception.class, () -> so.precondition(new MasinaIliAlat()));
	}

	@Test
	void testPreconditionImePrazanString() {
		RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
		radnik.setIme("");
		radnik.setPrezime("Markovic");
		radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
		radnik.setRadnoMesto(radnoMesto);
		assertThrows(java.lang.Exception.class, () -> so.precondition(radnik));
	}

	@Test
	void testPreconditionPrezimePrazanString() {
		RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
		radnik.setIme("Marko");
		radnik.setPrezime("");
		radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
		radnik.setRadnoMesto(radnoMesto);
		assertThrows(java.lang.Exception.class, () -> so.precondition(radnik));
	}

	@Test
	void testPreconditionMejlPogresanFormat() {
		RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
		radnik.setIme("Marko");
		radnik.setPrezime("Markovic");
		radnik.setMejl("marko.markovic@gmail.com");
		radnik.setRadnoMesto(radnoMesto);
		assertThrows(java.lang.Exception.class, () -> so.precondition(radnik));
	}

	@Test
	void testPreconditionRadnoMestoNull() {
		radnik.setIme("Marko");
		radnik.setPrezime("Markovic");
		radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
		assertThrows(java.lang.Exception.class, () -> so.precondition(radnik));
	}

	@Test
	void testExecuteOperationPromeniIme() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnik.setIme("Petar");

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Petar", ((Radnik) lista.get(0)).getIme());
			assertEquals("Markovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0627756551", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("marko.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniPrezime() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnik.setPrezime("Petrovic");

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Marko", ((Radnik) lista.get(0)).getIme());
			assertEquals("Petrovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0627756551", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("marko.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniBrojTelefona() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnik.setBrojTelefona("0613855161");

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Marko", ((Radnik) lista.get(0)).getIme());
			assertEquals("Markovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0613855161", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("marko.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniMejl() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnik.setMejl("m.markovic@ps.fon.bg.ac.rs");

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Marko", ((Radnik) lista.get(0)).getIme());
			assertEquals("Markovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0627756551", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("m.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniRadnoMesto() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnoMesto = new RadnoMesto(6l, "Stolar");
			radnik.setRadnoMesto(radnoMesto);

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Marko", ((Radnik) lista.get(0)).getIme());
			assertEquals("Markovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0627756551", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("marko.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniDvaAtributa() {
		try {
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756551");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			radnoMesto = new RadnoMesto(6l, "Stolar");
			radnik.setRadnoMesto(radnoMesto);
			radnik.setMejl("m.markovic@ps.fon.bg.ac.rs");

			so.executeOperation(radnik);

			OpstiDomenskiObjekat odo = new Radnik();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT r.radnikID, r.ime, r.prezime, r.brojTelefona, r.mejl, r.radnoMesto,"
					+ "rm.sifraRM, rm.nazivRM FROM Radnik r inner join RadnoMesto rm on r.radnoMesto = rm.sifraRM";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((Radnik) lista.get(0)).getRadnikID());
			assertEquals("Marko", ((Radnik) lista.get(0)).getIme());
			assertEquals("Markovic", ((Radnik) lista.get(0)).getPrezime());
			assertEquals("0627756551", ((Radnik) lista.get(0)).getBrojTelefona());
			assertEquals("m.markovic@ps.fon.bg.ac.rs", ((Radnik) lista.get(0)).getMejl());
			assertEquals(radnoMesto, ((Radnik) lista.get(0)).getRadnoMesto());

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
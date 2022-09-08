package rs.ac.bg.fon.ps.so.radnik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajListuRadnikaSOTest extends AbstractSOTest {

	UcitajListuRadnikaSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajListuRadnikaSO();

		upit = "DELETE FROM Radnik";
		statement = connection.createStatement();
		statement.executeUpdate(upit);
		statement.close();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();

		so = null;
	}

	@Test
	void testExecuteOperationJedanRadnik() {
		try {
			Radnik radnik = new Radnik();
			radnik.setRadnikID(1l);
			radnik.setIme("Marko");
			radnik.setPrezime("Markovic");
			radnik.setBrojTelefona("0627756256");
			radnik.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik);

			List<Radnik> radnici = new ArrayList<>();

			so.executeOperation(radnici);

			assertEquals(1, radnici.size());
			assertEquals(radnik, radnici.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationViseRadnika() {
		try {
			Radnik radnik1 = new Radnik();
			radnik1.setRadnikID(1l);
			radnik1.setIme("Marko");
			radnik1.setPrezime("Markovic");
			radnik1.setBrojTelefona("0627756256");
			radnik1.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			RadnoMesto radnoMesto1 = new RadnoMesto(2l, "Sef gradilista");
			radnik1.setRadnoMesto(radnoMesto1);

			Radnik radnik2 = new Radnik();
			radnik2.setRadnikID(2l);
			radnik2.setIme("Petar");
			radnik2.setPrezime("Petrovic");
			radnik2.setMejl("petar.petrovic@ps.fon.bg.ac.rs");
			RadnoMesto radnoMesto2 = new RadnoMesto(5l, "Rukovalac gradjevinskim masinama");
			radnik2.setRadnoMesto(radnoMesto2);

			broker.zapamti(radnik1);
			broker.zapamti(radnik2);

			List<Radnik> radnici = new ArrayList<>();

			so.executeOperation(radnici);

			assertEquals(2, radnici.size());
			assertTrue(radnici.contains(radnik1));
			assertTrue(radnici.contains(radnik2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

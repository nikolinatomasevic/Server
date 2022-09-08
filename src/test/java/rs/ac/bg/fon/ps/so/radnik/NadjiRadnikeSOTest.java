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

class NadjiRadnikeSOTest extends AbstractSOTest {

	NadjiRadnikeSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new NadjiRadnikeSO();

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
	void testExecuteOperationJedanRezultat() {
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
			radnik2.setIme("Marko");
			radnik2.setPrezime("Petrovic");
			radnik2.setMejl("marko.petrovic@ps.fon.bg.ac.rs");
			RadnoMesto radnoMesto2 = new RadnoMesto(5l, "Rukovalac gradjevinskim masinama");
			radnik2.setRadnoMesto(radnoMesto2);

			broker.zapamti(radnik1);
			broker.zapamti(radnik2);

			Radnik r = new Radnik();
			r.setIme("Marko");
			r.setPrezime("Markovic");
			List<Object> param = new ArrayList<>();
			param.add(r);
			List<Radnik> trazeniRadnici = new ArrayList<>();
			param.add(trazeniRadnici);

			so.execute(param);

			assertEquals(1, trazeniRadnici.size());
			assertTrue(trazeniRadnici.contains(radnik1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationViseRezultata() {
		try {
			Radnik radnik1 = new Radnik();
			radnik1.setRadnikID(1l);
			radnik1.setIme("Marko");
			radnik1.setPrezime("Markovic");
			radnik1.setBrojTelefona("0627756256");
			radnik1.setMejl("marko.markovic@ps.fon.bg.ac.rs");
			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			radnik1.setRadnoMesto(radnoMesto);

			Radnik radnik2 = new Radnik();
			radnik2.setRadnikID(2l);
			radnik2.setIme("Marko");
			radnik2.setPrezime("Petrovic");
			radnik2.setMejl("marko.petrovic@ps.fon.bg.ac.rs");
			radnik2.setRadnoMesto(radnoMesto);

			broker.zapamti(radnik1);
			broker.zapamti(radnik2);

			Radnik r = new Radnik();
			r.setIme("");
			r.setPrezime("");
			r.setRadnoMesto(radnoMesto);
			List<Object> param = new ArrayList<>();
			param.add(r);
			List<Radnik> trazeniRadnici = new ArrayList<>();
			param.add(trazeniRadnici);

			so.execute(param);

			assertEquals(2, trazeniRadnici.size());
			assertTrue(trazeniRadnici.contains(radnik1));
			assertTrue(trazeniRadnici.contains(radnik2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

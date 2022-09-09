package rs.ac.bg.fon.ps.so.revers;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.repository.db.Converter;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajReversSOTest extends AbstractSOTest {

	UcitajReversSO so;
	StavkaReversa stavkaReversa;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajReversSO();
		stavkaReversa = new StavkaReversa();

		upit = "DELETE FROM Revers";
		statement = connection.createStatement();
		statement.executeUpdate(upit);

		upit = "DELETE FROM Radnik";
		statement = connection.createStatement();
		statement.executeUpdate(upit);

		upit = "DELETE FROM MasinaIliAlat";
		statement = connection.createStatement();
		statement.executeUpdate(upit);

		statement.close();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();

		so = null;
		stavkaReversa = null;
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
	void testPrecondition() {
		Revers revers = new Revers();
		MasinaIliAlat masinaIliAlat = new MasinaIliAlat(1l, "Brusilica", 23, 8);
		stavkaReversa.setRevers(revers);
		stavkaReversa.setRb(1);
		stavkaReversa.setZaduzenaKolicina(8);
		stavkaReversa.setRazduzenaKolicina(12);
		stavkaReversa.setMasinaIliAlat(masinaIliAlat);
		assertThrows(java.lang.Exception.class, () -> so.precondition(stavkaReversa));
	}

	@Test
	void testExecuteOperation() {
		try {
			Revers revers = new Revers();
			revers.setBrojReversa(1l);
			Date datumIzdavanja = new SimpleDateFormat("dd.MM.yyyy.").parse("8.8.2022.");
			revers.setDatumIzdavanja(datumIzdavanja);

			RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
			Radnik radnik = new Radnik(1l, "Marko", "Markovic", "0637792469", "marko.markovic@ps.fon.bg.ac.rs",
					radnoMesto);
			revers.setRadnik(radnik);
			broker.zapamti(radnik);

			MasinaIliAlat masinaIliAlat = new MasinaIliAlat(1l, "Brusilica", 23, 8);
			stavkaReversa.setRevers(revers);
			stavkaReversa.setRb(1);
			stavkaReversa.setZaduzenaKolicina(8);
			stavkaReversa.setRazduzenaKolicina(0);
			stavkaReversa.setMasinaIliAlat(masinaIliAlat);
			List<StavkaReversa> stavke = new LinkedList<StavkaReversa>();
			stavke.add(stavkaReversa);
			revers.setStavke(stavke);
			broker.zapamti(masinaIliAlat);
			
			broker.zapamti(revers);
			broker.zapamti(stavkaReversa);

			stavkaReversa.setRazduzenaKolicina(4);

			so.executeOperation(stavkaReversa);

			List<Revers> reversi = new ArrayList<Revers>();
			reversi = broker.ucitajListu(new Revers());
			for (Revers r : reversi) {
				StavkaReversa s = new StavkaReversa();
				s.setRevers(r);
				r.getStavke().addAll((List<StavkaReversa>) broker.ucitajListu(s));
			}

			assertEquals(4, reversi.get(0).getStavke().get(0).getRazduzenaKolicina());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

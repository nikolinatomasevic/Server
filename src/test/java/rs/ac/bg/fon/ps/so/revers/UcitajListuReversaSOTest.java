package rs.ac.bg.fon.ps.so.revers;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.domain.Radnik;
import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.domain.Revers;
import rs.ac.bg.fon.ps.domain.StavkaReversa;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajListuReversaSOTest extends AbstractSOTest {

	UcitajListuReversaSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajListuReversaSO();

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

			MasinaIliAlat masinaIliAlat1 = new MasinaIliAlat(1l, "Brusilica", 23, 8);
			StavkaReversa stavkaReversa1 = new StavkaReversa(revers, 1, 8, null, 0, masinaIliAlat1);
			MasinaIliAlat masinaIliAlat2 = new MasinaIliAlat(2l, "Agregat", 1, 1);
			StavkaReversa stavkaReversa2 = new StavkaReversa(revers, 2, 1, null, 0, masinaIliAlat2);
			List<StavkaReversa> stavke = new LinkedList<StavkaReversa>();
			stavke.add(stavkaReversa1);
			stavke.add(stavkaReversa2);
			revers.setStavke(stavke);
			broker.zapamti(masinaIliAlat1);
			broker.zapamti(masinaIliAlat2);

			broker.zapamti(revers);
			broker.zapamti(stavkaReversa1);
			broker.zapamti(stavkaReversa2);

			List<Revers> reversi = new ArrayList<>();

			so.executeOperation(reversi);

			assertEquals(1, reversi.size());
			assertEquals(revers, reversi.get(0));
			assertTrue(reversi.get(0).getStavke().contains(stavkaReversa1));
			assertTrue(reversi.get(0).getStavke().contains(stavkaReversa2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

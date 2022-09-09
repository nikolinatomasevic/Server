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

class NadjiReverseSOTest extends AbstractSOTest {

	NadjiReverseSO so;
	Revers revers1;
	StavkaReversa stavkaReversa1;
	StavkaReversa stavkaReversa2;
	Revers revers2;
	StavkaReversa stavkaReversa3;
	StavkaReversa stavkaReversa4;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new NadjiReverseSO();

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

		revers1 = new Revers();
		revers1.setBrojReversa(1l);
		Date datumIzdavanja1 = new SimpleDateFormat("dd.MM.yyyy.").parse("8.8.2022.");
		revers1.setDatumIzdavanja(datumIzdavanja1);

		RadnoMesto radnoMesto = new RadnoMesto(2l, "Sef gradilista");
		Radnik radnik1 = new Radnik(1l, "Marko", "Markovic", "0637792469", "marko.markovic@ps.fon.bg.ac.rs",
				radnoMesto);
		revers1.setRadnik(radnik1);
		broker.zapamti(radnik1);

		MasinaIliAlat masinaIliAlat1 = new MasinaIliAlat(1l, "Brusilica", 23, 8);
		stavkaReversa1 = new StavkaReversa(revers1, 1, 8, null, 0, masinaIliAlat1);
		MasinaIliAlat masinaIliAlat2 = new MasinaIliAlat(2l, "Agregat", 1, 2);
		stavkaReversa2 = new StavkaReversa(revers1, 2, 1, null, 0, masinaIliAlat2);
		List<StavkaReversa> stavke1 = new LinkedList<StavkaReversa>();
		stavke1.add(stavkaReversa1);
		stavke1.add(stavkaReversa2);
		revers1.setStavke(stavke1);
		broker.zapamti(masinaIliAlat1);
		broker.zapamti(masinaIliAlat2);
		
		broker.zapamti(revers1);
		broker.zapamti(stavkaReversa1);
		broker.zapamti(stavkaReversa2);

		revers2 = new Revers();
		revers2.setBrojReversa(2l);
		Date datumIzdavanja2 = new SimpleDateFormat("dd.MM.yyyy.").parse("9.9.2022.");
		revers2.setDatumIzdavanja(datumIzdavanja2);

		Radnik radnik2 = new Radnik(2l, "Petar", "Markovic", "0628892469", "petar.markovic@ps.fon.bg.ac.rs",
				radnoMesto);
		revers2.setRadnik(radnik2);
		broker.zapamti(radnik2);

		MasinaIliAlat masinaIliAlat3 = new MasinaIliAlat(3l, "Busilica", 20, 5);
		stavkaReversa3 = new StavkaReversa(revers2, 1, 5, null, 0, masinaIliAlat3);
		stavkaReversa4 = new StavkaReversa(revers2, 2, 1, null, 0, masinaIliAlat2);
		List<StavkaReversa> stavke2 = new LinkedList<StavkaReversa>();
		stavke2.add(stavkaReversa3);
		stavke2.add(stavkaReversa4);
		revers2.setStavke(stavke2);
		broker.zapamti(masinaIliAlat3);
		
		broker.zapamti(revers2);
		broker.zapamti(stavkaReversa3);
		broker.zapamti(stavkaReversa4);
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();

		so = null;
	}

	@Test
	void testExecuteOperationJedanRezultat() {
		try {
			Revers r = new Revers();

			Radnik rad = new Radnik();
			rad.setIme("Petar");
			rad.setPrezime("");
			r.setRadnik(rad);

			MasinaIliAlat m = new MasinaIliAlat();
			m.setNaziv("Agregat");
			List<StavkaReversa> sreversa = new ArrayList<>();
			StavkaReversa sr = new StavkaReversa();
			sr.setMasinaIliAlat(m);
			sreversa.add(sr);
			r.setStavke(sreversa);

			List<Object> param = new ArrayList<>();
			param.add(r);
			List<StavkaReversa> trazeneStavkeReversa = new ArrayList<>();
			param.add(trazeneStavkeReversa);

			so.executeOperation(param);

			assertEquals(1, trazeneStavkeReversa.size());
			assertTrue(trazeneStavkeReversa.contains(stavkaReversa4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationViseRezultata() {
		try {
			Revers r = new Revers();

			Radnik rad = new Radnik();
			rad.setIme("");
			rad.setPrezime("Markovic");
			r.setRadnik(rad);

			MasinaIliAlat m = new MasinaIliAlat();
			m.setNaziv("Agregat");
			List<StavkaReversa> sreversa = new ArrayList<>();
			StavkaReversa sr = new StavkaReversa();
			sr.setMasinaIliAlat(m);
			sreversa.add(sr);
			r.setStavke(sreversa);

			List<Object> param = new ArrayList<>();
			param.add(r);
			List<StavkaReversa> trazeneStavkeReversa = new ArrayList<>();
			param.add(trazeneStavkeReversa);

			so.executeOperation(param);

			assertEquals(2, trazeneStavkeReversa.size());
			assertTrue(trazeneStavkeReversa.contains(stavkaReversa2));
			assertTrue(trazeneStavkeReversa.contains(stavkaReversa4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

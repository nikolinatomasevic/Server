package rs.ac.bg.fon.ps.so.masinaIliAlat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class NadjiMasineIliAlatSOTest extends AbstractSOTest {

	NadjiMasineIliAlatSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new NadjiMasineIliAlatSO();

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
	void testExecuteOperationJedanRezultat() {
		try {
			MasinaIliAlat masinaIliAlat1 = new MasinaIliAlat();
			masinaIliAlat1.setMasinaIliAlatID(1l);
			masinaIliAlat1.setNaziv("Busilica za beton");
			masinaIliAlat1.setKolicinaUMagacinu(17);
			masinaIliAlat1.setKolicinaNaZaduzenju(9);

			MasinaIliAlat masinaIliAlat2 = new MasinaIliAlat();
			masinaIliAlat2.setMasinaIliAlatID(2l);
			masinaIliAlat2.setNaziv("Busilica");
			masinaIliAlat2.setKolicinaUMagacinu(1);
			masinaIliAlat2.setKolicinaNaZaduzenju(0);

			broker.zapamti(masinaIliAlat1);
			broker.zapamti(masinaIliAlat2);

			MasinaIliAlat m = new MasinaIliAlat();
			m.setNaziv("beton");
			List<Object> param = new ArrayList<>();
			param.add(m);
			List<MasinaIliAlat> trazeneMasineIliAlat = new ArrayList<>();
			param.add(trazeneMasineIliAlat);

			so.execute(param);

			assertEquals(1, trazeneMasineIliAlat.size());
			assertTrue(trazeneMasineIliAlat.contains(masinaIliAlat1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationViseRezultata() {
		try {
			MasinaIliAlat masinaIliAlat1 = new MasinaIliAlat();
			masinaIliAlat1.setMasinaIliAlatID(1l);
			masinaIliAlat1.setNaziv("Busilica za beton");
			masinaIliAlat1.setKolicinaUMagacinu(17);
			masinaIliAlat1.setKolicinaNaZaduzenju(9);

			MasinaIliAlat masinaIliAlat2 = new MasinaIliAlat();
			masinaIliAlat2.setMasinaIliAlatID(2l);
			masinaIliAlat2.setNaziv("Busilica");
			masinaIliAlat2.setKolicinaUMagacinu(1);
			masinaIliAlat2.setKolicinaNaZaduzenju(0);

			broker.zapamti(masinaIliAlat1);
			broker.zapamti(masinaIliAlat2);

			MasinaIliAlat m = new MasinaIliAlat();
			m.setNaziv("Busilica");
			List<Object> param = new ArrayList<>();
			param.add(m);
			List<MasinaIliAlat> trazeneMasineIliAlat = new ArrayList<>();
			param.add(trazeneMasineIliAlat);

			so.execute(param);

			assertEquals(2, trazeneMasineIliAlat.size());
			assertTrue(trazeneMasineIliAlat.contains(masinaIliAlat1));
			assertTrue(trazeneMasineIliAlat.contains(masinaIliAlat2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

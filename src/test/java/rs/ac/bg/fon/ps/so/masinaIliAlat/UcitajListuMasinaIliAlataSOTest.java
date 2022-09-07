package rs.ac.bg.fon.ps.so.masinaIliAlat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.MasinaIliAlat;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajListuMasinaIliAlataSOTest extends AbstractSOTest {

	UcitajListuMasinaIliAlataSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajListuMasinaIliAlataSO();

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
	void testExecuteOperationJednaMasinaIliAlat() {
		try {
			MasinaIliAlat masinaIliAlat = new MasinaIliAlat();
			masinaIliAlat.setMasinaIliAlatID(1l);
			masinaIliAlat.setNaziv("Brusilica");
			masinaIliAlat.setKolicinaUMagacinu(17);
			masinaIliAlat.setKolicinaNaZaduzenju(9);

			broker.zapamti(masinaIliAlat);

			List<MasinaIliAlat> masineIliAlat = new ArrayList<>();

			so.executeOperation(masineIliAlat);

			assertEquals(1, masineIliAlat.size());
			assertEquals(masinaIliAlat, masineIliAlat.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationViseMasinaIliAlata() {
		try {
			MasinaIliAlat masinaIliAlat1 = new MasinaIliAlat();
			masinaIliAlat1.setMasinaIliAlatID(1l);
			masinaIliAlat1.setNaziv("Brusilica");
			masinaIliAlat1.setKolicinaUMagacinu(17);
			masinaIliAlat1.setKolicinaNaZaduzenju(9);

			MasinaIliAlat masinaIliAlat2 = new MasinaIliAlat();
			masinaIliAlat2.setMasinaIliAlatID(2l);
			masinaIliAlat2.setNaziv("Agregat");
			masinaIliAlat2.setKolicinaUMagacinu(1);
			masinaIliAlat2.setKolicinaNaZaduzenju(1);

			broker.zapamti(masinaIliAlat1);
			broker.zapamti(masinaIliAlat2);

			List<MasinaIliAlat> masineIliAlat = new ArrayList<>();

			so.executeOperation(masineIliAlat);

			assertEquals(2, masineIliAlat.size());
			assertTrue(masineIliAlat.contains(masinaIliAlat1));
			assertTrue(masineIliAlat.contains(masinaIliAlat2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

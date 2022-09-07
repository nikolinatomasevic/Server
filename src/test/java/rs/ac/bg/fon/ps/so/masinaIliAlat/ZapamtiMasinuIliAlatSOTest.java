package rs.ac.bg.fon.ps.so.masinaIliAlat;

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
import rs.ac.bg.fon.ps.repository.db.Converter;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class ZapamtiMasinuIliAlatSOTest extends AbstractSOTest {

	ZapamtiMasinuIliAlatSO so;
	MasinaIliAlat masinaIliAlat;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new ZapamtiMasinuIliAlatSO();
		masinaIliAlat = new MasinaIliAlat();

		upit = "DELETE FROM MasinaIliAlat";
		statement = connection.createStatement();
		statement.executeUpdate(upit);
		statement.close();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();

		so = null;
		masinaIliAlat = null;
	}

	@Test
	void testPreconditionNull() {
		assertThrows(java.lang.Exception.class, () -> so.precondition(null));
	}

	@Test
	void testPreconditionInstancaDrugeKlase() {
		assertThrows(java.lang.Exception.class, () -> so.precondition(new Radnik()));
	}

	@Test
	void testPreconditionNazivPrazanString() {
		masinaIliAlat.setNaziv("");
		assertThrows(java.lang.Exception.class, () -> so.precondition(masinaIliAlat));
	}

	@Test
	void testPreconditionKolicinaUMagacinuManjaOdNule() {
		masinaIliAlat.setNaziv("Brusilica");
		masinaIliAlat.setKolicinaUMagacinu(-17);
		assertThrows(java.lang.Exception.class, () -> so.precondition(masinaIliAlat));
	}

	@Test
	void testPreconditionKolicinaNaZaduzenjuManjaOdNule() {
		masinaIliAlat.setNaziv("Brusilica");
		masinaIliAlat.setKolicinaNaZaduzenju(-9);
		assertThrows(java.lang.Exception.class, () -> so.precondition(masinaIliAlat));
	}

	@Test
	void testExecuteOperation() {
		try {
			masinaIliAlat.setNaziv("Brusilica");
			masinaIliAlat.setKolicinaUMagacinu(17);
			masinaIliAlat.setKolicinaNaZaduzenju(9);

			so.executeOperation(masinaIliAlat);
			
			OpstiDomenskiObjekat odo = new MasinaIliAlat();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT * FROM MasinaIliAlat";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1, lista.size());
			assertEquals(masinaIliAlat, lista.get(0));

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

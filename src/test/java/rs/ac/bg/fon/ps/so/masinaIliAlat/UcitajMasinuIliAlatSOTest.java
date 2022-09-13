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

class UcitajMasinuIliAlatSOTest extends AbstractSOTest {

	UcitajMasinuIliAlatSO so;
	MasinaIliAlat masinaIliAlat;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();

		so = new UcitajMasinuIliAlatSO();
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
	void testExecuteOperationPromeniNaziv() {
		try {
			masinaIliAlat.setMasinaIliAlatID(1l);
			masinaIliAlat.setNaziv("Brusilica");
			masinaIliAlat.setKolicinaUMagacinu(17);
			masinaIliAlat.setKolicinaNaZaduzenju(9);

			broker.zapamti(masinaIliAlat);

			masinaIliAlat.setNaziv("Brusilica 2");

			so.executeOperation(masinaIliAlat);

			OpstiDomenskiObjekat odo = new MasinaIliAlat();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT * FROM MasinaIliAlat";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((MasinaIliAlat) lista.get(0)).getMasinaIliAlatID());
			assertEquals("Brusilica 2", ((MasinaIliAlat) lista.get(0)).getNaziv());
			assertEquals(17, ((MasinaIliAlat) lista.get(0)).getKolicinaUMagacinu());
			assertEquals(9, ((MasinaIliAlat) lista.get(0)).getKolicinaNaZaduzenju());
			
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExecuteOperationPromeniKolicinuUMagacinu() {
		try {
			masinaIliAlat.setMasinaIliAlatID(1l);
			masinaIliAlat.setNaziv("Brusilica");
			masinaIliAlat.setKolicinaUMagacinu(17);
			masinaIliAlat.setKolicinaNaZaduzenju(9);

			broker.zapamti(masinaIliAlat);

			masinaIliAlat.setKolicinaUMagacinu(20);

			so.executeOperation(masinaIliAlat);

			OpstiDomenskiObjekat odo = new MasinaIliAlat();
			List<OpstiDomenskiObjekat> lista = new ArrayList<>();

			upit = "SELECT * FROM MasinaIliAlat";
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(upit);
			while (rs.next()) {
				lista.add(Converter.convert(odo, rs));
			}

			assertEquals(1l, ((MasinaIliAlat) lista.get(0)).getMasinaIliAlatID());
			assertEquals("Brusilica", ((MasinaIliAlat) lista.get(0)).getNaziv());
			assertEquals(20, ((MasinaIliAlat) lista.get(0)).getKolicinaUMagacinu());
			assertEquals(9, ((MasinaIliAlat) lista.get(0)).getKolicinaNaZaduzenju());
			
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

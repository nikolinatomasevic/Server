package rs.ac.bg.fon.ps.so.radnoMesto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ps.domain.RadnoMesto;
import rs.ac.bg.fon.ps.so.AbstractSOTest;

class UcitajListuRadnihMestaSOTest extends AbstractSOTest {
	
	UcitajListuRadnihMestaSO so;

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		
		so = new UcitajListuRadnihMestaSO();
	}

	@AfterEach
	protected void tearDown() throws Exception {
		super.tearDown();
		
		so = null;
	}

	@Test
	void testExecuteOperation() {
		try {
			List<RadnoMesto> radnaMesta = new ArrayList<>();

			so.executeOperation(radnaMesta);

			assertEquals(6, radnaMesta.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

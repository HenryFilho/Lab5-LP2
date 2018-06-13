package lab5.system;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	Controller control;
	@Before
	public void buildController() {
		control = new Controller(100000, 0.01);
	}

	@Test
	public void testAddScenario() {
		assertEquals(control.addScenario("Vou ficar com preguiça de fazer teste de entrada vazia."), 1);
	}

	@Test
	public void testToStringScenario() {
		control.addScenario("Hoje eu consigo me matar.");
		control.addScenario("É HOJE");
		control.addScenario("AGORA VAI AGORA VAI!!!!111!!!1111!1");
		assertEquals(control.toStringScenario(), "1 - Hoje eu consigo me matar. - Não finalizado\n2 - É HOJE - Não finalizado\n3 - AGORA VAI AGORA VAI!!!!111!!!1111!1 - Não finalizado");
	}

	@Test
	public void testToStringScenario2() {
		control.addScenario("Hoje eu consigo me matar.");
		assertEquals(control.toStringScenario(1), "1 - Hoje eu consigo me matar. - Não finalizado");
	}

	@Test
	public void testGetCashier() {
		assertEquals(control.getCashier(), 100000);
	}

	@Test
	public void testGetCashierInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReward() {
		fail("Not yet implemented");
	}

	@Test
	public void testTotalBets() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringBets() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalizeBet() {
		fail("Not yet implemented");
	}

}

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
		fail("Not yet implemented");
	}

	@Test
	public void testToStringScenario2() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCashier() {
		fail("Not yet implemented");
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

package lab5.system;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	Controller control;
	
	@Before
	public void createController() {
		control = new Controller(100000, 0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testControllerFail() {
		control = new Controller(-1, 0.01);
	}
	
	public void testControllerFail2() {
		control = new Controller(100000, -1);
	}

	@Test
	public void testAddScenario() {
		int temp = control.addScenario("Example");
		assertEquals(temp,1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddScenarioNull() {
		control.addScenario(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddScenarioEmpty() {
		control.addScenario("");
	}
	
	@Test
	public void testAddScenarioBonus() {
		int temp = control.addScenario("Example",1000);
		assertEquals(temp,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddScenarioBonusInvalid() {
		control.addScenario("Example",0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddBetInvalidScenario() {
		control.addScenario("Example");
		control.addBet(0, "Satan Morningstar", 200, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBetOutofRangeScenario() {
		control.addScenario("Example");
		control.addBet(2, "Satan Morningstar", 200, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBetEmptyBetter() {
		control.addScenario("Example");
		control.addBet(1, "", 200, "VAI ACONTECER");
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddBetNullBetter() {
		control.addScenario("Example");
		control.addBet(1, null, 200, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBetInvalidValue() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 0, "VAI ACONTECER");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBetEmptyPrediction() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "");
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddBetNullPrediction() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBetInvalidPrediction() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "qwerty");
	}

	@Test
	public void testToStringScenario() {
		control.addScenario("Example one");
		control.addScenario("Example two");
		control.addScenario("Example three");
		assertEquals(control.toStringScenario(), "1 - Example one - Nao finalizado\n"
											   + "2 - Example two - Nao finalizado\n"
											   + "3 - Example three - Nao finalizado");
	}

	@Test
	public void testToStringScenarioInt() {
		control.addScenario("Example");
		String temp = control.toStringScenario(1);
		assertEquals(temp, "1 - Example - Nao finalizado");
	}
	
	@Test
	public void testToStringScenarioIntBonus() {
		control.addScenario("Example",1000);
		String temp = control.toStringScenario(1);
		assertEquals(temp, "1 - Example - Nao finalizado - R$ 10,00");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testToStringScenarioIntInvalid() {
		control.addScenario("Example");
		control.toStringScenario(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testToStringScenarioIntOutofRange() {
		control.addScenario("Example");
		control.toStringScenario(2);
	}

	@Test
	public void testGetCashier() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.addBet(1, "Lucifer Lightbringer", 200, "VAI ACONTECER");
		control.addBet(1, "Mammon", 100, "N VAI ACONTECER");
		control.addBet(1, "Asmodeus", 590, "N VAI ACONTECER");
		control.finalizeBet(1, true);
		int temp = control.getCashier(1);
		assertEquals(temp,6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCashierInvalid() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(1, true);
		control.getCashier(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCashierOutofRange() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(1, true);
		control.getCashier(2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCashierOpenScenario() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.getCashier(1);
	}

	@Test
	public void testGetReward() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.addBet(1, "Lucifer Lightbringer", 200, "VAI ACONTECER");
		control.addBet(1, "Mammon", 100, "N VAI ACONTECER");
		control.addBet(1, "Asmodeus", 590, "N VAI ACONTECER");
		control.finalizeBet(1, true);
		int temp = control.getReward(1);
		assertEquals(temp,684);
	}
	
	@Test
	public void testGetRewardBonus() {
		control.addScenario("Example",100);
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.addBet(1, "Lucifer Lightbringer", 200, "VAI ACONTECER");
		control.addBet(1, "Mammon", 100, "N VAI ACONTECER");
		control.addBet(1, "Asmodeus", 590, "N VAI ACONTECER");
		control.finalizeBet(1, true);
		int temp = control.getReward(1);
		assertEquals(temp,784);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetRewardInvalid() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(1, true);
		control.getReward(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetRewardOutofRange() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(1, true);
		control.getReward(2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetRewardOpenScenario() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.getReward(1);
	}
	
	@Test
	public void testTotalBets() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.addBet(1, "Lucifer Lightbringer", 200, "VAI ACONTECER");
		control.addBet(1, "Mammon", 100, "N VAI ACONTECER");
		control.addBet(1, "Asmodeus", 590, "N VAI ACONTECER");
		int temp = control.totalBets(1);
		assertEquals(temp,4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotalBetsInvalid() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.totalBets(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotalBetsOutofRange() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.totalBets(2);
	}

	@Test
	public void testTotalBetsValue() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.addBet(1, "Lucifer Lightbringer", 200, "VAI ACONTECER");
		control.addBet(1, "Mammon", 100, "N VAI ACONTECER");
		control.addBet(1, "Asmodeus", 590, "N VAI ACONTECER");
		int temp = control.totalBetsValue(1);
		assertEquals(temp,1090);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotalBetsValueInvalid() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.totalBetsValue(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTotalBetsValueOutofRange() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.totalBetsValue(2);
	}

	@Test
	public void testToStringBets() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		String temp = control.toStringBets(1);
		assertEquals(temp,"Satan Morningstar - R$2,00 - VAI ACONTECER");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFinalizeBetInvalid() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(0, true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFinalizeBetOutofRange() {
		control.addScenario("Example");
		control.addBet(1, "Satan Morningstar", 200, "VAI ACONTECER");
		control.finalizeBet(1, true);
		control.finalizeBet(1, true);
	}

}

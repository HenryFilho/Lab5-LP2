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

	@Test(expected = IllegalArgumentException.class)
	public void testAddBetFinalized() {
		control.addScenario("Vai dar erro");
		control.finalizeBet(1, true);
		control.addBet(1, "Fulaninho", 20000, "N VAI ACONTECER");
	}

	@Test
	public void testToStringScenario() {
		control.addScenario("Hoje eu consigo me matar.");
		control.addScenario("É HOJE");
		control.addScenario("AGORA VAI AGORA VAI!!!!111!!!1111!1");
		assertEquals(control.toStringScenario(),
				"1 - Hoje eu consigo me matar. - Nao finalizado\n2 - É HOJE - Nao finalizado\n3 - AGORA VAI AGORA VAI!!!!111!!!1111!1 - Nao finalizado");
	}

	@Test
	public void testToStringScenario2() {
		control.addScenario("Hoje eu consigo me matar.");
		assertEquals(control.toStringScenario(1), "1 - Hoje eu consigo me matar. - Nao finalizado");
	}

	@Test
	public void testGetCashier() {
		assertEquals(control.getCashier(), 100000);
	}

	@Test
	public void testGetCashierInt() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		control.addBet(1, "Anonimo", 199, "N VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.addBet(1, "Livia Maria", 30000, "VAI ACONTECER");
		control.addBet(1, "Raquel Lopes", 20000, "VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.finalizeBet(1, true);

		assertEquals(control.getCashier(1), 201);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCashierNotFinalized() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		control.addBet(1, "Anonimo", 199, "N VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.addBet(1, "Livia Maria", 30000, "VAI ACONTECER");
		control.addBet(1, "Raquel Lopes", 20000, "VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");

		control.getCashier(1);
	}

	@Test
	public void testGetReward() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		control.addBet(1, "Anonimo", 199, "N VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.addBet(1, "Livia Maria", 30000, "VAI ACONTECER");
		control.addBet(1, "Raquel Lopes", 20000, "VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.finalizeBet(1, true);

		assertEquals(control.getReward(1), 19998);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetRewardNotFinalized() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		control.addBet(1, "Anonimo", 199, "N VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
		control.addBet(1, "Livia Maria", 30000, "VAI ACONTECER");
		control.addBet(1, "Raquel Lopes", 20000, "VAI ACONTECER");
		control.addBet(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");

		control.getReward(1);
	}

	@Test
	public void testTotalBets() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		assertEquals(control.totalBets(1), 1);
	}

	@Test
	public void testTotalBetsValue() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");

		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		assertEquals(control.totalBetsValue(1), 20000);
	}

	@Test
	public void testToStringBets() {
		control.addScenario("A maioria irá tirar mais do que 7 na prova!");
		control.addBet(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
		assertEquals(control.toStringBets(1), "Francisco Cisco - R$200,00 - N VAI ACONTECER");
	}

}

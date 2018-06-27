package lab5.system;

import java.util.HashSet;
import java.util.Set;

public class Scenario {

	protected int num;
	protected String desc;
	protected String status;
	protected Set<Bet> bets;

	/**
	 * Construtor do cenário
	 * 
	 * @param num
	 *            número do cenário
	 * @param desc
	 *            descrição do cenário, o que talvez vá acontecer.
	 */
	public Scenario(int num, String desc) {
		this.num = num;
		this.desc = desc;
		this.status = "Nao finalizado";
		this.bets = new HashSet<>();
	}

	/**
	 * Getter
	 * 
	 * @return num
	 */
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return num + " - " + desc + " - " + status;
	}

	/**
	 * Cadastra uma aposta.
	 * 
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 */
	public void addBet(String better, int value, String prediction) {
		if (!status.equals("Nao finalizado"))
			throw new IllegalArgumentException("Caixa finalizado");

		bets.add(new Bet(better, value, prediction));
	}

	/**
	 * Mostra a quantia de apostas do cenário.
	 * 
	 * @return Quantia de apostas.
	 */
	public int getBetsQty() {
		return bets.size();
	}

	/**
	 * Mostra o valor total apostado no cenário.
	 * 
	 * @return Valor total de apostas.
	 */
	public int getBetsValue() {
		int temp = 0;
		for (Bet bet : bets)
			temp += bet.getValue();

		return temp;
	}

	/**
	 * Mostra o toString de todas as apostas do cenário.
	 * 
	 * @return apostador - valor - previsão
	 */
	public String toStringBets() {
		String temp = "";
		for (Bet bet : bets)
			temp += bet + System.lineSeparator();

		return temp.trim();
	}

	/**
	 * Finaliza um cenário para que ele não receba mais apostas.
	 * 
	 * @param ocurred
	 *            Resultado(Finalizado (ocorreu)/Finalizado (n ocorreu))
	 */
	public void finalize(boolean ocurred) {
		if (ocurred)
			status = "Finalizado (ocorreu)";
		else
			status = "Finalizado (n ocorreu)";
	}

	/**
	 * Retorna o valor que deve ser dividido entre o caixa e os vencedores.
	 * 
	 * @return valor
	 */
	public int getCashier() {
		int temp = 0;
		for (Bet bet : bets) {
			boolean check1 = bet.getPrediction().equals("VAI ACONTECER") && !status.equals("Finalizado (ocorreu)");
			boolean check2 = bet.getPrediction().equals("N VAI ACONTECER") && !status.equals("Finalizado (n ocorreu)");

			if (check1 || check2)
				temp += bet.getValue();
		}

		return temp;
	}

	/**
	 * Retorna se o cenário encontra-se fechado ou não.
	 * 
	 * @return true se fechado, false se aberto.
	 */
	public boolean closed() {
		if (!status.equals("Nao finalizado"))
			return true;
		return false;
	}

}

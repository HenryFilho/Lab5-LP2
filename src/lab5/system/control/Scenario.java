package lab5.system.control;

import java.util.ArrayList;
import java.util.List;

/**
 * Cenário pai.
 * 
 * @author Henry Filho
 *
 */

public class Scenario {

	private int num;
	private String desc;
	private String status;
	private List<Bet> bets;

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
		this.bets = new ArrayList<>();
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
	 * @return Posição da aposta.
	 */
	public int addBet(String better, int value, String prediction) {
		bets.add(new Bet(better, value, prediction));
		return bets.size();
	}

	/**
	 * Cadastra uma aposta com seguro por valor.
	 * 
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 * @param assuranceValue
	 *            Valor do seguro.
	 * @return Posição da aposta.
	 */
	public int addBet(String better, int value, String prediction, int assuranceValue) {
		bets.add(new AssuredBet<Integer>(better, value, prediction, assuranceValue));
		return bets.size();
	}

	/**
	 * Cadastra uma aposta com seguro por taxa.
	 * 
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 * @param rate
	 *            Taxa do seguro.
	 * @return Posição da aposta.
	 */
	public int addBet(String better, int value, String prediction, double rate) {
		bets.add(new AssuredBet<Double>(better, value, prediction, rate));
		return bets.size();
	}

	/**
	 * Modifica o seguro de uma aposta para um valor.
	 * 
	 * @param assuredBet
	 *            Posição da aposta.
	 * @param value
	 *            Valor do seguro da aposta.
	 * @return Posição da aposta.
	 */
	public int setAssurance(int assuredBet, int value) {
		bets.get(assuredBet - 1).setAssurance(value);
		return assuredBet;
	}

	/**
	 * Modifica o seguro de uma aposta para uma taxa.
	 * 
	 * @param assuredBet
	 *            Posição da aposta.
	 * @param rate
	 *            Taxa do seguro da aposta.
	 * @return Posição da aposta.
	 */
	public int setRate(int assuredBet, double rate) {
		bets.get(assuredBet - 1).setAssurance(rate);
		return assuredBet;
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
	 * Retorna o valor do seguro.
	 * 
	 * @return valor do seguro.
	 */
	public int getAssurance() {
		int temp = 0;
		for (Bet bet : bets) {
			boolean check1 = bet.getPrediction().equals("VAI ACONTECER") && !status.equals("Finalizado (ocorreu)");
			boolean check2 = bet.getPrediction().equals("N VAI ACONTECER") && !status.equals("Finalizado (n ocorreu)");

			if ((check1 || check2) && bet instanceof AssuredBet) {
				temp += bet.getAssurance();
			}
		}

		return temp;
	}

	/**
	 * Retorna o valor que deve ser dado aos vencedores.
	 * 
	 * @param rate
	 *            Taxa do valor recebido pelo caixa.
	 * @return valor dado aos vencedores.
	 */
	public int getReward(double rate) {
		return getCashier() - (int) (getCashier() * rate);
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

	/**
	 * Retorna a descrição do cenário.
	 * 
	 * @return Descrição do cenário.
	 */
	public String getDesc() {
		return desc;
	}

}

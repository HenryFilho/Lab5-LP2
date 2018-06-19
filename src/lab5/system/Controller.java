package lab5.system;

import java.util.ArrayList;

/**
 * Laboratório de Programação 2 - Lab 5 (Controlador)
 * 
 * @author Henry Maldiney de Lira Nóbrega Filho - 117210389
 *
 */

public class Controller {

	private int cashier;
	private double rate;
	private ArrayList<Scenario> scenarios;

	/**
	 * Construtor do controlador
	 * 
	 * @param cents
	 *            dinheiro iniciais do caixa.
	 * @param rate
	 *            taxa que é tirada das apostas e adicionada ao caixa.
	 */
	public Controller(int cents, double rate) {
		if(cents < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		if(rate < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		
		this.cashier = cents;
		this.rate = rate;
		this.scenarios = new ArrayList<>();
	}

	/**
	 * Cadastra um cenário.
	 * 
	 * @param desc
	 *            descrição do cenário, o que talvez vá acontecer.
	 * @return retorna o número correspondente do cenário cadastrado.
	 */
	public int addScenario(String desc) {
		if(desc.equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		
		Scenario temp = new Scenario(scenarios.size() + 1, desc);
		scenarios.add(temp);
		return temp.getNum();
	}

	/**
	 * Cadastra uma aposta.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 */
	public void addBet(int scenario, String better, int value, String prediction) {
		scenarios.get(scenario - 1).addBet(better, value, prediction);
	}

	/**
	 * Exibe o toString de todos os cenários cadastrados.
	 * 
	 * @return número - descrição - estado
	 */
	public String toStringScenario() {
		String temp = "";
		for (int i = 0; i < scenarios.size(); i++) {
			temp += scenarios.get(i).toString() + System.lineSeparator();
		}
		return temp.trim();
	}

	/**
	 * Exibe o toString de um cenário especifico.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return número - descrição - estado
	 */
	public String toStringScenario(int scenario) {
		if(scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
		if(scenario > scenarios.size())
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario nao cadastrado");
		
		return scenarios.get(scenario - 1).toString();
	}

	/**
	 * Exibe o dinheiro total do caixa.
	 * 
	 * @return centavos no caixa.
	 */
	public int getCashier() {
		return cashier;
	}

	/**
	 * Retorna o valor das apostas que deve ser adicionado ao caixa.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Valor adicionado ao caixa.
	 */
	public int getCashier(int scenario) {
		cashier += this.cashierCalc(scenario);
		return this.cashierCalc(scenario);
	}

	/**
	 * Retorna o prêmio que deve ser distribuido entre os vencedores.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Valor do prêmio.
	 */
	public int getReward(int scenario) {
		return scenarios.get(scenario - 1).getCashier() - this.cashierCalc(scenario);
	}

	/**
	 * Calcula o valor que deve ser adicionado ao caixa.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return valor que deve ser adicionado ao caixa.
	 */
	private int cashierCalc(int scenario) {
		int temp = scenarios.get(scenario - 1).getCashier();
		return (int) (temp * rate);
	}

	/**
	 * Mostra a quantia de apostas em um dado cenário.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Quantia total de apostas.
	 */
	public int totalBets(int scenario) {
		return scenarios.get(scenario - 1).getBetsQty();
	}

	/**
	 * Mostra o valor total apostado em um determinado cenário.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Valor total de apostas.
	 */
	public int totalBetsValue(int scenario) {
		return scenarios.get(scenario - 1).getBetsValue();
	}

	/**
	 * Mostra o toString de todas as apostas de um determinado cenário.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return apostador - valor - previsão
	 */
	public String toStringBets(int scenario) {
		return scenarios.get(scenario - 1).toStringBets();
	}

	/**
	 * Finaliza um cenário para que ele não receba mais apostas.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @param ocurred
	 *            Resultado(Finalizado (ocorreu)/Finalizado (n ocorreu))
	 */
	public void finalizeBet(int scenario, boolean ocurred) {
		scenarios.get(scenario - 1).finalize(ocurred);
	}

}

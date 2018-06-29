package lab5.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lab5.system.control.Scenario;
import lab5.system.control.ScenarioBonus;
import lab5.system.sorters.SorterName;
import lab5.system.sorters.SorterQty;

/**
 * Controlador do sistema, responsável pelas principais funções do programa e
 * pelo tratamento de exceções.
 * 
 * @author Henry Filho
 *
 */

public class Controller {

	private int cashier;
	private double rate;
	private List<Scenario> scenarios;
	private String order;

	/**
	 * Construtor do controlador
	 * 
	 * @param cents
	 *            dinheiro iniciais do caixa.
	 * @param rate
	 *            taxa que é tirada das apostas e adicionada ao caixa.
	 */
	public Controller(int cents, double rate) {
		if (cents < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		if (rate < 0)
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");

		this.cashier = cents;
		this.rate = rate;
		this.scenarios = new ArrayList<>();
		this.order = "cadastro";
	}

	/**
	 * Cadastra um cenário.
	 * 
	 * @param desc
	 *            descrição do cenário, o que talvez vá acontecer.
	 * @return retorna o número correspondente do cenário cadastrado.
	 */
	public int addScenario(String desc) {
		if (desc.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		if (desc.equals(null))
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");

		Scenario temp = new Scenario(scenarios.size() + 1, desc);
		scenarios.add(temp);
		return temp.getNum();
	}

	/**
	 * Cadastra um cenário com um bônus que será dado aos vencedores.
	 * 
	 * @param desc
	 *            descrição do cenário.
	 * @param bonus
	 *            bônus que será dado aos vencedores.
	 * @return retorna o número correspondente do cenário cadastrado.
	 */
	public int addScenario(String desc, int bonus) {
		if (desc.trim().equals(""))
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		if (desc.equals(null))
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		if (bonus <= 0)
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");

		cashier -= bonus;
		ScenarioBonus temp = new ScenarioBonus(scenarios.size() + 1, desc, bonus);
		scenarios.add(temp);
		return temp.getNum();
	}

	/**
	 * exceções lançadas durante o cadastro de aposta. Método feito com o intuito de
	 * não repetir tantas vezes as várias exceções lançadas pelos métodos addBet.
	 */
	private void addBetExceptions(int scenario, String better, int value, String prediction, String msg) {
		if (scenario <= 0)
			throw new IllegalArgumentException(msg + ": Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException(msg + ": Cenario nao cadastrado");
		if (scenarios.get(scenario - 1).closed())
			throw new IllegalArgumentException(msg + ": Cenario ja esta fechado");
		if (better.trim().equals(""))
			throw new IllegalArgumentException(msg + ": Apostador nao pode ser vazio ou nulo");
		if (better.equals(null))
			throw new NullPointerException(msg + ": Apostador nao pode ser vazio ou nulo");
		if (value <= 0)
			throw new IllegalArgumentException(msg + ": Valor nao pode ser menor ou igual a zero");
		if (prediction.trim().equals(""))
			throw new IllegalArgumentException(msg + ": Previsao nao pode ser vazia ou nula");
		if (prediction.equals(null))
			throw new NullPointerException(msg + ": Previsao nao pode ser vazia ou nula");
		if (!prediction.equals("VAI ACONTECER") && !prediction.equals("N VAI ACONTECER"))
			throw new IllegalArgumentException(msg + ": Previsao invalida");
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
	 * @return posição da aposta.
	 */
	public int addBet(int scenario, String better, int value, String prediction) {
		addBetExceptions(scenario, better, value, prediction, "Erro no cadastro de aposta");

		return scenarios.get(scenario - 1).addBet(better, value, prediction);
	}

	/**
	 * Cadastra uma aposta assegurada por valor.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 * @param assuranceValue
	 *            valor do seguro.
	 * @param cost
	 *            custo da aposta.
	 * @return posição da aposta.
	 */
	public int addBet(int scenario, String better, int value, String prediction, int assuranceValue, int cost) {
		addBetExceptions(scenario, better, value, prediction, "Erro no cadastro de aposta assegurada por valor");

		cashier += cost;
		return scenarios.get(scenario - 1).addBet(better, value, prediction, assuranceValue);

	}

	/**
	 * Cadastra uma aposta assegurada por taxa.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 * @param rate
	 *            taxa do seguro.
	 * @param cost
	 *            custo da aposta.
	 * @return posição da aposta.
	 */
	public int addBet(int scenario, String better, int value, String prediction, double rate, int cost) {
		addBetExceptions(scenario, better, value, prediction, "Erro no cadastro de aposta assegurada por taxa");

		cashier += cost;
		return scenarios.get(scenario - 1).addBet(better, value, prediction, rate);
	}

	public int setAssurance(int scenario, int assuredBet, int value) {
		return scenarios.get(scenario - 1).setAssurance(assuredBet, value);
	}

	public int setRate(int scenario, int assuredBet, double rate) {
		return scenarios.get(scenario - 1).setRate(assuredBet, rate);
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
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta de cenario: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro na consulta de cenario: Cenario nao cadastrado");

		return scenarios.get(scenario - 1).toString();
	}
	
	public String toStringOrderedScenario(int scenario) {
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta de cenario ordenado: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro na consulta de cenario ordenado: Cenario nao cadastrado");
		
		if (order.equals("cadastro"))
			return scenarios.get(scenario - 1).toString();
		if (order.equals("nome")) {
			List<Scenario> temp = new ArrayList<>(scenarios);
			Collections.sort(temp, new SorterName());
			return temp.get(scenario-1).toString();
		}if (order.equals("apostas")) {
			List<Scenario> temp = new ArrayList<>(scenarios);
			Collections.sort(temp, new SorterQty());
			return temp.get(scenario-1).toString();
			
		}return "punch";
	}

	public void changeOrder(String order) {
		if (order.trim().equals("") || order.equals(null))
			throw new NullPointerException("Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		if (!order.equals("cadastro") && !order.equals("nome") && !order.equals("apostas"))
			throw new IllegalArgumentException("Erro ao alterar ordem: Ordem invalida");
		
		this.order = order;
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
	 * Retorna o valor das apostas que foi adicionado ao caixa.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Valor adicionado ao caixa.
	 */
	public int getCashier(int scenario) {
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		if (!scenarios.get(scenario - 1).closed())
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");

		int temp = scenarios.get(scenario - 1).getCashier();
		return (int) (temp * rate);
	}

	/**
	 * Retorna o prêmio que deve ser distribuido entre os vencedores.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Valor do prêmio.
	 */
	public int getReward(int scenario) {
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException(
					"Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		if (!scenarios.get(scenario - 1).closed())
			throw new IllegalArgumentException(
					"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");

		return scenarios.get(scenario - 1).getReward(rate);
	}

	/**
	 * Mostra a quantia de apostas em um dado cenário.
	 * 
	 * @param scenario
	 *            Cenário desejado.
	 * @return Quantia total de apostas.
	 */
	public int totalBets(int scenario) {
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do total de apostas: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro na consulta do total de apostas: Cenario nao cadastrado");

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
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro na consulta do valor total de apostas: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");

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
		if (scenario <= 0)
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario invalido");
		if (scenario > scenarios.size())
			throw new IndexOutOfBoundsException("Erro ao fechar aposta: Cenario nao cadastrado");
		if (scenarios.get(scenario - 1).closed())
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");

		scenarios.get(scenario - 1).finalize(ocurred);
		cashier += scenarios.get(scenario - 1).getCashier() * rate;
		cashier -= scenarios.get(scenario - 1).getAssurance();
	}

}

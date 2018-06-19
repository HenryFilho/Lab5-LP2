package lab5;

import easyaccept.EasyAccept;
import lab5.system.Controller;

/**
 * Laboratório de Programação 2 - Lab 5 (Fachada)
 * 
 * @author Henry Maldiney de Lira Nóbrega Filho - 117210389
 *
 */

public class Facade {
	private Controller control;

	public static void main(String[] args) {
		args = new String[] {"lab5.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt",
				"acceptance_test/us4_test.txt"};
		EasyAccept.main(args);
	}
	
	/**
	 * Inicializa o controlador.
	 * 
	 * @param centavos
	 *            dinheiro iniciais do caixa.
	 * @param taxa
	 *            taxa que é tirada das apostas e adicionada ao caixa.
	 */
	public void inicializa(int centavos, double taxa) {
		control = new Controller(centavos, taxa);
	}

	/**
	 * Exibe o dinheiro total do caixa.
	 * 
	 * @return centavos no caixa.
	 */
	public int getCaixa() {
		return control.getCashier();
	}

	/**
	 * Cadastra um cenário.
	 * 
	 * @param descricao
	 *            descrição do cenário, o que talvez vá acontecer.
	 * @return retorna o número correspondente do cenário cadastrado.
	 */
	public int cadastrarCenario(String descricao) {
		return control.addScenario(descricao);
	}

	/**
	 * Exibe o toString de todos os cenários cadastrados.
	 * 
	 * @return número - descrição - estado
	 */
	public String exibirCenarios() {
		return control.toStringScenario();
	}

	/**
	 * Exibe o toString de um cenário especifico.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return número - descrição - estado
	 */
	public String exibirCenario(int cenario) {
		return control.toStringScenario(cenario);
	}

	/**
	 * Cadastra uma aposta.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @param apostador
	 *            Nome do apostador.
	 * @param valor
	 *            Valor da aposta.
	 * @param previsao
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		control.addBet(cenario, apostador, valor, previsao);
	}

	/**
	 * Mostra a quantia de apostas em um dado cenário.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return Quantia de apostas.
	 */
	public int totalDeApostas(int cenario) {
		return control.totalBets(cenario);
	}

	/**
	 * Mostra o valor total apostado em um determinado cenário.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return Valor total de apostas.
	 */
	public int valorTotalDeApostas(int cenario) {
		return control.totalBetsValue(cenario);
	}

	/**
	 * Mostra o toString de todas as apostas de um determinado cenário.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return apostador - valor - previsão
	 */
	public String exibeApostas(int cenario) {
		return control.toStringBets(cenario);
	}

	/**
	 * Finaliza um cenário para que ele não receba mais apostas.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @param ocorreu
	 *            Resultado(Finalizado (ocorreu)/Finalizado (n ocorreu))
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		control.finalizeBet(cenario, ocorreu);
	}

	/**
	 * Retorna o valor das apostas que deve ser adicionado ao caixa.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return Valor adicionado ao caixa.
	 */
	public int getCaixaCenario(int cenario) {
		return control.getCashier(cenario);
	}

	/**
	 * Retorna o prêmio que deve ser distribuido entre os vencedores.
	 * 
	 * @param cenario
	 *            Cenário desejado.
	 * @return Valor do prêmio.
	 */
	public int getTotalRateioCenario(int cenario) {
		return control.getReward(cenario);
	}
}
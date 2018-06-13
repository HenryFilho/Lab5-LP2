package lab5;

import lab5.system.Controller;

public class Facade {
	private Controller control;

	public void inicializa(int centavos, double taxa) {
		control = new Controller(centavos, taxa);
	}
	
	public int getCaixa() {
		return control.getCashier();
	}
	
	public int cadastrarCenario(String descricao) {
		return control.addScenario(descricao);
	}
	
	public String exibirCenarios() {
		return control.toStringScenario();
	}
	
	public String exibirCenario(int cenario) {
		return control.toStringScenario(cenario);
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		control.addBet(cenario, apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return control.totalBets(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return control.toStringBets(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		control.finalizeBet(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		return control.getCashier(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return control.getReward(cenario);
	}
}
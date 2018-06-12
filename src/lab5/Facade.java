package lab5;

public class Facade {
	private Controller control;

	public void inicializa(int centavos, double taxa) {
		control = new Controller(centavos, taxa);
	}
	
	public int getCaixa() {
		return control.getCaixa();
	}
	
	public int cadastrarCenario(String descricao) {
		return control.addCenario(descricao);
	}
	
	public String exibirCenarios() {
		return control.toStringCenario();
	}
	
	public String exibirCenario(int cenario) {
		return control.toStringCenario(cenario);
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		control.addAposta(cenario, apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return control.totalApostas(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return control.toStringApostas(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		control.finalizaAposta(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		
	}
}
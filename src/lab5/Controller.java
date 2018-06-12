package lab5;

import java.util.ArrayList;

public class Controller {
	
	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	
	public Controller(int centavos, double taxa) {
		this.caixa = centavos;
		this.taxa = taxa;
		this.cenarios = new ArrayList<>();
	}

	public int addCenario(String descricao) {
		Cenario temp = new Cenario(cenarios.size()+1, descricao);
		cenarios.add(temp);
		return temp.getNum();
	}

	public void addAposta(int cenario, String apostador, int valor, String previsao) {
		cenarios.get(cenario-1).addAposta(apostador, valor, previsao);
	}
	
	public String toStringCenario() {
		String temp = "";
		for(int i = 0; i < cenarios.size(); i++) {
			temp += cenarios.get(i).toString();
			
			if(i+1 < cenarios.size())
				temp += System.lineSeparator();
		}
		return temp;
	}
	
	public String toStringCenario(int cenario) {
		return cenarios.get(cenario-1).toString();
	}
	
	public int getCaixa() {
		return caixa;
	}

	public int totalApostas(int cenario) {
		return cenarios.get(cenario-1).getApostasQtd();
	}

	public String toStringApostas(int cenario) {
		return cenarios.get(cenario-1).toStringApostas();
	}

	public void finalizaAposta(int cenario, boolean ocorreu) {
		cenarios.get(cenario-1).finalizar(ocorreu);
	}

}

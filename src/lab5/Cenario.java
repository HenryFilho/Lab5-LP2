package lab5;

import java.util.HashSet;
import java.util.Iterator;

public class Cenario {
	
	private int num;
	private String descricao;
	private String estado;
	private HashSet<Aposta> apostas;
	
	public Cenario(int num, String descricao) {
		this.num = num;
		this.descricao = descricao;
		this.estado = "Não finalizado";
		this.apostas = new HashSet<>();
	}

	public int getNum() {
		return num;
	}
	
	@Override
	public String toString() {
		return num + " - " + descricao + " - " + estado;
	}

	public void addAposta(String apostador, int valor, String previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
	}

	public int getApostasQtd() {
		return apostas.size();
	}

	public String toStringApostas() {
		String temp = "";
		Iterator<Aposta> itr = apostas.iterator();
		while(itr.hasNext()) {
			temp += itr.next().toString();
			if(itr.hasNext())
				temp += System.lineSeparator();
		}
		return temp;
	}

	public void finalizar(boolean ocorreu) {
		if(ocorreu)
			estado = "Finalizado (ocorreu)";
		else
			estado = "Finalizado (n ocorreu)";
	}

}

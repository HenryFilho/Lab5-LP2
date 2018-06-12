package lab5;

public class Aposta {

	private String apostador;
	private int valor;
	private String previsao;
	
	public Aposta(String apostador, int valor, String previsao) {
		this.apostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}
	
	@Override
	public String toString() {
		Double temp = valor / 100.0;
		return String.format("%s - R$%.2f - %s", apostador, temp, previsao);
	}

}

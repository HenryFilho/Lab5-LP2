package lab5.system;

public class Bet {

	private String better;
	private int value;
	private String prediction;

	/**
	 * Construtor da aposta
	 * 
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão(N VAI ACONTECER/VAI ACONTECER)
	 */
	public Bet(String better, int value, String prediction) {
		this.better = better;
		this.value = value;
		this.prediction = prediction;
	}

	@Override
	public String toString() {
		Double temp = value / 100.0;
		return String.format("%s - R$%.2f - %s", better, temp, prediction);
	}

	/**
	 * Getter
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Getter
	 * @return better
	 */
	public String getBetter() {
		return better;
	}

	/**
	 * Getter
	 * @return prediction
	 */
	public String getPrediction() {
		return prediction;
	}

}

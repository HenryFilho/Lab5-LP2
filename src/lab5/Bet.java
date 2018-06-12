package lab5;

public class Bet {

	private String better;
	private int value;
	private String prediction;
	
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

	public int getValue() {
		return value;
	}
	
	public String getBetter() {
		return better;
	}
	
	public String getPrediction() {
		return prediction;
	}

}

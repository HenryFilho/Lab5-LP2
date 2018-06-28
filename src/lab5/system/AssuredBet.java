package lab5.system;

/**
 * Aposta com seguro, seja por valor ou taxa, que será dado ao apostador caso
 * ele erre. No caso de valor ele receberá um valor bruto, no caso de taxa será
 * uma porcentagem do valor dado em sua aposta.
 * 
 * @author Henry Filho
 *
 * @param <E>
 *            Tipo de seguro: Integer para seguro de valor, Double para seguro
 *            de taxa. (Utilizar outros tipos acarretarão em erros, e não há
 *            motivos para utilizá-los, portanto não use.)
 */

public class AssuredBet<E> extends Bet {

	private int assurance;
	private double rate;

	/**
	 * Construtor da aposta.
	 * 
	 * @param better
	 *            Nome do apostador.
	 * @param value
	 *            Valor da aposta.
	 * @param prediction
	 *            Previsão da aposta.
	 * @param assurance
	 *            Seguro da aposta. (Integer ou Double, NÃO utilize outros valores.)
	 */
	public AssuredBet(String better, int value, String prediction, E assurance) {
		super(better, value, prediction);
		setAssurance(assurance);
	}

	@Override
	public int getAssurance() {
		return assurance;
	}

	@Override
	public void setAssurance(Object assurance) {
		rate = 0.0;
		if (assurance instanceof Integer)
			this.assurance = (int) assurance;
		else if (assurance instanceof Double) {
			this.assurance = (int) ((double) assurance * value);
			rate = (double) assurance;
		}
	}

	@Override
	public String toString() {
		String temp;
		if (rate == 0.0) {
			temp = String.format("- ASSEGURADA (VALOR) - R$ %.2f", assurance);
		} else {
			temp = String.format("- ASSEGURADA (TAXA) - %f", rate);
		}
		return super.toString() + temp;
	}

}

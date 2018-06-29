package lab5.system.control;

/**
 * Cenário com um bônus que é distribuido entre os vencedores.
 * 
 * @author Henry Filho
 *
 */

public class ScenarioBonus extends Scenario {

	private int bonus;

	/**
	 * Construtor do cenário.
	 * 
	 * @param num
	 *            Número do cenário.
	 * @param desc
	 *            Descrição do cenário.
	 * @param bonus
	 *            Bônus distribuido entre os vencedores.
	 */
	public ScenarioBonus(int num, String desc, int bonus) {
		super(num, desc);
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		double temp = bonus / 100.0;
		return String.format("%s - R$ %.2f", super.toString(), temp);
	}

	@Override
	public int getReward(double rate) {
		return super.getReward(rate) + bonus;
	}
}

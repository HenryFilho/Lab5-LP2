package lab5.system;

public class ScenarioBonus extends Scenario {

	private int bonus;

	public ScenarioBonus(int num, String desc, int bonus) {
		super(num, desc);
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		double temp = bonus / 100.0;
		return String.format("%s - R$ %.2f", super.toString(), temp);
	}

	/**
	 * Retorna o bonus que deve ser dado aos vencedores
	 * 
	 * @return bonus
	 */
	public int getBonus() {
		return bonus;
	}
}
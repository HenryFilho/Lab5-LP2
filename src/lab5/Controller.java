package lab5;

import java.util.ArrayList;

public class Controller {
	
	private int cashier;
	private double rate;
	private ArrayList<Scenario> scenarios;
	
	public Controller(int cents, double rate) {
		this.cashier = cents;
		this.rate = rate;
		this.scenarios = new ArrayList<>();
	}

	public int addScenario(String desc) {
		Scenario temp = new Scenario(scenarios.size()+1, desc);
		scenarios.add(temp);
		return temp.getNum();
	}

	public void addBet(int scenario, String better, int value, String prediction) {
		scenarios.get(scenario-1).addBet(better, value, prediction);
	}
	
	public String toStringScenario() {
		String temp = "";
		for(int i = 0; i < scenarios.size(); i++) {
			temp += scenarios.get(i).toString();
			
			if(i+1 < scenarios.size())
				temp += System.lineSeparator();
		}
		return temp;
	}
	
	public String toStringCenario(int scenario) {
		return scenarios.get(scenario-1).toString();
	}
	
	public int getCashier() {
		return cashier;
	}
	
	public int getCashier(int scenario) {
		cashier += this.cashierCalc(scenario);
		return this.cashierCalc(scenario);
	}
	
	public int getReward(int scenario) {
		return scenarios.get(scenario-1).getCashier() - this.cashierCalc(scenario);
	}
	
	private int cashierCalc(int scenario) {
		int temp = scenarios.get(scenario-1).getCashier();
		return (int) (temp * rate);
	}

	public int totalBets(int scenario) {
		return scenarios.get(scenario-1).getBetsQty();
	}

	public String toStringBets(int scenario) {
		return scenarios.get(scenario-1).toStringBets();
	}

	public void finalizeBet(int scenario, boolean ocorred) {
		scenarios.get(scenario-1).finalize(ocorred);
	}

	

}

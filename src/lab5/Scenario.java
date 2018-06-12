package lab5;

import java.util.HashSet;
import java.util.Iterator;

public class Scenario {
	
	private int num;
	private String desc;
	private String status;
	private HashSet<Bet> bets;
	
	public Scenario(int num, String desc) {
		this.num = num;
		this.desc = desc;
		this.status = "Não finalizado";
		this.bets = new HashSet<>();
	}

	public int getNum() {
		return num;
	}
	
	@Override
	public String toString() {
		return num + " - " + desc + " - " + status;
	}

	public void addBet(String better, int value, String prediction) {
		if(!status.equals("Não finalizado"))
			throw new RuntimeException("Caixa finalizado");
		
		bets.add(new Bet(better, value, prediction));
	}

	public int getBetsQty() {
		return bets.size();
	}

	public String toStringBets() {
		String temp = "";
		Iterator<Bet> itr = bets.iterator();
		while(itr.hasNext()) {
			temp += itr.next().toString();
			if(itr.hasNext())
				temp += System.lineSeparator();
		}
		return temp;
	}

	public void finalize(boolean ocurred) {
		if(ocurred)
			status = "Finalizado (ocorreu)";
		else
			status = "Finalizado (n ocorreu)";
	}

	public int getCashier() {
		int temp = 0;
		if(!status.equals("Não finalizado")) {
			Iterator<Bet> itr = bets.iterator();
			while(itr.hasNext()) {
				Bet tempBet = itr.next();
				
				boolean check1 = tempBet.getPrediction().equals("VAI ACONTECER") && !status.equals("Finalizado (ocorreu)");
				boolean check2 = tempBet.getPrediction().equals("N VAI ACONTECER") && !status.equals("Finalizado (n ocorreu)");
				
				if(check1 || check2) {
					temp += tempBet.getValue();
					}
			}
			
			return temp;
		}throw new RuntimeException("Caixa não finalizado");
	}

}

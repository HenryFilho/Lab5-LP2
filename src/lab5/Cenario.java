package lab5;

public class Cenario {
	
	private int num;
	private String descricao;
	private String estado;
	
	public Cenario(int num, String descricao) {
		this.num = num;
		this.descricao = descricao;
		this.estado = "Não finalizado";
	}

	public int getNum() {
		return num;
	}
	
	@Override
	public String toString() {
		return num + " - " + descricao + " - " + estado;
	}

	public void addAposta(String apostador, int valor, String previsao) {
		// TODO Auto-generated method stub
		
	}

}

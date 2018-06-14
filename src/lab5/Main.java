package lab5;

public class Main {
    public static void main(String[] args) {
         Facade facade = new Facade();
         int centavos = 100000;
         double taxa = 0.01;
         facade.inicializa(centavos, taxa);
         facade.cadastrarCenario("A maioria irá tirar mais do que 7 na prova!");
         
         facade.cadastrarAposta(1, "Francisco Cisco", 20000, "N VAI ACONTECER");
         facade.cadastrarAposta(1, "Anonimo", 199, "N VAI ACONTECER");
         facade.cadastrarAposta(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
         facade.cadastrarAposta(1, "Livia Maria", 30000, "VAI ACONTECER");
         facade.cadastrarAposta(1, "Raquel Lopes", 20000, "VAI ACONTECER");
         facade.cadastrarAposta(1, "Matheus Gaudencio", 10000, "VAI ACONTECER");
         facade.fecharAposta(1, true);
         System.out.println(facade.getCaixaCenario(1));
         System.out.println(facade.getTotalRateioCenario(1));
         System.out.println(facade.exibeApostas(1));
         System.out.println(facade.valorTotalDeApostas(1));
         
         
    }
}


/**
“Francisco Cisco - R$200,00 - N VAI ACONTECER”
“Anonimo - R$1,99 - N VAI ACONTECER”
“Matheus Gaudencio - R$100,00 - VAI ACONTECER”
“Livia Maria - R$300,00 - VAI ACONTECER”
“Raquel Lopes- R$200,00 - VAI ACONTECER”
“Matheus Gaudencio - R$100,00 - VAI ACONTECER”
*/
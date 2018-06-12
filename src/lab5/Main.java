package lab5;

public class Main {
    public static void main(String[] args) {
         Facade facade = new Facade();
         int centavos = 100000;
         double taxa = 0.01;
         facade.inicializa(centavos, taxa);
         facade.cadastrarCenario("A maioria irá tirar mais do que 7 na prova!");
         
         System.out.println(facade.exibirCenarios());
         facade.cadastrarAposta(1, "Teste", 2525, "Bola");
         System.out.println(facade.exibeApostas(1));
    }
}

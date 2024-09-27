package Empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    private List<Moeda> moedas;

    public Principal() {
        moedas = new ArrayList<>();
    }

    public void adicionarMoeda(Moeda moeda) {
        moedas.add(moeda);
        System.out.println("Moeda adicionada ao cofrinho.");
    }

    public void removerMoeda(Moeda moeda) {
        if (moedas.remove(moeda)) {
            System.out.println("Moeda removida do cofrinho.");
        } else {
            System.out.println("A moeda não foi encontrada no cofrinho.");
        }
    }

    public void listarMoedas() {
        if (moedas.isEmpty()) {
            System.out.println("Não há nada no seu cofrinho.");
        } else {
            System.out.println("Moedas no Cofrinho:");
            for (Moeda moeda : moedas) {
                System.out.println(moeda);
            }
        }
    }

    public double calcularValorTotalEmReais() {
        double valorTotal = 0;
        for (Moeda moeda : moedas) {
            valorTotal += moeda.getValorEmReais();
        }
        return valorTotal;
    }

    public static void main(String[] args) {
        Principal cofrinho = new Principal();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("----- Cofrinho de Moedas -----");
            System.out.println("1 - Adicionar moedas");
            System.out.println("2 - Remover moedas");
            System.out.println("3 - Listar moedas");
            System.out.println("4 - Calcular valor total em Reais");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma das opcoes: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor da moeda: ");
                    double valor = scanner.nextDouble();
                    System.out.println("Digite a moeda (USD, EUR, BRL): ");
                    String pais = scanner.next().toUpperCase();
                    Moeda moeda = new Moeda(valor, pais);
                    cofrinho.adicionarMoeda(moeda);
                    break;
                case 2:
                    System.out.println("Digite o valor da moeda a ser removida: ");
                    double valorRemover = scanner.nextDouble();
                    System.out.println("Digite a moeda a ser removida (USD, EUR, BRL): ");
                    String paisRemover = scanner.next().toUpperCase();
                    Moeda moedaRemover = new Moeda(valorRemover, paisRemover);
                    cofrinho.removerMoeda(moedaRemover);
                    break;
                case 3:
                    cofrinho.listarMoedas();
                    break;
                case 4:
                    double valorTotal = cofrinho.calcularValorTotalEmReais();
                    System.out.println("Valor total em Reais: R$" + valorTotal);
                    break;
                case 5:
                    System.out.println("Saindo do programa... Obrigado por vir!");
                    break;
                default:
                    System.out.println("Opção invalida. Por favor, tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}

class Moeda {
    private double valor;
    private String pais;

    public Moeda(double valor, String pais) {
        this.valor = valor;
        this.pais = pais;
    }

    public double getValorEmReais() {
    	// O preço da moeda foi coletada no dia 17/07/2023 para que ficasse mais próximo da realidade.
        // 1 Dólar = 4.79 Reais
        // 1 Euro = 5.38 Reais
        // 1 Real = 1 Real
        double taxaDeCambio;
        switch (pais) {
            case "USD":
                taxaDeCambio = 4.79;
                break;
            case "EUR":
                taxaDeCambio = 5.38;
                break;
            case "BRL":
                taxaDeCambio = 1.0;
                break;
            default:
                System.out.println("Moeda invalida.");
                return 0.0;
        }
        return valor * taxaDeCambio;
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Moeda outraMoeda = (Moeda) obj;
        return Double.compare(outraMoeda.valor, valor) == 0 &&
                Objects.equals(pais, outraMoeda.pais);
    }

    public int hashCode() {
        return Objects.hash(valor, pais);
    }
    	//Abaixo, o valor que retornará será o valor(quantidade da moeda) e a moeda selecionada.
    	//Apesar de estar a usar a string pais para representar a moeda.
    public String toString() {
        return "Valor: " + valor + " | Moeda: " + pais;
    }
}

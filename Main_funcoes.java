import java.util.Scanner;

public class Main_funcoes{

    static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Informe a quantidade de clientes: ");

		int qtde= s.nextInt();

        String[] nome = new String[qtde];
        String[] telefone = new String[qtde];
        int[] tipo = new int[qtde];
        int[] minutos = new int[qtde];
        double[] valorDaConta = new double[qtde];
        double[][] precos = new double[3][2];
        double menorConta = Double.MAX_VALUE;
		int minutosTotais = 0;
		int consumoMedio = 0;
		int contadorTipo1 = 0;
		int contadorTipo2 = 0;
        double porcentagemTipo2 = 0;

        lerDadosClientes(nome, telefone, tipo, minutos);
        lerPrecos(precos);


        calcularValoresContas(tipo, minutos, valorDaConta, precos);
        double receitaTotal = calcularReceitaTotal(valorDaConta);

		for(int i = 0; i < qtde; i++){
			if(tipo[i]== 1){
				minutosTotais+=minutos[i];
				contadorTipo1++;
			}
			if(tipo[i]== 2){
				contadorTipo2++;
			}
		}

		if(contadorTipo1 !=0){
		    consumoMedio = minutosTotais / contadorTipo1;
		}

		porcentagemTipo2 = ((double) contadorTipo2 / (double) qtde) * 100;

        int opcao;
        do {
            opcao = exibirMenu();
            processarOpcao(opcao, nome, telefone, tipo, minutos, valorDaConta, receitaTotal, contadorTipo1, consumoMedio, contadorTipo2, porcentagemTipo2);
        } while (opcao != 7);

        System.out.println("FIM DO PROGRAMA!");
        s.close();
    }
    
    static void lerDadosClientes(String[] nome, String[] telefone, int[] tipo, int[] minutos) {
        s.nextLine(); 
        for (int i = 0; i < nome.length; i++) {
            System.out.println("Cliente " + (i + 1) + ":");
            System.out.print("Nome: ");
            nome[i] = s.nextLine();
            System.out.print("Telefone: ");
            telefone[i] = s.nextLine();
            System.out.print("Tipo de assinatura: ");
            tipo[i] = s.nextInt();
            System.out.print("Minutos consumidos: ");
            minutos[i] = s.nextInt();
            s.nextLine();
        }
    }

    static void lerPrecos(double[][] precos) {
        System.out.println("Informe o preço básico e excedente de cada tipo de conta:");
        for (int i = 0; i < 3; i++) {
            System.out.printf("Tipo " + i + ": ");
            precos[i][0] = s.nextDouble();
            precos[i][1] = s.nextDouble();
            s.nextLine();
        }
    }

    static void calcularValoresContas(int[] tipo, int[] minutos, double[] valorDaConta, double[][] precos) {
        for (int i = 0; i < tipo.length; i++) {
            double valorExtra = 0;
            int minutosExtra = 0;

            if (minutos[i] <= 90) {
                valorDaConta[i] = precos[tipo[i]][0];
            } else {
                minutosExtra = minutos[i] - 90;
                valorExtra = precos[tipo[i]][1] * minutosExtra;
                valorDaConta[i] = valorExtra + precos[tipo[i]][0];
            }
        }
    }

    static double calcularReceitaTotal(double[] valorDaConta) {
        double receitaTotal = 0;
        for (int i = 0; i < valorDaConta.length; i++) {
        receitaTotal += valorDaConta[i];
        }
        return receitaTotal;
    }

    static int exibirMenu() {
        System.out.println();
        System.out.println("MENU DE OPCOES:");
        System.out.println("1) Relatorio de clientes");
        System.out.println("2) A receita total");
        System.out.println("3) Conta foi mais barata");
        System.out.println("4) Consumo medio de clientes tipo 1.");
        System.out.println("5) Clientes que consumiram acima de 120 pulsos.");
        System.out.println("6) A porcentagem de clientes tipo 2");
        System.out.println("7) Sair");
        System.out.print("Informe uma opcao: ");
        return s.nextInt();
    }

    static void processarOpcao(int opcao, String[] nome, String[] telefone, int[] tipo, int[] minutos,
                                        double[] valorDaConta, double receitaTotal, int contadorTipo1,
                                        int consumoMedio, int contadorTipo2, double porcentagemTipo2) {
        double menorConta = Double.MAX_VALUE;
        switch (opcao) {
            case 1:
                for (int i = 0; i < nome.length; i++) {
                    System.out.printf("%s, %s, Tipo %d, Minutos: %d, Conta = R$ %.2f\n",
                            nome[i], telefone[i], tipo[i], minutos[i], valorDaConta[i]);
                }
                break;
            case 2:
                System.out.printf("Receita total = R$ %.2f\n", receitaTotal);
                break;
            case 3:
                for (int i = 0; i < valorDaConta.length; i++) {
                    if (valorDaConta[i] < menorConta) {
                    menorConta = valorDaConta[i];
                    }
                }
                System.out.printf("A conta mais barata é R$: %.2f\n", menorConta);
                break;
            case 4:
                System.out.println("O consumo médio dos clientes do Tipo 1: " + consumoMedio);
                break;
            case 5:
                for (int i = 0; i < nome.length; i++) {
                    if (minutos[i] > 120) {
                        System.out.println(nome[i]);
                    }
                }
                break;
            case 6:
                System.out.printf("Porcentagem de clientes tipo 2: %.2f%%\n", porcentagemTipo2);
                break;
            case 7:
                break;
            default:
                System.out.println("Escolha uma opção válida.");
                break;
        }
    }
} 
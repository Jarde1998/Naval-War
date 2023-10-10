package br.unisinos;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		System.out.println("\t⚓ BATALHA NAVAL ⚓\n");
		Jogo j = new Jogo();
		Random random = new Random();
		String opcao = Teclado.leString("Deseja jogar?");
		if ("Sim".equals(opcao) || "sim".equals(opcao) || "s".equals(opcao)) {
			// Jogo começa com sorteio dos objetos, após isso é imprimido na tela o
			// tabuleiro do jogador e computador.
			j.sorteioObjetos();
			j.imprimirTabuleiroJogador();
			j.imprimirTabuleiroC();
			while (true) {
				// Aqui é o controle da jogada do jogador, onde a pessoa digita a linha e a
				// coluna que deseja atirar.
				int linha = 0;
				// Como no exemplo de tabuleiro estava a linha como String, decidi deixar igual,
				// porém é necessário
				// passar para int pois o tabuleiro é int e recebe parametros int.
				String a = Teclado.leString("Linha: ");
				if(!"A".equals(a)&&!a.equals("B")&&!a.equals("C")&&!a.equals("D")&&!a.equals("E")) {
					System.out.println("Linha inválida.");
					while(true) {
						a = Teclado.leString("Digite novamente: ");
						if(a.equals("A")||a.equals("B")||a.equals("C")||a.equals("D")||a.equals("E")) {
							break;
						}
					}
				}
				int coluna = Teclado.leInt("Coluna: ");
				if(coluna != 0 &&coluna != 1 &&coluna != 2 &&coluna != 3 &&coluna != 4) {
					System.out.println("Coluna inválida.");
					while(true) {
						coluna = Teclado.leInt("Digite novamente: ");
						if(coluna==0||coluna==1||coluna==2||coluna==3||coluna==4) {
							break;
						}
					}
				}
				switch (a) {
				case "A":
					linha = 0;
					break;
				case "B":
					linha = 1;
					break;
				case "C":
					linha = 2;
					break;
				case "D":
					linha = 3;
					break;
				case "E":
					linha = 4;
					break;
				}
				// Chamada do método da jogada do jogador e logo abaixo é exibido a quantidade
				// total dos objetos do computador
				// independente se o jogador tenha acertado algo ou não.
				j.jogadaJogador(linha, coluna);
				System.out.println("P(c): " + j.getpC() + " D(c): " + j.getdC() + " S(c): " + j.getsC() + "\n");
				String[] colunasC = { "A", "B", "C", "D", "E" };
				// Syso para imprimir o tabuleiro atualizado do computador.
				System.out.println("\n\tTabuleiro do computador");
				System.out.printf("\t" + 0 + "\t" + 1 + "\t" + 2 + "\t" + 3 + "\t" + 4 + "\n" + "\n");
				for (int i2 = 0; i2 < j.getTabuleiroC().length; i2++) {
					System.out.printf(colunasC[i2] + "\t");
					for (int j2 = 0; j2 < j.getTabuleiroC()[i2].length; j2++) {

						System.out.print(j.getTabuleiroC()[i2][j2] + "\t");

					}
					System.out.println();
				}
				// Após a jogada do jogador é feito a checagem caso todas os objetos tenham sido
				// destruidos,
				// para em seguida parar o jogo e anunciar o jogador como vencedor.
				if (j.getpC() == 0 && j.getdC() == 0 && j.getsC() == 0) {
					System.out.println("O jogador venceu a batalha naval!");
					break;

				}

				// Controle das jogas do jogador através do método random, onde é gerado uma
				// linha e coluna aleatório.
				int linhaC = random.nextInt(5);
				int colunaC = random.nextInt(5);
				j.jogadaComputador(linhaC, colunaC);
				System.out.println("P(j): " + j.getpJ() + " D(j): " + j.getdJ() + " S(j): " + j.getsJ() + "\n");
				// Após a jogada do computador é feito a checagem caso todas os objetos tenham
				// sido destruidos,
				// para em seguida parar o jogo e anunciar o computador como vencedor.
				if (j.getpJ() == 0 && j.getdJ() == 0 && j.getsJ() == 0) {
					System.out.println("O computador venceu a batalha naval!");
					j.sorteioObjetos();
					break;
				}

			}
		}

	}

}

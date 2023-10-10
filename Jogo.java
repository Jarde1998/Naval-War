package br.unisinos;

import java.util.Random;

public class Jogo {
	private int pJ, dJ, sJ, pC, dC, sC;

	private String[][] objJogador = new String[5][5];
	private String[][] objComputador = new String[5][5];
	private String[][] tabuleiroC = new String[5][5];

	public Jogo() {
		// Construtor com os objetos de cada jogador(porta-aviao, destroyer e submarino)
		pJ = 4;
		dJ = 5;
		sJ = 6;
		pC = 4;
		dC = 5;
		sC = 6;
	}

	// Método para sorteio dos objetos do jogador e do computador
	public void sorteioObjetos() {

		Random random = new Random();

		// Contadores para controlar a quantidade de objetos de cada jogador
		int portaAvioes = 0;
		int destroyer = 0;
		int submarino = 0;

		// while para posição dos Porta-aviões
		while (portaAvioes < 4) {
			int linha = 0;
			int coluna = 0;
			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("P".equals(objJogador[linha][coluna]));

			objJogador[linha][coluna] = "P";
			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("P".equals(objComputador[linha][coluna]));

			objComputador[linha][coluna] = "P";
			portaAvioes++;
		}
		// while para posição dos destroyers
		while (destroyer < 5) {
			int linha = 0;
			int coluna = 0;
			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("P".equals(objJogador[linha][coluna]) || "D".equals(objJogador[linha][coluna]));
			objJogador[linha][coluna] = "D";

			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("P".equals(objComputador[linha][coluna]) || "D".equals(objComputador[linha][coluna]));
			objComputador[linha][coluna] = "D";

			destroyer++;
		}
		// while para posição dos submarinos
		while (submarino < 6) {
			int linha = 0;
			int coluna = 0;
			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("S".equals(objJogador[linha][coluna]) || "P".equals(objJogador[linha][coluna])
					|| "D".equals(objJogador[linha][coluna]));
			objJogador[linha][coluna] = "S";

			do {
				linha = random.nextInt(5);
				coluna = random.nextInt(5);
			} while ("S".equals(objComputador[linha][coluna]) || "P".equals(objComputador[linha][coluna])
					|| "D".equals(objComputador[linha][coluna]));
			objComputador[linha][coluna] = "S";

			submarino++;
		}
	}

	// Metodo para imprimir o tabuleiro do jogador; usado apenas no inicio do jogo
	// para que o jogador saiba
	// a posição dos seus objetos
	public void imprimirTabuleiroJogador() {

		String[] colunasJ = { "A", "B", "C", "D", "E" };
		System.out.println("\tTabuleiro do Jogador");
		System.out.printf("\t" + 0 + "\t" + 1 + "\t" + 2 + "\t" + 3 + "\t" + 4 + "\n" + "\n");
		for (int i = 0; i < objJogador.length; i++) {
			System.out.printf(colunasJ[i] + "\t");
			for (int j = 0; j < objJogador[i].length; j++) {
				if (objJogador[i][j] == null) {
					// Foi dito para utilizar o "˜" [ara representar a água porém eu achei que
					// ficava muito feio e mau centralizado,
					// então decidi usar o "-" para isso.
					objJogador[i][j] = "-";
				}
				System.out.print(objJogador[i][j] + "\t");
			}
			System.out.println();
		}
	}

	// Metodo para imprimir o tabuleiro do computador; atualizado em cada rodada
	// caso algum objeto seja atingido
	public void imprimirTabuleiroC() {
		String[] colunasC = { "A", "B", "C", "D", "E" };
		System.out.println("\n\tTabuleiro do computador");
		System.out.printf("\t" + 0 + "\t" + 1 + "\t" + 2 + "\t" + 3 + "\t" + 4 + "\n" + "\n");
		for (int i = 0; i < tabuleiroC.length; i++) {
			System.out.printf(colunasC[i] + "\t");
			for (int j = 0; j < tabuleiroC.length; j++) {
				if (!"P".equals(tabuleiroC[i][j]) || !"D".equals(tabuleiroC[i][j]) || !"S".equals(tabuleiroC[i][j])) {
					// Foi dito para utilizar o "˜" [ara representar a água porém eu achei que
					// ficava muito feio e mau centralizado,
					// então decidi usar o "-" para isso.
					tabuleiroC[i][j] = "-";
					System.out.print(tabuleiroC[i][j] + "\t");
				}
			}
			System.out.println();
		}

	}

	// Método para controlar a jogada do jogador. O jogador digita uma String que
	// representa a linha
	// e no método main é feito a conversão para assim passar o parametro que
	// represente a linha em int.
	public void jogadaJogador(int linha, int coluna) {
		// A String "-" representa no tabuleiro do computador uma posição em que havia
		// um objeto que já tinha sido atingido,
		// dessa forma ele compara e avisa o jogador.
		if ("P".equals(objComputador[linha][coluna]) || objComputador[linha][coluna] == "-") {
			if ("P".equals(objComputador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}

				// Caso o jogador acerte algo, será mostrado através do syso abaixo.
				System.out.println("Porta aviões atingido. Posição atingida: [" + letra + "," + coluna + "]");
				// Após, será atualizado no tabuleiro do computador "tabuleiroC" o objeto que
				// foi atingido para que fique visível
				// para o jogador.
				tabuleiroC[linha][coluna] = "P";
				// Tanto "objComputador" como "objJogador" servem apenas para comparar as
				// posições digitadas.
				// O tabuleiro que será exibido em cada uma das rodadas é apenas o do computador
				// "tabuleiroC".
				// Decidi que não teria razão ter que mostrar em cada rodada o tabuleiro do
				// jogador pois o jogador já sabe onde está
				// os seus objetos.
				objComputador[linha][coluna] = "-";
				pC--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}

		} else if ("D".equals(objComputador[linha][coluna]) || objComputador[linha][coluna] == "-") {
			if ("D".equals(objComputador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}
				System.out.println("Destroyer atingido. Posição atingida: [" + letra + "," + coluna + "]");
				tabuleiroC[linha][coluna] = "D";
				objComputador[linha][coluna] = "-";
				dC--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}
		} else if ("S".equals(objComputador[linha][coluna]) || objComputador[linha][coluna] == "-") {
			if ("S".equals(objComputador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}
				System.out.println("Submarino atingido. Posição atingida: [" + letra + "," + coluna + "]");
				tabuleiroC[linha][coluna] = "S";
				objComputador[linha][coluna] = "-";
				sC--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}
		} else if (objComputador[linha][coluna] == null) {
			System.out.println("Você acertou na água.");
		}
	}

	// Mesma lógica do método acima porém aqui é no tabuleiro do jogador, aquele que
	// será mostrado em cada uma das rodadas.
	// Ao invés de "-", aqui será utilizado o asterisco "*".
	public void jogadaComputador(int linha, int coluna) {
		if ("P".equals(objJogador[linha][coluna]) || objJogador[linha][coluna] == "*") {
			if ("P".equals(objJogador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}
				System.out.println("Porta aviões atingido. Posição atingida: [" + letra + "," + coluna + "]");
				objJogador[linha][coluna] = "*";
				pJ--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}
		} else if ("D".equals(objJogador[linha][coluna]) || objJogador[linha][coluna] == "*") {
			if ("D".equals(objJogador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}
				System.out.println("Destroyer atingido. Posição atingida: [" + letra + "," + coluna + "]");
				objJogador[linha][coluna] = "*";
				dJ--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}
		} else if ("S".equals(objJogador[linha][coluna]) || objJogador[linha][coluna] == "*") {
			if ("S".equals(objJogador[linha][coluna])) {
				String letra = "";
				if (linha == 0) {
					letra = "A";
				} else if (linha == 1) {
					letra = "B";
				} else if (linha == 2) {
					letra = "C";
				} else if (linha == 3) {
					letra = "D";
				} else {
					letra = "E";
				}
				System.out.println("Submarino atingido. Posição atingida: [" + letra + "," + coluna + "]");
				objJogador[linha][coluna] = "*";
				sJ--;
			} else {
				System.out.println("Este objeto já foi atingido.");
			}
		} else if (objJogador[linha][coluna] == "-") {
			System.out.println("Você acertou na água.");
		}
	}

	public String[][] getTabuleiroC() {
		return tabuleiroC;
	}

	public void setTabuleiroC(String[][] tabuleiroC) {
		this.tabuleiroC = tabuleiroC;
	}

	public int getpJ() {
		return pJ;
	}

	public void setpJ(int pJ) {
		this.pJ = pJ;
	}

	public int getdJ() {
		return dJ;
	}

	public void setdJ(int dJ) {
		this.dJ = dJ;
	}

	public int getsJ() {
		return sJ;
	}

	public void setsJ(int sJ) {
		this.sJ = sJ;
	}

	public int getpC() {
		return pC;
	}

	public void setpC(int pC) {
		this.pC = pC;
	}

	public int getdC() {
		return dC;
	}

	public void setdC(int dC) {
		this.dC = dC;
	}

	public int getsC() {
		return sC;
	}

	public void setsC(int sC) {
		this.sC = sC;
	}

}

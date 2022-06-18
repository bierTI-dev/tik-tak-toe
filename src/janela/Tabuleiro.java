package janela;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel implements MouseListener {
	//Defini��o das fontes
	Font fonte1 = new Font("Consolas", Font.BOLD, 80);
	Font fonte2 = new Font("Consolas", Font.BOLD, 30);
	Font fonte3 = new Font("Consolas", Font.BOLD, 15);
	
	//Declara��o de vari�veis
	int matriz[][];
	int jogador; //Vai ter a mesma fun��o que classe Jogador
	int ganhador;
	boolean jogarNov;
	int vencedor1; 
	int vencedor2;
	int empates;

	Color cor1;
	
	public Tabuleiro() { 
		//Definindo vari�veis
		matriz = new int[3][3];	
		jogador = 1;
		ganhador = 0;
		jogarNov = false;
		cor1 = new Color(0, 160, 0);
		vencedor1 = 0;
		vencedor2 = 0;
		empates = 0;
		
		//Loop FOR demarca��o de linhas e colunas
		for(int linha = 0; linha < 3; linha++) {

			for(int coluna = 0; coluna < 3; coluna++) {
				System.out.print(matriz[linha][coluna]);
			}
			System.out.println();
		}
	}

	@Override //Sobrescrita
	public void paintComponent(Graphics g2) { //Ambiente grafico
		Graphics2D g = (Graphics2D) g2.create();
		System.out.println();
		for(int linha = 0; linha < 3; linha++) { //Loop FOR ambiente gr�fico
			for(int coluna = 0; coluna < 3; coluna++) {
				System.out.print(matriz[linha][coluna]);
			}
			System.out.println();
		}
		if (jogarNov) { //Se selegionado que quer jogar novamente
			new JOptionPane();
			int jogarNovamente = JOptionPane.showConfirmDialog(this, "Quer jogar novamente ?");
			if (jogarNovamente == JOptionPane.OK_OPTION) {
				jogarNov = false;
				restart(); //Reinicia o jogo
			} else {
				System.exit(1); //Fecha o jogo
			}
		}

		//Defini��o de cores e linhas
		g.setStroke(new BasicStroke(5)); 
		g.setFont(fonte1); 			
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.blue);
		g.drawLine(0, 200, 600, 200);
		g.drawLine(0, 400, 600, 400);
		g.drawLine(200, 0, 200, 600);
		g.drawLine(400, 0, 400, 600);
		
		//Loop marca��o linhas e colunas
		for(int linha = 0; linha < 3; linha++) {			
			for(int coluna = 0; coluna < 3; coluna++) {
				if (matriz[linha][coluna] == 1) {
					g.setColor(cor1);
					g.drawString("o", 75 + coluna * 200, 125 + linha * 200);
				} else if (matriz[linha][coluna] == 2) {
					g.setColor(Color.RED);
					g.drawString("x", 75 + coluna * 200, 125 + linha * 200);
			}
		}		
	}			
		if (ganhador != 0) { //Se ganhador diferente de 0 
			g.setFont(fonte2); //Seta fonte 2 a "g"
			if(ganhador == 3) { //Se ganhador igual a 3
				g.setColor(Color.black); //Seta cor preta 
				g.drawString("O jogo empatou jogadores! ", 120, 220);				
			} else {
				if (ganhador == 1) //Se ganhador igual a1
					g.setColor(cor1); //seta cor 1
				else if (ganhador == 2) //se jogador igual a 2
					g.setColor(Color.red); //Seta cor vermelha						
				g.setFont(fonte2);
				g.drawString("O player " + ganhador + " venceu", 127, 225); //Resultado			
			}
			
			jogarNov = true; //Jogar novamente
			repaint(); //Reseta as cores
		}		
		g.setFont(fonte3);		
		g.setColor(cor1);
		g.drawString("Vit�rias " + vencedor1, 60, 20);		
		g.setColor(Color.red);
		g.drawString("Vit�rias " + vencedor2, 460, 20);
		g.setColor(Color.black);
		g.drawString("Empates: " + empates, 260, 20);
	}

	@Override
	public void mouseClicked(MouseEvent e) { //Eventos de clique
	//{ Lógica de clique nas linhas e colunas
		int y = e.getY() / 200;
		int x = e.getX() / 200;
		if (y ==3) {
			y = 2;
		} 
		if (x ==3) {
			x = 2;
		}
		System.out.println("Selecionou linha y: " + y);
		System.out.println("Selecionou linha x: " + x);		
		if (jogador == 1 && matriz [y][x] == 0) {		
			matriz[y][x] = 1;
			jogador = 2;
		} else if (jogador == 2 && matriz [y][x] == 0) {
			matriz[y][x] = 2;
			jogador = 1;
		}
	//}		
		validaGanhador(); //Valida quem ganhou		
		repaint(); 
	}
		
	private void restart() { //Reinicia o jogo		
		for(int linha = 0; linha < 3; linha++) {			
			for(int coluna = 0; coluna < 3; coluna++) {
				matriz[linha][coluna] = 0;
				ganhador = 0;
			}
		}	
	}
			
	private void validaGanhador() { //Valida quem ganhou		
		//verifica y
		for(int linha = 0; linha < 3; linha++) {
			if (matriz[linha][0] == matriz[linha][1] && matriz [linha][0] == matriz[linha][2] && matriz [linha][0] != 0) {
				System.out.println("Um jogador venceu");
				ganhador = matriz[linha][0];
				break;
			}
		}

		//verifica x
		for(int coluna = 0; coluna < 3; coluna++) {
			if (matriz[0][coluna] == matriz[1][coluna] && matriz [0][coluna] == matriz[2][coluna] && matriz [0][coluna] != 0) {
				System.out.println("Um jogador venceu");
				ganhador = matriz[0][coluna];
				break;
			}
		}
		if (matriz[0][0] == matriz [1][1] && matriz[0][0] == matriz [2][2]&& matriz [0][0] != 0) {
				System.out.println("Um jogador venceu");
				ganhador = matriz[0][0];

		}	
		if (matriz[0][2] == matriz [1][1] && matriz[0][2] == matriz [2][0]&& matriz [0][2] != 0) {
				System.out.println("Um jogador venceu");
				ganhador = matriz[0][2];
		}
	
		//Conta vitorias
		if (ganhador == 1) {
			vencedor1++; 
		} else if (ganhador == 2) {
			vencedor2++;
		} else {
		
			//Preencheu linhas
			boolean cheia = true;
			for(int linha = 0; linha < 3; linha++) {			
				for(int coluna = 0; coluna < 3; coluna++) {
					if(matriz[linha][coluna] == 0)
						cheia = false;
				}
			}

			if (cheia) {
				ganhador = 3;
				empates++;
			}
		}
	}		
//Eventos do mouse
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
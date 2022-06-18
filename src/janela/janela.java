package janela;
//VICTOR SILVA
//RU: 3529473

//Importando JFrame
import javax.swing.JFrame;



public class janela {	
	public static void main(String[] args){	
		//Interface (nome, fun��es) 
		JFrame frame = new JFrame("Jogo da Velha Uninter");
		frame.setSize(600, 630);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);		
		//Inicia novo jogo
		Tabuleiro velha = new Tabuleiro();
		velha.setBounds(0, 0, 600, 600);		
		frame.add(velha);		
		frame.addMouseListener(velha);
	}
}


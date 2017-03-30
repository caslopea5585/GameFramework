package game;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	GamePanel gamePanel;
	
	
	public GameWindow() {
		
		gamePanel = new GamePanel();
		add(gamePanel);
		
		//패널에 프로그래밍 적으로 포커스 올리기
		gamePanel.setFocusable(true);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}
	

	
	
}

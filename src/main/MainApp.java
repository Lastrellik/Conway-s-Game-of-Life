package src.main;

import java.awt.Color;
import javax.swing.*;

public class MainApp {
	private static JFrame frame;
	private static MainPanel mainPanel;

	public static void main(String[] args) {
		configureColors();
		Board gameBoard = new Board(100, 100);
		mainPanel = new MainPanel(gameBoard);
		GameController controller = new GameController(gameBoard);
		ControlPanel controlPanel = new ControlPanel(controller);
		mainPanel.setControlPanel(controlPanel);
		controller.initializeGame();
		buildGui(gameBoard, controlPanel);
		controller.startGame();
		
	}
	private static void buildGui(final Board board, ControlPanel controlPanel) {
		frame = new JFrame();
		frame.add(mainPanel);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void configureColors(){
		Cell.setAliveColor(Color.GREEN);
		Cell.setDeadColor(Color.BLACK);
		Cell.setOutlineColor(Color.BLACK);
	}

}

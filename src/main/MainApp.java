package main;

import java.awt.Color;

import javax.swing.*;

public class MainApp {

	public static void main(String[] args) {
		Cell.setAliveColor(Color.RED);
		Cell.setDeadColor(Color.BLACK);
		Cell.setOutlineColor(Color.BLACK);
		JFrame frame = new JFrame();
		Board board = new Board(100, 100);
		MainPanel mainPanel = new MainPanel();
		ControlPanel controlPanel = new ControlPanel();
		mainPanel.setBoard(board);
		mainPanel.setControlPanel(controlPanel);
		frame.add(mainPanel);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true){
			board.updateCells();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

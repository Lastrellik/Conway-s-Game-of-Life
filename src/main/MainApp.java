package main;

import java.awt.Color;

import javax.swing.*;

public class MainApp {

	public static void main(String[] args) {
		Cell.setAliveColor(Color.GREEN);
		Cell.setDeadColor(Color.BLACK);
		Cell.setOutlineColor(Color.BLACK);
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
				Color.MAGENTA, Color.cyan, Color.PINK, Color.GRAY, Color.WHITE
		};
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
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			//board.bringRandomCellsToLife(5);
			//Cell.setAliveColor(colors[(int)(Math.random() * colors.length)]);
			board.updateCells();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

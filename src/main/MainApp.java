package main;

import java.awt.Color;

import javax.swing.*;

public class MainApp {

	public static void main(String[] args) {
		Cell.setAliveColor(Color.BLACK);
		Cell.setDeadColor(Color.WHITE);
		Cell.setOutlineColor(Color.WHITE);
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
		board.bringOneCellToLife(55, 50);
		board.bringOneCellToLife(56, 51);
		board.bringOneCellToLife(57, 51);
		board.bringOneCellToLife(58, 50);
		board.bringOneCellToLife(57, 49);
		board.bringOneCellToLife(56, 49);
		board.bringOneCellToLife(59, 50);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		board.calculateAllNeighbors();
		while (true) {
			//board.bringRandomCellsToLife(5);
			//Cell.setAliveColor(colors[(int)(Math.random() * colors.length)]);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			board.updateActiveCells();
			if(board.getFrameCount() == 18) {
				board.bringOneCellToLife(50, 50);
				board.calculateAllNeighbors();
			}
		}
	}

}

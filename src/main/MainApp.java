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
		board.bringOneCellToLife(55, 50);
		board.bringOneCellToLife(56, 51);
		board.bringOneCellToLife(57, 51);
		board.bringOneCellToLife(58, 50);
		board.bringOneCellToLife(57, 49);
		board.bringOneCellToLife(56, 49);
		board.bringOneCellToLife(59, 50);
		board.calculateAllNeighbors();
		
		JFrame frame2 = new JFrame();
		Board board2 = new Board(100, 100);
		MainPanel mainPanel2 = new MainPanel();
		ControlPanel controlPanel2 = new ControlPanel();
		mainPanel2.setBoard(board2);
		mainPanel2.setControlPanel(controlPanel2);
		frame2.add(mainPanel2);
		frame2.setSize(1000, 1000);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setVisible(true);
		board2.bringOneCellToLife(55, 50);
		board2.bringOneCellToLife(56, 51);
		board2.bringOneCellToLife(57, 51);
		board2.bringOneCellToLife(58, 50);
		board2.bringOneCellToLife(57, 49);
		board2.bringOneCellToLife(56, 49);
		board2.bringOneCellToLife(59, 50);
		board2.calculateAllNeighbors();
		while (true) {
			//board.bringRandomCellsToLife(5);
			//Cell.setAliveColor(colors[(int)(Math.random() * colors.length)]);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			board.updateActiveCells();
			board2.updateActiveCells();
			if(board.getFrameCount() == 20) {
				board.bringOneCellToLife(50, 50);
				board.calculateAllNeighbors();
				board2.bringOneCellToLife(50, 50);
				board2.calculateAllNeighbors();
			}
			System.out.println(board.getNumOfActiveCells());
		}
	}

}

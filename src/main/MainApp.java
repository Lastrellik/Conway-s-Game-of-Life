package main;

import java.awt.Color;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class MainApp {
	private static JFrame frame;
	private static MainPanel mainPanel;
	private static ControlPanel controlPanel;
	private static Board boardA;
	private static Board boardB;

	public static void main(String[] args) {
		configureColors();
		boardA = new Board(100, 100);
		buildGui(boardA);
		bringCellsToLife(boardA);
		sleep(1000);
		while(true){
			mainPanel.setBoard(boardA);
			boardB = boardA.copyOfBoard();
			boardB.updateAllCells();
			sleep(200);
			mainPanel.setBoard(boardB);
			boardA = boardB.copyOfBoard();
			boardA.updateAllCells();
			sleep(200);
		}
		
	}

	private static void bringCellsToLife(final Board board) {
		board.bringOneCellToLife(55, 50);
		board.bringOneCellToLife(56, 51);
		board.bringOneCellToLife(57, 51);
		board.bringOneCellToLife(58, 50);
		board.bringOneCellToLife(57, 49);
		board.bringOneCellToLife(56, 49);
		board.bringOneCellToLife(59, 50);
		board.calculateAllNeighbors();
	}
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private static void buildGui(final Board board) {
		frame = new JFrame();
		mainPanel = new MainPanel(board);
		controlPanel = new ControlPanel();
		mainPanel.setControlPanel(controlPanel);
		frame.add(mainPanel);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void configureColors(){
		Cell.setAliveColor(Color.BLACK);
		Cell.setDeadColor(Color.WHITE);
		Cell.setOutlineColor(Color.WHITE);
		Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
				Color.MAGENTA, Color.cyan, Color.PINK, Color.GRAY, Color.WHITE
		};		
	}

}

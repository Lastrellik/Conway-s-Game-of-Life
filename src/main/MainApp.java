package main;

import java.awt.Color;

import javax.swing.*;

public class MainApp {

	public static void main(String[] args) {
		Cell.setAliveColor(Color.GREEN);
		Cell.setDeadColor(Color.GRAY);
		Cell.setOutlineColor(Color.BLACK);
		JFrame frame = new JFrame();
		Board board = new Board(50, 50);
		board.bringRandomCellsToLife(500);
		frame.add(board);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true){
			board.updateCells();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package main;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	private ControlPanel controlPanel;
	
	public MainPanel(Board board){
		setLayout(new BorderLayout());
		this.gameBoard = board;
		add(board, BorderLayout.CENTER);
	}

	public void setBoard(Board board){
		gameBoard = board;
		//add(board, BorderLayout.CENTER);
	}
	
	public Board getBoard(){
		return gameBoard;
	}
	
	public void setControlPanel(ControlPanel controlPanel){
		this.controlPanel = controlPanel;
		add(controlPanel, BorderLayout.SOUTH);
	}
	
	public ControlPanel getControlPanel(){
		return controlPanel;
	}
}

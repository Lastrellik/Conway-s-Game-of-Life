package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Cell extends JButton{
	private static final long serialVersionUID = 1L;
	private static Color aliveColor;
	private static Color deadColor;
	private static Color outlineColor;
	private boolean alive = false;
	private Color currentColor;
	private int aliveNeighbors = 0;
	private final int ROW, COL;
	private boolean bringToLifeOnMouseOver = false;
	
	public Cell(int row, int col){
		setBorder(new LineBorder(outlineColor));
		setText("");
		setFocusPainted(false);
		this.ROW = row;
		this.COL = col;
		buildActionListener();
		currentColor = Cell.deadColor;
	}
	
	public void bringToLife(){
		alive = true;
		currentColor = Cell.getAliveColor();
		setBackground(currentColor);
	}
	
	public void kill(){
		alive = false;
		currentColor = Cell.getDeadColor();
		setBackground(currentColor);
	}
	
	public void toggleLife(){
		if(alive) {
			kill();
		} else {
			bringToLife();
		}
		setBackground(currentColor);
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void updateCell(){
		if(isAlive() && getNumOfAliveNeighbors() < 2){
			kill();
		} else if (isAlive() && getNumOfAliveNeighbors() > 3){
			kill();
		} else if (!isAlive() && getNumOfAliveNeighbors() == 3){
			bringToLife();
		}
		setBackground(currentColor);
	}
	
	private void buildActionListener(){
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((Cell) arg0.getSource()).toggleLife();
			}
		});
		
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (!bringToLifeOnMouseOver) return;
				((Cell) arg0.getSource()).bringToLife();
			}
			
		});
			
	}
	
	
	public Color getCurrentColor(){
		return currentColor;
	}
	
	public int getNumOfAliveNeighbors() {
		return aliveNeighbors;
	}

	public void setNumOfAliveNeighbors(int aliveNeighbors) {
		int maxNumberOfNeighbors = 8;
		if(aliveNeighbors > maxNumberOfNeighbors || aliveNeighbors < 0){
			throw new IllegalArgumentException("invalid number of neighbors");
		}
		this.aliveNeighbors = aliveNeighbors;
	}

	public int getRow() {
		return ROW;
	}

	public int getCol() {
		return COL;
	}

	public static Color getAliveColor() {
		return aliveColor;
	}

	public static void setAliveColor(Color aliveColor) {
		Cell.aliveColor = aliveColor;
	}

	public static Color getDeadColor() {
		return deadColor;
	}

	public static void setDeadColor(Color deadColor) {
		Cell.deadColor = deadColor;
	}
	
	public static void setOutlineColor(Color outlineColor){
		Cell.outlineColor = outlineColor;
	}

	public static Color getOutlineColor(){
		return Cell.outlineColor;
	}

	public boolean isBringToLifeOnMouseOver() {
		return bringToLifeOnMouseOver;
	}

	public void setBringToLifeOnMouseOver(boolean bringToLifeOnMouseOver) {
		this.bringToLifeOnMouseOver = bringToLifeOnMouseOver;
	}
	
}

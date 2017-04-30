package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public Cell(){
		setBorder(new LineBorder(outlineColor));
		setText("");
		setFocusPainted(false);
		buildActionListener();
		currentColor = Cell.deadColor;
	}
	
	public void bringToLife(){
		alive = true;
		currentColor = Cell.getAliveColor();
	}
	
	public void kill(){
		alive = false;
		currentColor = Cell.getDeadColor();
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
}

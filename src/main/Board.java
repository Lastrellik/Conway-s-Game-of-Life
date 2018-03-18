package main;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private int boardWidthInCells;
	private int boardHeightInCells;
	private Cell[][] cells;
	private Set<Cell> aliveCells = new HashSet<Cell>();
	private Set<Cell> activeCells = new HashSet<Cell>();
	private int frameCount = 0;

	public Board(int boardWidth, int boardHeight) {
		setBoardWidthInCells(boardWidth);
		setBoardHeightInCells(boardHeight);
		setMetadata();
		buildCells();
	}

	private void setMetadata() {
		setLayout(new GridLayout(boardWidthInCells, boardHeightInCells, 0, 0));
	}

	private void buildCells() {
		cells = new Cell[boardWidthInCells][boardHeightInCells];
		for (int row = 0; row < boardHeightInCells; row++) {
			for (int col = 0; col < boardWidthInCells; col++) {
				cells[row][col] = new Cell(row, col);
				add(cells[row][col]);
				cells[row][col].updateCell();
			}
		}
	}

	public void bringRandomCellsToLife(int numOfCells) {
		int randomRowIndex, randomColIndex, currentNumOfAddedCells = 0;
		while (currentNumOfAddedCells < numOfCells) {
			randomRowIndex = (int) (Math.random() * boardWidthInCells);
			randomColIndex = (int) (Math.random() * boardHeightInCells);
			if (!cells[randomRowIndex][randomColIndex].isAlive()) {
				cells[randomRowIndex][randomColIndex].bringToLife();
				currentNumOfAddedCells++;
				processCellLifeChange(cells[randomRowIndex][randomColIndex]);
			}
		}
	}

	public void calculateAllNeighbors() {
		for (Cell[] row : cells) {
			for(Cell c : row) c.setNumOfAliveNeighbors(determineSingleCellNeighbors(c));
		}
	}

	private int determineSingleCellNeighbors(Cell cell) {
		int numOfAliveNeighbors = 0;
		for(Cell c : getListOfNeighbors(cell)){
			if(c.isAlive()) numOfAliveNeighbors++;
		}
		return numOfAliveNeighbors;
	}

	public HashSet<Cell> getListOfNeighbors(Cell cell) {
		HashSet<Cell> neighbors = new HashSet<Cell>();
		int row = cell.getRow(), column = cell.getCol();
		for (int i = row - 1; i <= row + 1; i++) {
			if (i < 0 || i >= boardHeightInCells)
				continue;
			for (int j = column - 1; j <= column + 1; j++) {
				if (j < 0 || j >= boardWidthInCells)
					continue;
				if (!(i == row && j == column))
					neighbors.add(cells[i][j]);
			}
		}
		return neighbors;
	}

	public void bringOneCellToLife(int row, int column) {
		cells[row][column].bringToLife();
		aliveCells.add(cells[row][column]);
		activeCells.addAll(getListOfNeighbors(cells[row][column]));
	}

	public void updateAllCells() {
		calculateAllNeighbors();
		for (int row = 0; row < boardWidthInCells; row++) {
			for (int col = 0; col < boardHeightInCells; col++) {
				cells[row][col].updateCell();
				processCellLifeChange(cells[row][col]);
			}
		}
		frameCount++;
		repaint();
	}
	
	public void updateActiveCells(){
		System.out.println(this.activeCells.size());
		for(Cell c : activeCells){
			c.setNumOfAliveNeighbors(determineSingleCellNeighbors(c));
		}
		for(Cell c : activeCells){
			c.updateCell();
			processCellLifeChange(c);
		}
		activeCells.clear();
		for(Cell d : aliveCells){
			activeCells.addAll(getListOfNeighbors(d));
			activeCells.add(d);
		}
		frameCount++;
	}
	
	private void processCellLifeChange(Cell cell){
		if(cell.isAlive()) {
			aliveCells.add(cell);
		} else {
			aliveCells.remove(cell);
		}
	}
	
	public Board copyOfBoard(){
		Board newBoard = new Board(boardWidthInCells, boardHeightInCells);
		newBoard.setCells(this.getCells());
		for (Cell c : aliveCells){
			newBoard.bringOneCellToLife(c.getRow(), c.getCol());
		}
		return newBoard;
	}
	
	public int getNumOfActiveCells(){
		return activeCells.size();
	}

	public Cell cellAt(int row, int column) {
		return cells[row][column];
	}

	public int getFrameCount() {
		return frameCount;
	}

	public int getNumOfAliveCells() {
		return aliveCells.size();
	}

	public int getBoardWidthInCells() {
		return boardWidthInCells;
	}

	public int getBoardHeightInCells() {
		return boardHeightInCells;
	}

	private void setBoardWidthInCells(int boardWidthInCells) {
		if (boardWidthInCells < 0)
			throw new IllegalArgumentException("board width cannot be negative");
		this.boardWidthInCells = boardWidthInCells;
	}

	private void setBoardHeightInCells(int boardHeightInCells) {
		if (boardHeightInCells < 0)
			throw new IllegalArgumentException("board height cannot be negative");
		this.boardHeightInCells = boardHeightInCells;
	}

	public Set<Cell> getAliveCells() {
		return new HashSet<Cell>(aliveCells);
	}

	public void setAliveCells(Set<Cell> aliveCells) {
		this.aliveCells = aliveCells;
	}

	public Set<Cell> getActiveCells() {
		return new HashSet<Cell>(activeCells);
	}

	public void setActiveCells(Set<Cell> activeCells) {
		this.activeCells = activeCells;
	}

	private Cell[][] getCells() {
		return cells.clone();
	}

	private void setCells(Cell[][] cells) {
		this.cells = cells;
	}	
	
}

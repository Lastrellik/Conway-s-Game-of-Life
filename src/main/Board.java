package main;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private int boardWidthInCells;
	private int boardHeightInCells;
	private Cell[][] cells;
	private Set<Cell> aliveCellsAndTheirNeighbors = new HashSet<Cell>();
	private int numOfAliveCells;
	private int frameCount = 0;

	public Board(int boardWidth, int boardHeight) {
		setBoardWidthInCells(boardWidth);
		setBoardHeightInCells(boardHeight);
		numOfAliveCells = 0;
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
		int randomRowIndex;
		int randomColIndex;
		int currentNumOfAddedCells = 0;
		while (currentNumOfAddedCells < numOfCells) {
			randomRowIndex = (int) (Math.random() * boardWidthInCells);
			randomColIndex = (int) (Math.random() * boardHeightInCells);
			if (!cells[randomRowIndex][randomColIndex].isAlive()) {
				cells[randomRowIndex][randomColIndex].bringToLife();
				currentNumOfAddedCells++;
				numOfAliveCells++;
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
		int row = cell.getRow();
		int column = cell.getCol();
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
		numOfAliveCells++;
	}

	public void updateCells() {
		calculateAllNeighbors();
		for (int row = 0; row < boardWidthInCells; row++) {
			for (int col = 0; col < boardHeightInCells; col++) {
				cells[row][col].updateCell();
			}
		}
		frameCount++;
	}

	public Cell cellAt(int row, int column) {
		return cells[row][column];
	}

	public int getFrameCount() {
		return frameCount;
	}

	public int getNumOfAliveCells() {
		return numOfAliveCells;
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
}

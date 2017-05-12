package tests;

import static org.junit.Assert.*;
import java.awt.GridLayout;

import org.junit.*;
import org.junit.rules.*;
import main.*;

public class BoardTest {
	private final int WIDTH_IN_CELLS = 100;
	private final int HEIGHT_IN_CELLS = 100;
	Board defaultBoard = new Board(WIDTH_IN_CELLS, HEIGHT_IN_CELLS);
	Board boardToTestNeighbors = new Board(WIDTH_IN_CELLS, HEIGHT_IN_CELLS);
	
	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void testConstructor() {
		assertEquals(WIDTH_IN_CELLS, defaultBoard.getBoardWidthInCells());
	}
	
	@Test
	public void testSetMetadata(){
		assertTrue(defaultBoard.getLayout() instanceof GridLayout);
	}
	
	@Test
	public void testBuildCells(){
		assertEquals(0, defaultBoard.getNumOfAliveCells());
	}
	
	@Test
	public void testBringRandomCellsToLife(){
		Board boardWithRandomCells = new Board(100, 100);
		boardWithRandomCells.bringRandomCellsToLife(25);
		assertEquals(25, boardWithRandomCells.getNumOfAliveCells());
	}
	
	@Test
	public void testBringOneCellToLife(){
		Board boardOneCellToLife = new Board(10, 10);
		boardOneCellToLife.bringOneCellToLife(0, 0);
		assertEquals(1, boardOneCellToLife.getNumOfAliveCells());
	}
	
	@Test
	public void testCellAt(){
		Board boardToTestCellAt = new Board(10, 10);
		boardToTestCellAt.bringOneCellToLife(1, 1);
		assertTrue(boardToTestCellAt.cellAt(1, 1).isAlive());
	}
	
	@Test
	public void testCalculateAllNeighbors(){
		boardToTestNeighbors.bringOneCellToLife(50, 50);
		boardToTestNeighbors.calculateAllNeighbors();
		assertEquals(1, boardToTestNeighbors.cellAt(49, 49).getNumOfAliveNeighbors());
	}
	
	@Test
	public void testCalculateAllNeighbors_topEdgeOfBoard(){
		boardToTestNeighbors.bringOneCellToLife(0, 50);
		boardToTestNeighbors.calculateAllNeighbors();
		assertEquals(1, boardToTestNeighbors.cellAt(0, 49).getNumOfAliveNeighbors());
	}
	
	@Test
	public void testCalculateAllNeighbors_bottomEdgeOfBoard(){
		boardToTestNeighbors.bringOneCellToLife(HEIGHT_IN_CELLS - 1, 50);
		boardToTestNeighbors.calculateAllNeighbors();
		assertEquals(1, boardToTestNeighbors.cellAt(HEIGHT_IN_CELLS - 1, 49).getNumOfAliveNeighbors());
	}
	
	@Test
	public void testCalculateAllNeighbors_rightEdgeOfBoard(){
		boardToTestNeighbors.bringOneCellToLife(50, WIDTH_IN_CELLS - 1);
		boardToTestNeighbors.calculateAllNeighbors();
		assertEquals(1, boardToTestNeighbors.cellAt(49, WIDTH_IN_CELLS - 1).getNumOfAliveNeighbors());
	}
	
	@Test
	public void testCalculateAllNeighbors_leftEdgeOfBoard(){
		boardToTestNeighbors.bringOneCellToLife(50, 0);
		boardToTestNeighbors.calculateAllNeighbors();
		assertEquals(1, boardToTestNeighbors.cellAt(49, 0).getNumOfAliveNeighbors());
	}
	
	@Test
	public void testGetListOfNeighbors(){
		Board testGetListOfNeighbors = new Board(10,10);
		Cell cellToTest = testGetListOfNeighbors.cellAt(5, 5);
		assertEquals(8, testGetListOfNeighbors.getListOfNeighbors(cellToTest).size());
	}
	
	@Test
	public void testGetFrameCount(){
		Board testGetFrameCountBoard = new Board(10, 10);
		testGetFrameCountBoard.updateAllCells();
		assertEquals(1, testGetFrameCountBoard.getFrameCount());
	}
	
	@Test
	public void testUpdateAllCells(){
		Board boardToUpdateAllCells = new Board(10, 10);
		boardToUpdateAllCells.updateAllCells();
		assertEquals(1, boardToUpdateAllCells.getFrameCount());
	}
	
	@Test
	public void testUpdateActiveCells(){
		Board boardToUpdateActiveCells = new Board(10, 10);
		boardToUpdateActiveCells.bringOneCellToLife(0, 0);
		boardToUpdateActiveCells.bringOneCellToLife(0, 1);
		boardToUpdateActiveCells.bringOneCellToLife(0, 2);
		boardToUpdateActiveCells.updateActiveCells();
		assertEquals(8, boardToUpdateActiveCells.getNumOfActiveCells());
	}
	
	@Test
	public void testGetNumOfActiveCells(){
		Board boardToTestGetNumOfActiveCells = new Board(10, 10);
		boardToTestGetNumOfActiveCells.bringOneCellToLife(0, 0);
		boardToTestGetNumOfActiveCells.bringOneCellToLife(0, 1);
		boardToTestGetNumOfActiveCells.bringOneCellToLife(0, 2);
		assertEquals(8, boardToTestGetNumOfActiveCells.getNumOfActiveCells());
	}
	
	@Test
	public void testGetNumOfAliveCells(){
		assertEquals(0, defaultBoard.getNumOfAliveCells());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructor_negativeWidth() {
		ex.expectMessage("board width cannot be negative");
		Board boardNegativeWidth = new Board(-10, 10);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructor_negativeHeight() {
		ex.expectMessage("board height cannot be negative");
		Board boardNegativeHeight = new Board(10, -10);
	}
	
	@Test
	public void testProcessCellLifeChange(){
		Board processCellLifeChangeBoard = new Board(10, 10);
		processCellLifeChangeBoard.bringOneCellToLife(5, 5);
		assertEquals(1, processCellLifeChangeBoard.getNumOfAliveCells());		
	}
}

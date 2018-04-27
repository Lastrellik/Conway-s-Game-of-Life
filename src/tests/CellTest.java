package src.tests;

import static org.junit.Assert.*;
import java.awt.Color;

import org.junit.*;
import org.junit.rules.ExpectedException;

import src.main.Cell;

public class CellTest {
	Cell defaultCell = new Cell(5, 5);
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Cell.setAliveColor(Color.GREEN);
		Cell.setDeadColor(Color.GRAY);
		Cell.setOutlineColor(Color.BLACK);
	}
	
	@Test
	public void testConstructor() {
		assertFalse(defaultCell.isAlive());
	}
	
	@Test
	public void testBringToLife(){
		Cell cellToBringToLife = new Cell(0, 0);
		cellToBringToLife.bringToLife();
		assertTrue(cellToBringToLife.isAlive());
	}
	
	@Test
	public void testKill(){
		Cell cellToKill = new Cell(0, 0);
		cellToKill.kill();
		assertFalse(cellToKill.isAlive() && cellToKill.getCurrentColor() == Cell.getDeadColor());
	}
	
	@Test
	public void testToggleLife(){
		boolean isAlive = defaultCell.isAlive();
		defaultCell.toggleLife();
		assertTrue(defaultCell.isAlive() != isAlive);
	}
	
	@Test
	public void testSetAliveColor(){
		Cell.setAliveColor(Color.GREEN);
		assertEquals(Cell.getAliveColor(), Color.GREEN);
	}
	
	@Test
	public void testSetDeadColor(){
		Cell.setDeadColor(Color.BLACK);
		assertEquals(Cell.getDeadColor(), Color.BLACK);
	}
	
	@Test
	public void testGetAliveColor(){
		Cell.setAliveColor(Color.BLUE);
		assertEquals(Color.BLUE, Cell.getAliveColor());
	}
	
	@Test
	public void testGetDeadColor(){
		Cell.setDeadColor(Color.ORANGE);
		assertEquals(Color.ORANGE, Cell.getDeadColor());
	}
	
	@Test
	public void testSetOutlineColor(){
		Cell.setOutlineColor(Color.BLACK);
		assertEquals(Color.BLACK, Cell.getOutlineColor());
	}
	
	@Test
	public void testGetOutlineColor(){
		Cell.setOutlineColor(Color.RED);
		assertEquals(Color.RED, Cell.getOutlineColor());
	}

	@Test
	public void testUpdateCell(){
		defaultCell.kill();
		defaultCell.updateCell();
		assertEquals(Cell.getDeadColor(), defaultCell.getCurrentColor());
	}
	
	@Test
	public void testGetCurrentColor(){
		Cell cellToTestGetColor = new Cell(0, 0);
		cellToTestGetColor.bringToLife();
		assertEquals(Cell.getAliveColor(), cellToTestGetColor.getCurrentColor());
	}
	
	@Test
	public void testSetNumOfAliveNeighbors(){
		defaultCell.setNumOfAliveNeighbors(2);
		assertEquals(2, defaultCell.getNumOfAliveNeighbors());
	}
	
	@Test
	public void testSetNumOfAliveNeighbors_tooHigh(){
		ex.expectMessage("invalid number of neighbors");
		defaultCell.setNumOfAliveNeighbors(9);
	}
	
	@Test
	public void testSetNumOfAliveNeighbors_tooLow(){
		ex.expectMessage("invalid number of neighbors");
		defaultCell.setNumOfAliveNeighbors(-5);
	}
	
	@Test
	public void testGetRow(){
		assertTrue(defaultCell.getRow() == 5);
	}
	
	@Test
	public void testGetCol(){
		assertTrue(defaultCell.getCol() == 5);
	}
	
	@Test
	public void testAddActionListener(){
		assertTrue(defaultCell.getActionListeners().length != 0);
	}
}

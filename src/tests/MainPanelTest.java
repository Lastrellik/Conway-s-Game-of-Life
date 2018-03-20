package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import main.*;

public class MainPanelTest {

	@Test
	public void testConstructor() {
		Board testBoard = new Board(100, 100);
		MainPanel mainPanel = new MainPanel(testBoard);
		assertEquals(testBoard, mainPanel.getBoard());
	}

	@Test
	public void testGetBoard(){
		Board testBoard = new Board(100, 100);
		MainPanel mainPanel = new MainPanel(testBoard);
		assertEquals(testBoard, mainPanel.getBoard());
	}
	
	@Test
	public void testSetControlPanel(){
		MainPanel mainPanel = new MainPanel(new Board(100, 100));
		ControlPanel expected = new ControlPanel(null);
		mainPanel.setControlPanel(expected);
		assertEquals(expected, mainPanel.getControlPanel());
	}
	
	@Test
	public void testGetControlPanel(){
		MainPanel mainPanel = new MainPanel(new Board(100, 100));
		ControlPanel expected = new ControlPanel(null);
		mainPanel.setControlPanel(expected);
		assertEquals(expected, mainPanel.getControlPanel());
		
	}
}

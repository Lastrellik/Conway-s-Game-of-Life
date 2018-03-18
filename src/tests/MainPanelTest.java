package tests;

import static org.junit.Assert.*;

import java.awt.BorderLayout;

import org.junit.Test;
import main.*;

public class MainPanelTest {
	MainPanel defaultMainPanel = new MainPanel(new Board(100,100));
	BorderLayout defaultPanelLayout = (BorderLayout) defaultMainPanel.getLayout();
	ControlPanel defaultControlPanel = new ControlPanel();

	@Test
	public void testConstructor() {
		MainPanel mainPanel = new MainPanel(new Board(100, 100));
		assertTrue(mainPanel.getLayout() instanceof BorderLayout);
	}

	@Test
	public void testGetBoard(){
		Board testGetBoardBoard = new Board(10, 10);
		defaultMainPanel.setBoard(testGetBoardBoard);
		assertEquals(testGetBoardBoard, defaultMainPanel.getBoard());
	}
	
	@Test
	public void testSetControlPanel(){
		defaultMainPanel.setControlPanel(defaultControlPanel);
		assertEquals(defaultControlPanel, defaultPanelLayout.getLayoutComponent(BorderLayout.SOUTH));
	}
	
	@Test
	public void testGetControlPanel(){
		defaultMainPanel.setControlPanel(defaultControlPanel);
		assertEquals(defaultControlPanel, defaultMainPanel.getControlPanel());
	}
}

package tests;

import static org.junit.Assert.*;
import main.*;

import org.junit.Test;

public class GameControllerTest {

	@Test
	public void testInitializeGame() {
		Board gameBoard = new Board(100, 100);
		GameController controller = new GameController(gameBoard);
		controller.initializeGame();
		assertTrue(gameBoard.getCells() != null);
	}
	
	@Test
	public void testPauseGame(){
		Board gameBoard = new Board(100, 100);
		GameController controller = new GameController(gameBoard);
		controller.pauseGame();
		assertTrue(controller.isPaused());
	}
	
	@Test
	public void testResumeGame(){
		Board gameBoard = new Board(100, 100);
		GameController controller = new GameController(gameBoard);
		controller.pauseGame();
		controller.resumeGame();
		assertFalse(controller.isPaused());		
	}
	
	@Test
	public void testSetTimeBetweenFrames(){
		Board gameBoard = new Board(100, 100);
		GameController controller = new GameController(gameBoard);
		controller.setTimeBetweenFrames(100);
		assertEquals(100, controller.getTimeBetweenFrames());		
	}
	
	@Test
	public void testGetTimeBetweenFrames(){
		Board gameBoard = new Board(100, 100);
		GameController controller = new GameController(gameBoard);
		controller.setTimeBetweenFrames(100);
		assertEquals(100, controller.getTimeBetweenFrames());		
	}

}

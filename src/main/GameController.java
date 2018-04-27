package src.main;

import java.awt.Toolkit;

public class GameController {
	private Board gameBoardA;
	private Board gameBoardB;
	private int timeBetweenFrames;
	private boolean isPaused = true;
	
	public GameController(Board gameBoard){
		this.gameBoardA = gameBoard;
		this.gameBoardB = gameBoard;
		timeBetweenFrames = 200;
	}
	
	public void initializeGame(){
		gameBoardA.buildCells();
		
	}
	
	public void startGame(){
		while(true){
			while(isPaused){
				sleep(100);
			}
			gameBoardB = gameBoardA;
			gameBoardB.updateAllCells();
			Toolkit.getDefaultToolkit().sync();
			gameBoardB.repaint();
			sleep(timeBetweenFrames);
			while(isPaused){
				sleep(100);
			}
			gameBoardA = gameBoardB;
			gameBoardA.updateAllCells();
			gameBoardA.repaint();
			Toolkit.getDefaultToolkit().sync();
			sleep(timeBetweenFrames);
		}		
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void pauseGame(){
		isPaused = true;
	}
	
	public void resumeGame(){
		isPaused = false;
	}
	
	public void setTimeBetweenFrames(int timeBetweenFrames){
		this.timeBetweenFrames = timeBetweenFrames;
	}
	
	public int getTimeBetweenFrames(){
		return timeBetweenFrames;
	}
	
	public boolean isPaused(){
		return isPaused;
	}
	
	public void resetGame(){
		gameBoardA.resetBoard();
		gameBoardB.resetBoard();
	}

	public void enableMouseOver() {
		gameBoardA.enableMouseOver();
		gameBoardB.enableMouseOver();
	}

	public void disableMouseOver() {
		gameBoardA.disableMouseOver();
		gameBoardB.disableMouseOver();		
	}

}

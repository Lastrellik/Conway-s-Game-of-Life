package tests;

import static org.junit.Assert.*;

import java.awt.*;
import org.junit.*;
import main.*;

public class ControlPanelTest {
	ControlPanel defaultControlPanel = new ControlPanel();
	FlowLayout defaultLayout = (FlowLayout) defaultControlPanel.getLayout();
	
	@Test
	public void testConstructor(){
		assertTrue(defaultControlPanel.getLayout() instanceof FlowLayout);
	}
	
	@Test
	public void testAddStartButton(){
		//TODO: Figure out start button //defaultControlPanel.addStartButton();
	}
}

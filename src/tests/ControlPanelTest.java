package src.tests;

import static org.junit.Assert.*;

import java.awt.*;
import org.junit.*;
import src.main.*;

public class ControlPanelTest {
	
	@Test
	public void testConstructor(){
		ControlPanel defaultControlPanel = new ControlPanel(new GameController(null));
		assertTrue(defaultControlPanel.getLayout() instanceof FlowLayout);
	}
}

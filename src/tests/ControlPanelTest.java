package tests;

import static org.junit.Assert.*;

import java.awt.*;
import org.junit.*;
import main.*;

public class ControlPanelTest {
	ControlPanel defaultControlPanel = new ControlPanel();
	
	@Test
	public void testConstructor(){
		assertTrue(defaultControlPanel.getLayout() instanceof FlowLayout);
	}
}

package src.main;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

public class ControlPanel extends JPanel {
	private GameController gameController;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ControlPanel(final GameController gameController) {
		this.gameController = gameController;
		
		final JButton btnStartPause = new JButton("Start");
		btnStartPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(gameController.isPaused()){
					gameController.resumeGame();
					btnStartPause.setText("Pause");
				} else {
					gameController.pauseGame();
					btnStartPause.setText("Resume");
				}
			}
		});
		add(btnStartPause);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameController.resetGame();
			}
		});
		add(btnReset);
		
		JLabel lblSpeed = new JLabel("Speed: ");
		add(lblSpeed);
		
		final JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				gameController.setTimeBetweenFrames(450 - 4 * slider.getValue());
			}
		});
		add(slider);
		
		final JCheckBox chckbxMouseOver = new JCheckBox("Mouse Over Brings Cell to Life");
		chckbxMouseOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxMouseOver.isSelected()) gameController.enableMouseOver();
				else gameController.disableMouseOver();
			}
		});
		add(chckbxMouseOver);

	}

}

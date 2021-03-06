package application;


import application.events.SlideEvent;
import events.EventControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Enifs.
 */

public class Launcher
{
	public static void main(String[] args)
	{
		Launcher launcher = new Launcher();
		launcher.run();
	}

	private void run()
	{
		this.ui = new MainUI();
		JFrame frame = new JFrame("TBT");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(this.ui.getRootPanel());
		frame.setVisible(true);
		frame.setSize(450, 800);

		field = (Field) ui.playground;
		nextPanel = (NextPanel) ui.nextPanel;
		field.nextPanel = nextPanel;
		ui.scoreBoard.field = field;

		Polyomino poly = field.spawnPolyomino();

//		new SpawnPolyominoEvent(field).fire();

		nextPanel.setNextPolyomino(PolyominoFactory.produceRandomPolyomino(field));


		//setting up scoreBoard
		field.scoreBoard = ui.scoreBoard;

		Timer timer = new Timer(1000/60, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				field.repaint();
				nextPanel.repaint();
				ui.scoreBoard.main.repaint();
			}
		});

		timer.setRepeats(true);
		timer.start();

		new EventControlPanel();

		field.slideEvent = new SlideEvent(1000, poly);
		EventControlPanel.registerRegularEvent(field.slideEvent);

		field.keys = new Keys(poly);
		ui.getRootPanel().addKeyListener(field.keys);
		ui.getRootPanel().grabFocus();
	}

	Field field;
	NextPanel nextPanel;
	MainUI ui;
}

/**
 * Created by Enifs.
 */

package ui;

import javax.swing.*;


public class MainUI
{

	public JPanel getRootPanel()
	{
		return rootPanel;
	}

	private JPanel rootPanel;

	public JPanel playground;


	private void createUIComponents()
	{
		this.playground = new Field();
	}
}

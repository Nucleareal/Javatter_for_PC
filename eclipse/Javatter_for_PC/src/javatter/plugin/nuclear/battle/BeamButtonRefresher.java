package javatter.plugin.nuclear.battle;

import java.awt.Color;

import javax.swing.JButton;

public class BeamButtonRefresher
{
	private JButton _button;

	public BeamButtonRefresher(JButton b)
	{
		_button = b;
	}

	public void changeColor()
	{
		Color color = BeamStatus.get().getColor();
		_button.setBackground(color);
	}
}

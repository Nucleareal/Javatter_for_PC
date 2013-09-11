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

	public void refreshButton()
	{
		Color color = BeamStatus.get().getColor();
		Color fcolor = color.getRed() > 127 ? Color.black : Color.white;
		_button.setText(BeamStatus.get().getText());
		_button.setForeground(fcolor);
		_button.setBackground(color);
	}
}

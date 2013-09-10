package javatter.plugin.nuclear;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.orekyuu.javatter.util.SaveData;

public class Builder_Button extends Builder_Base
{
	private JButton _button;
	private SaveData _data;

	public Builder_Button(SaveData data)
	{
		_data = data;
	}

	public JButton create(String msg, final String name, ActionListener[] listeners)
	{
		_button = new JButton();
		_button.setText(msg);
		for(ActionListener e : listeners)
		{
			_button.addActionListener(e);
		}
		return _button;
	}
}

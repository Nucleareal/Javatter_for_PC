package javatter.plugin.nuclear;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.orekyuu.javatter.util.SaveData;

public class Builder_Label extends Builder_Base
{
	private SaveData _data;

	public Builder_Label(SaveData data)
	{
		_data = data;
	}

	@Override
	public JLabel create(String msg, String name, ActionListener[] listeners)
	{
		JLabel lb = new JLabel(msg);
		for(ActionListener al : listeners)
		{
		}
		return lb;
	}

	public JLabel create(String msg, final String name)
	{
		return create(msg, name, new ActionListener[]{});
	}
}

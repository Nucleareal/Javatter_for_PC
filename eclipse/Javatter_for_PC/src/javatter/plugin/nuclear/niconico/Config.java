package javatter.plugin.nuclear.niconico;

import java.awt.Component;

import javatter.plugin.nuclear.Builder_Label;
import javatter.plugin.nuclear.Builder_TextField;
import javatter.plugin.nuclear.ConfigAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.orekyuu.javatter.util.SaveData;

public class Config extends ConfigAdapter
{
	private SaveData _data;

	public Config(SaveData data)
	{
		super(data);
		_data = data;
	}

	@Override
	public Component getComponent()
	{
		JLabel pane = new Builder_Label(_data).create("ツイートのスクロールする速さ", "");

		JTextField speed = new Builder_TextField(_data).create(_data.getString("ScrollSpeed"), "ScrollSpeed");

		return queuing(pane, speed);
	}
}

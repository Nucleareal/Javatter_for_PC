package javatter.plugin.nuclear.brainfuck;

import java.awt.BorderLayout;
import java.awt.Component;

import javatter.plugin.nuclear.ConfigAdapter;

import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;

public class BrainfuckConfig extends ConfigAdapter
{
	public BrainfuckConfig(SaveData data)
	{
		super(data);
	}

	@Override
	public Component getComponent()
	{
		return new JPanel(new BorderLayout());
	}
}

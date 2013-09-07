package javatter.plugin.nuclear.brainfuck;

import javatter.plugin.nuclear.PluginModelAdapter;
import javatter.plugin.nuclear.StatusUtils;

import javax.swing.JOptionPane;

import twitter4j.Status;

public class BrainfuckModel extends PluginModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		String text = StatusUtils.removeMentions(status);
		if(text.startsWith("+"))
		{
			JOptionPane.showMessageDialog(null, BrainfuckInter.get().run(text));
		}
	}
}

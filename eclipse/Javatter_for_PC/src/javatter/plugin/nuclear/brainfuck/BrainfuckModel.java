package javatter.plugin.nuclear.brainfuck;

import javatter.plugin.nuclear.ModelAdapter;
import javatter.plugin.nuclear.StatusUtils;

import javax.swing.JOptionPane;

import twitter4j.Status;

public class BrainfuckModel extends ModelAdapter
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

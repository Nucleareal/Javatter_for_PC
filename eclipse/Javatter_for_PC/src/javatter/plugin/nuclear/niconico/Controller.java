package javatter.plugin.nuclear.niconico;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import twitter4j.Status;

public class Controller
{
	private static NicoFrame _frame;

	public static void initialize()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		_frame = new NicoFrame("", 0);
		_frame.setSize(dim);
		_frame.setVisible(true);
	}

	public static void onStatus(Status s)
	{
		_frame.onStatus(s);
	}

	public static void repaint()
	{
		_frame.repaint();
	}
}

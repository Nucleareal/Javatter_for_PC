package javatter.plugin.nuclear.niconico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import twitter4j.Status;

public class NicoFrame extends JFrame
{
	private static final Color transColor = new Color(0,0,0,0);
	private static GraphicsDevice[] _ary;
	private NicoPane _pane;

	public NicoFrame(String title, int screenNo)
	{
		super(title);
		setUndecorated(true);
		setAlwaysOnTop(true);
		getRootPane().setDoubleBuffered(true);
		((JComponent)getContentPane()).setDoubleBuffered(true);

		setBackground(transColor);
		getContentPane().setBackground(transColor);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		_ary = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(_pane = new NicoPane());
	}

	public void onStatus(Status s)
	{
		_pane.onStatus(s);
	}
}

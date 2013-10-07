package javatter.plugin.nuclear.battle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JavaBattleScreen extends JFrame
{
	private Dimension dim = new Dimension(640, 480);
	private Image buffer;
	private Graphics bg;

	private String MyName;
	private String EnName;
	private ImageIcon MyImg;
	private ImageIcon EnImg;
	private int counter;
	private ActionAndFinishableListener _handler;

	public JavaBattleScreen(String myName, String enName, ImageIcon myImg, ImageIcon enImg, ActionAndFinishableListener handler)
	{
		counter = 0;
		MyName = myName;
		EnName = enName;
		MyImg = myImg;
		EnImg = enImg;
		_handler = handler;
		buffer = createImage(dim.width, dim.height);
		setSize(dim);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void run() throws Exception
	{
		repaint();
		Thread.sleep(10);
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	@Override
	public void paint(Graphics g)
	{
		if(buffer == null) buffer = createImage(dim.width, dim.height);
		bg = buffer.getGraphics();

		Drawing d = Drawing.None;

		if(counter < 100) d = Drawing.PreBattle;
		else if(counter < 200) d = Drawing.Beaming;
		else if(counter < 300) d = Drawing.Result;

		DrawingResult res = d.Paint(bg, MyImg, EnImg, MyName, EnName, dim, this);

		g.drawImage(buffer, 0, 0, this);

		counter++;

		switch(res)
		{
			case Action:
			case Finish:
				ResultedThread t = new ResultedThread(_handler, res);
				t.start();
			default:
				break;
		}
	}
}

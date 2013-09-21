package javatter.plugin.nuclear.niconico;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

import twitter4j.Status;

public class NicoPane extends JPanel
{
	private BlockingQueue<Comment> _list;

	public NicoPane()
	{
		super();
		_list = new ArrayBlockingQueue<>(100, true);
	}

	@Override
	public void paint(Graphics g)
	{
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Iterator<Comment> im = _list.iterator();
		for(int i = 0; i < _list.size(); i++)
		{
			Comment m = im.next();
			m.visit(this, i);
		}
		for(Comment m : _list)
		{
			m.paint(this, g);
		}
	}

	public void destroy(Comment comment)
	{
		_list.remove(comment);
	}

	public void onStatus(Status s)
	{
		_list.add(new Comment(s, _list.size()));
	}
}

package javatter.plugin.nuclear.niconico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javatter.plugin.nuclear.TwitterUtils;
import javatter.plugin.nuclear.URLUtil;

import javax.swing.ImageIcon;

import twitter4j.Status;

import com.orekyuu.javatter.util.IconCache;

public class Comment
{
	public static int DX = -4;
	public static int DY = 0;
	public static int DEFAULT_DX = -4;
	public static int DISTANCE_Y = 50;

	private static int SX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int _line;
	private int _x;
	private int _y;
	private String _comment = "This is not good for the niconico.";
	private int _length = 32;
	private ImageIcon _img;
	private boolean _isMyStatus;

	public Comment(Status s, int line)
	{
		_line = line;
		_x = SX;
		_y = 52 + DISTANCE_Y*_line;
		_comment = s.getUser().getScreenName()+": "+s.getText();
		_length = _comment.length();
		_img = IconCache.getInstance().getIcon(URLUtil.create(s.getUser().getProfileImageURL()));

		_isMyStatus = s.getUser().getId() == TwitterUtils.getUserId();
	}

	public void visit(NicoPane pane, int line)
	{
		if(line < _line)
		{
			_y -= (_line - line)*DISTANCE_Y;
			_line = line;
		}
		_x += DX;
		_y += DY;
		if(_x < -_length*32)
		{
			pane.destroy(this);
		}
	}

	public void paint(NicoPane pane, Graphics g)
	{
		g.drawImage(_img.getImage(), _x-52, _y-32, 48, 48, pane);
		g.setFont(new Font("", Font.BOLD, 32));
		g.setColor(getBackColor());
		g.drawString(_comment, _x-1, _y-1);
		g.drawString(_comment, _x+1, _y-1);
		g.drawString(_comment, _x-1, _y+1);
		g.drawString(_comment, _x+1, _y+1);
		g.setColor(getMainColor());
		g.drawString(_comment, _x, _y);

		if(_isMyStatus)
		{
			g.setColor(getRectColor());
			g.drawRect(_x-2, _y-DISTANCE_Y+16, 32*_length, 48+4);
			g.drawRect(_x-1, _y-DISTANCE_Y+17, 32*_length-2, 48+2);
			g.drawRect(_x, _y-DISTANCE_Y+18, 32*_length-4, 48);
		}
	}

	private Color getRectColor()
	{
		return new Color(255, 255, 0, 255);
	}

	private Color getBackColor()
	{
		return new Color(0, 0, 0, 255);
	}

	private Color getMainColor()
	{
		return new Color(255, 255, 255, 255);
	}
}

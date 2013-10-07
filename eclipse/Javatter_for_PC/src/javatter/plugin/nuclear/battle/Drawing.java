package javatter.plugin.nuclear.battle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public enum Drawing
{
	Finish,
	None,
	PreBattle
	{
		@Override
		public DrawingResult Paint(Graphics g, ImageIcon me, ImageIcon en, String Me, String En, Dimension dim, ImageObserver ob)
		{
			g.setColor(Color.white); g.drawRect(0, 0, dim.width, dim.height);
			g.setFont(f);
			String n = "@"+Me+" VS @"+En;
			g.setColor(Color.black); g.drawString(n, getCenteringParam(dim.width, fs*(n.length()/2)), getCenteringParam(dim.width, fs));

			g.drawImage(me.getImage(), -200 + getCenteringParam(dim.width, me.getIconWidth()), getCenteringParam(dim.height, me.getIconHeight()), ob);
			g.drawImage(en.getImage(), +200 + getCenteringParam(dim.width, en.getIconWidth()), getCenteringParam(dim.height, en.getIconHeight()), ob);

			return DrawingResult.None;
		}
	},
	Beaming{
		@Override
		public DrawingResult Paint(Graphics g, ImageIcon me, ImageIcon en, String Me, String En, Dimension dim, ImageObserver ob)
		{
			g.setColor(Color.white); g.drawRect(0, 0, dim.width, dim.height);
			if(!IsAction) { IsAction = true; return DrawingResult.Action; }
			return DrawingResult.None;
		}
	},
	Result{
		@Override
		public DrawingResult Paint(Graphics g, ImageIcon me, ImageIcon en, String Me, String En, Dimension dim, ImageObserver ob)
		{
			g.setColor(Color.white); g.drawRect(0, 0, dim.width, dim.height);
			return DrawingResult.Finish;
		}
	},;

	private static final int fs = 16;
	private static final Font f = new Font("Consolas", Font.PLAIN, fs);
	private static boolean IsAction = false;

	public DrawingResult Paint(Graphics g, ImageIcon me, ImageIcon en, String Me, String En, Dimension dim, ImageObserver ob)
	{
		return DrawingResult.None;
	}

	public static void EnableReuseAction()
	{
		IsAction = false;
	}

	private static int getCenteringParam(int size, int csize)
	{
		return size / 2 - csize / 2;
	}
}

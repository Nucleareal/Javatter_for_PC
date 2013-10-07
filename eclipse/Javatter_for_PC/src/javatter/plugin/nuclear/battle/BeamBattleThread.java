package javatter.plugin.nuclear.battle;

import java.net.URL;

import javatter.plugin.nuclear.SafeThread;
import javatter.plugin.nuclear.StatusUtils;

import javax.swing.ImageIcon;

import twitter4j.Status;
import twitter4j.User;

import com.orekyuu.javatter.util.IconCache;

public class BeamBattleThread extends SafeThread implements ActionAndFinishableListener
{
	private JavaBattleScreen scr;
	private Status status;
	private BattleType type;

	private String MyName = "orekyuu";
	private String EnName = MyName;
	private ImageIcon MyImg;
	private ImageIcon EnImg;

	public BeamBattleThread(Status s, BattleType t)
	{
		status = s;
		type = t;
	}

	private URL createURL(String url)
	{
		try {
			return new URL(url);
		} catch(Exception e) { }
		return null;
	}

	@Override
	protected void initialize()
	{
		User Enemy = StatusUtils.getOwner(status);
		User Me = BattleCash.getMyUser();
		MyName = Me.getScreenName();
		EnName = Enemy.getScreenName();
		MyImg = IconCache.getInstance().getIcon(createURL(Me.getProfileImageURL()));
		EnImg = IconCache.getInstance().getIcon(createURL(Enemy.getProfileImageURL()));

		scr = new JavaBattleScreen(MyName, EnName, MyImg, EnImg, this);
		scr.setVisible(true);
	}

	@Override
	protected void finalize()
	{
		Drawing.EnableReuseAction();
	}

	@Override
	protected void safeRun() throws Exception
	{
		try
		{
			scr.run();
		}
		catch(Exception e)
		{
		}
	}

	public void DoAction()
	{
		type.DoAction(status);
	}

	public void DoFinish()
	{
		scr.dispose();
		exit();
	}
}

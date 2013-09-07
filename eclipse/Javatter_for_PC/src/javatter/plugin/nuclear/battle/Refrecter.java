package javatter.plugin.nuclear.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Random;

import javatter.plugin.nuclear.StringUtil;

import javax.swing.JButton;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.view.MainWindowView;

public class Refrecter
{
	public static void tryIn(final MainWindowView mainView)
	{
		try
		{
			Class<MainWindowView> clazz = MainWindowView.class;
			Field f = clazz.getDeclaredField("javaButton");
			f.setAccessible(true);
			Object button = f.get(mainView);
			JButton jb = ((JButton)button);
			jb.removeActionListener(mainView);
			final Random rand = new Random();
			jb.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						TwitterManager.getInstance().getTwitter().updateStatus(mainView.getTweetTextArea().getText()+"Javaビーム"+StringUtil.repeat("ﾋﾞ", 3+rand.nextInt(20))+StringUtil.repeat("w", 3+rand.nextInt(20)));
					}
					catch(Exception f)
					{
					}
				}
			});
		}
		catch(Exception e)
		{
		}
	}
}

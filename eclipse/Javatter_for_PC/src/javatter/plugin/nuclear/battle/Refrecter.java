package javatter.plugin.nuclear.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.util.TwitterUtil;
import com.orekyuu.javatter.view.MainWindowView;

public class Refrecter
{
	public static void tryIn(final MainWindowView mainView)
	{
		try
		{
			Class<MainWindowView> clazz = MainWindowView.class;
			Field f = clazz.getDeclaredField("javaButton");
			Field u = clazz.getDeclaredField("util");
			f.setAccessible(true);
			u.setAccessible(true);
			Object button = f.get(mainView);
			Object util = u.get(mainView);
			JButton jb = ((JButton)button);
			final TwitterUtil tu = (TwitterUtil)util;
			jb.removeActionListener(mainView);
			jb.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						if(BeamStatus.get().isAvailableBeam())
						{
							if(PluginMain._data.getBoolean("isAlertOnBeam"))
							{
								int res = JOptionPane.showConfirmDialog(null, "本当にJavaビームを撃ちますか？\n今ならまだ踏みとどまって人間でいられますよ？", "警告", JOptionPane.YES_NO_OPTION);
								if(JOptionPane.YES_OPTION != res)
								{
									return;
								}
							}

							String sx = JavatterBeamPulser.shot(mainView.getTweetTextArea().getText());
							tu.tweet(TwitterManager.getInstance().getTwitter(), sx);
							mainView.getTweetTextArea().setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Javaパワー不足です！");
						}
					}
					catch(Exception f)
					{
						f.printStackTrace();
					}
				}
			});
			BeamButtonRefresher bbr = new BeamButtonRefresher(jb);
			BeamStatus.get().setRefresher(bbr);
			bbr.refreshButton();
		}
		catch(Exception e)
		{
		}
	}
}

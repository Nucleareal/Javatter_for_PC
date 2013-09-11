package javatter.plugin.nuclear.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Random;

import javatter.plugin.nuclear.StringUtil;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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

							if(BeamStatus.get().isMaxPower() && new Random().nextInt(16) == 0)
							{
								String st = "雄大なるJavaの歴史よ、太古よりのJava神よ、我にJavaの力を与え給え。今、Javaの力を解き放つ！Java波動七式";
								String sk = StringUtil.repeat("！", 7+rand.nextInt(20));
								String se = " "+getFooter(rand.nextInt());
								BeamStatus.get().decr();
								TwitterManager.getInstance().getTwitter().updateStatus(st+sk+se);
								return;
							}

							BeamStatus.get().decr();
							String be = StringUtil.repeat("ﾋﾞ", 3+rand.nextInt(20));
							String ww = StringUtil.repeat("w", 3+rand.nextInt(20));
							String sx = mainView.getTweetTextArea().getText()+"Javaビーム"+be+ww;
							if(BeamStatus.get().getRandomEmet())
							{
								be = StringUtil.repeat("ﾎﾞ", 3+rand.nextInt(10));
								ww = StringUtil.repeat("…", 3+rand.nextInt(10));
								sx = "Javaスライムｼﾞｮ"+be+ww;
							}
							TwitterManager.getInstance().getTwitter().updateStatus(sx);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Javaパワー不足です！");
						}
					}
					catch(Exception f)
					{
					}
				}

				private String getFooter(int rand)
				{
					for(;rand%13!=0;rand++);
					return String.valueOf(rand);
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

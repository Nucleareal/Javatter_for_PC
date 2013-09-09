package javatter.plugin.nuclear.battle;

import java.util.Random;

import javatter.plugin.nuclear.NumberUtil;
import javatter.plugin.nuclear.PluginModelAdapter;
import javatter.plugin.nuclear.StatusUtils;
import javatter.plugin.nuclear.StringUtil;
import twitter4j.Status;
import twitter4j.StatusUpdate;

import com.orekyuu.javatter.account.TwitterManager;

public class JavatterBattleModel extends PluginModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		if(StatusUtils.isnRetweet(status) && JavaBattleUtil.isJavaBeam(status))
		{
			try
			{
				if(!PluginMain._data.getBoolean("isAvailable"))
				{
					return;
				}
				if(status.getUser().getId() == TwitterManager.getInstance().getTwitter().getId())
				{
					return;
				}
			}
			catch(Exception e)
			{
				return;
			}

			String header = StatusUtils.getInReplyToHeader(status);
			Random r0 = new Random(status.getId());
			Random r1 = new Random(System.currentTimeMillis());
			int at = r0.nextInt(101);
			int df = r1.nextInt(101);

			at = (int)(10 * Math.sqrt(at));
			df = (int)(10 * Math.sqrt(df));

			String s0 = String.format("%03d", at);
			String s1 = String.format("%03d", df);

			String sl = "Javaガード"+StringUtil.repeat("ｶﾞ", 3+r1.nextInt(10))+StringUtil.repeat("w", 3+r1.nextInt(10));
			if(df > 97)
			{
				df = 101;
				s1 = "Inf";
				sl = "30億Javaデバイスガード"+StringUtil.repeat("！", r1.nextInt(10));
			}

			String text = sl+" [戦果|攻撃側:"+s0+" VS 守備側:"+s1+"] "+NumberUtil.getBaranceResult(at, df, "防衛失敗", "防衛成功", "均衡状態")+"です！";
			StatusUpdate su = new StatusUpdate(header+text);
			su.setInReplyToStatusId(status.getId());

			try
			{
				TwitterManager.getInstance().getTwitter().updateStatus(su);
			}
			catch(Exception e)
			{
			}
		}
	}
}

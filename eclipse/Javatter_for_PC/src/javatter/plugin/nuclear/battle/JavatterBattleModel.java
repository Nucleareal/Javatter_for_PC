package javatter.plugin.nuclear.battle;

import java.util.Random;

import javatter.plugin.nuclear.NumberUtil;
import javatter.plugin.nuclear.ModelAdapter;
import javatter.plugin.nuclear.StatusUtils;
import javatter.plugin.nuclear.StringUtil;

import javax.swing.JOptionPane;

import twitter4j.Status;
import twitter4j.StatusUpdate;

import com.orekyuu.javatter.account.TwitterManager;

public class JavatterBattleModel extends ModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		if(PluginMain._data.getBoolean("isAvailable") && StatusUtils.isnRetweet(status))
		{
			try
			{
				if(status.getUser().getId() == TwitterManager.getInstance().getTwitter().getId())
				{
					return;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
			if(JavaBattleUtil.isJavaBeam(status))
			{
				if(!PluginMain._data.getBoolean("isAutoGuard"))
				{
					boolean isAlert = !BeamStatus.get().isFullPower();
					String str = ( isAlert ? ("攻撃力が予測ができません\n") : ("相手の予測攻撃力\n"+getPower(status.getId())+"\n" ) ) + "防御しますか？";
					int res = JOptionPane.showConfirmDialog(null, str, "", JOptionPane.YES_NO_OPTION);
					if(res != JOptionPane.YES_OPTION) return;
				}

				String header = StatusUtils.getInReplyToHeader(status);
				Random r1 = new Random(System.currentTimeMillis());
				int at = getPower(status.getId());
				int df = usePower(System.currentTimeMillis());

				String s0 = String.format("%03d", at);
				String s1 = String.format("%03d", df);

				String sl = "Javaガード"+StringUtil.repeat("ｶﾞ", 3+r1.nextInt(10))+StringUtil.repeat("w", 3+r1.nextInt(10));
				if(df > 97)
				{
					df = 101;
					s1 = "Inf";
					sl = (r1.nextInt(8) == 0 ? "Javaカリスマ" : "30億Javaデバイス")+"ガード"+StringUtil.repeat("！", r1.nextInt(10));
				}

				String text = sl+" [戦果|攻撃側:"+s0+" VS 守備側:"+s1+"] "+NumberUtil.getBaranceResult(at, df, "防衛失敗", "防衛成功", "均衡状態")+"です！";
				StatusUpdate su = new StatusUpdate(header+text);
				su.setInReplyToStatusId(status.getId());

				if(at - df > 0) { BeamStatus.get().addDamage(at-df); }

				try
				{
					TwitterManager.getInstance().getTwitter().updateStatus(su);
				}
				catch(Exception e)
				{
				}
			}
			if(JavaBattleUtil.isJavaLazer(status))
			{
				BeamStatus.get().addDamage(100);
			}
			if(JavaBattleUtil.isJavaSlime(status))
			{
				String header = StatusUtils.getInReplyToHeader(status);
				String text = "そのスライム、斬らせてもらう"+StringUtil.repeat("！", 3+new Random().nextInt(10));

				StatusUpdate su = new StatusUpdate(header+text);
				su.setInReplyToStatusId(status.getId());
				try
				{
					TwitterManager.getInstance().getTwitter().updateStatus(su);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			if(JavaBattleUtil.isSimeSlayer(status))
			{
				BeamStatus.get().addDamage(20+new Random().nextInt(31));
			}
		}
	}

	public int getPower(long seed)
	{
		return (int)(10 * Math.sqrt(new Random(seed).nextInt(BeamStatus.get().nodecr()+1)));
	}

	public int usePower(long seed)
	{
		return (int)(10 * Math.sqrt(new Random(seed).nextInt(BeamStatus.get().decr()+1)));
	}
}

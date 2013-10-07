package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.ModelAdapter;
import javatter.plugin.nuclear.StatusUtils;
import twitter4j.Status;

import com.orekyuu.javatter.account.TwitterManager;

public class JavatterBattleModel extends ModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		if(PluginMain._data.getBoolean("isAvailable") && StatusUtils.isnRetweet(status))
		{
			try {
				if(status.getUser().getId() == TwitterManager.getInstance().getTwitter().getId()) return;
			} catch(Exception e) { return; }

			BattleType bp = null;
			bp = JavaBattleUtil.isJavaBeam(status) ? BattleType.BeamToGuard : bp;
			bp = JavaBattleUtil.isJavaLazer(status) ? BattleType.JavaLazer : bp;
			bp = JavaBattleUtil.isJavaSlime(status) ? BattleType.Slime : bp;
			bp = JavaBattleUtil.isSimeSlayer(status) ? BattleType.Slayer : bp;

			if(bp != null)
			{
				BeamBattleThread thread = new BeamBattleThread(status, bp);
				thread.start();
			}
		}
	}
}

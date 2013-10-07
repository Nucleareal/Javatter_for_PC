package javatter.plugin.nuclear.battle;

import java.util.Random;

import javatter.plugin.nuclear.NumberUtil;
import javatter.plugin.nuclear.StatusUtils;
import javatter.plugin.nuclear.StringUtil;

import javax.swing.JOptionPane;

import twitter4j.Status;
import twitter4j.StatusUpdate;

import com.orekyuu.javatter.account.TwitterManager;

public enum BattleType
{
	BeamToGuard{
		@Override
		public void DoAction(Status status)
		{
			int EnPower = getPower(status.getId());
			String EnPowerStr = String.format("%03d", EnPower);

			if(!PluginMain._data.getBoolean("isAutoGuard"))
			{
				String msg = ( BeamStatus.get().isFullPower() ? ("相手の予測攻撃力:"+EnPower ) : ("攻撃力が予測ができません")) + "\n防御しますか？\n";
				if(JOptionPane.showConfirmDialog(null, msg, "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
			}

			int MyPower = usePower(System.currentTimeMillis());
			String MyPowerStr = String.format("%03d", MyPower);

			//Try to Guard for Beam
			String header = StatusUtils.getInReplyToHeader(status);

			String PostResult = "Javaガード"+StringUtil.repeat("ｶﾞ", 3+nextInt(10))+StringUtil.repeat("w", 3+nextInt(10));
			if(MyPower > 97)
			{
				MyPower = 101;
				PostResult = (nextInt(8) == 0 ? "VMクラッシュ" : "30億Javaデバイス")+"ガード"+StringUtil.repeat("！", nextInt(10));
			}

			String text = PostResult+
					" [戦果|攻撃側:"+EnPowerStr+" VS 守備側:"+MyPowerStr+"] "+
					NumberUtil.getBaranceResult(EnPower, MyPower, "防衛失敗", "防衛成功", "均衡状態")+"です！";
			StatusUpdate su = new StatusUpdate(header+text);
			su.setInReplyToStatusId(status.getId());

			int Damage = EnPower - MyPower;
			if(Damage > 0)
				BeamStatus.get().addDamage(Damage);

			BattleHistory.getIns().AddHistory(""+
					System.currentTimeMillis()+
					": @"+BattleCash.getMyUser().getScreenName()+" VS "+
					"@"+StatusUtils.getOwner(status).getScreenName()+" ["+
					" At:"+EnPower+
					" Df:"+MyPower+
					" ]"+
					NumberUtil.getBaranceResult(EnPower, MyPower, "Failed("+Damage+")", "Success", "Draw")
					);

			try {
				TwitterManager.getInstance().getTwitter().updateStatus(su);
			} catch(Exception e) { }
		}
	},
	JavaLazer
	{
		@Override
		public void DoAction(Status status)
		{
			BeamStatus.get().addDamage(100);
		}
	},
	Slime{
		@Override
		public void DoAction(Status status)
		{
			String header = StatusUtils.getInReplyToHeader(status);
			String text = "そのスライム、斬らせてもらう"+StringUtil.repeat("！", 3+nextInt(10));

			StatusUpdate su = new StatusUpdate(header+text);
			su.setInReplyToStatusId(status.getId());

			try {
				TwitterManager.getInstance().getTwitter().updateStatus(su);
			} catch(Exception e) { }
		}
	},
	Slayer{
		@Override
		public void DoAction(Status status)
		{
			BeamStatus.get().addDamage(20+nextInt(31));
		}
	},;

	public void DoAction(Status status)
	{
	}

	public int getPower(long seed)
	{
		Random rand = new Random();
		rand.setSeed(seed);
		return StateCalculator.getState(rand, BeamStatus.get().nodecr()+1);
	}

	public int usePower(long seed)
	{
		Random rand = new Random();
		rand.setSeed(seed);
		return StateCalculator.getState(rand, BeamStatus.get().decr()+1);
	}

	public int nextInt(int n)
	{
		return new Random().nextInt(n);
	}
}

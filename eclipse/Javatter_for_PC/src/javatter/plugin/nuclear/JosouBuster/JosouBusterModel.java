package javatter.plugin.nuclear.JosouBuster;

import java.util.Random;

import javatter.plugin.nuclear.ModelAdapter;
import javatter.plugin.nuclear.StatusUtils;
import javatter.plugin.nuclear.StringUtil;
import twitter4j.Status;

public class JosouBusterModel extends ModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		long UserID = StatusUtils.getOwner(status).getId();
		String UserSN = StatusUtils.getOwner(status).getScreenName();
		if(StatusUtils.isnRetweet(status))
		{
			if(JosouBusterUtil.isEnemyTweet(status)) //ワンチャンセックスハードッコイ
			{
				System.out.println("Enemy Appeared.");

				if(JosouBusterStock.IsRegistered(UserID)) //かつ登録済み
				{
					JosouBusterStock.ResetBusterCounter(UserID); //リセット
				}
				else
				{
					if(JosouBusterStock.Enqueue(UserID)) //ワンチャンセックスハードッコイなら
					{
						JosouBusterStock.Save(); //敵とみなす
						NotifyEnemy(UserSN);
					}
					if(isWaitingMode)
					{
						Counter++; //釣り上げ中ならカウンタを回す
					}
				}
			}
			//ワンチャンセックスハードッコイでない
			else if(JosouBusterStock.IsRegistered(UserID))
			{
				JosouBusterStock.IncrimentBusterCounter(UserID);
			}
		}
	}

	private static volatile boolean isWaitingMode = false;
	private static volatile int Counter = 0;

	public static void JosouBuster()
	{
		if(!isWaitingMode)
		{
			EnableWait();
			JosouBusterWaitThread.Start();
			JosouBusterUtil.Post("女装 ["+System.currentTimeMillis()+"]"+StringUtil.repeat("　", new Random().nextInt(10)));
		}
	}

	public static void Fishing()
	{
		DisableWait();
		if(Counter > 0)
		{
			JosouBusterUtil.Post("今回"+Counter+"件の女装を釣り上げました。");
		}
	}

	private static void EnableWait()
	{
		isWaitingMode = true;
		Counter = 0;
	}

	public static void DisableWait()
	{
		isWaitingMode = false;
	}

	private static void NotifyEnemy(String name)
	{
		JosouBusterUtil.Post("敵と想われるUserを確認。狙いを定めます [Targetting:"+name+"]");
	}
}

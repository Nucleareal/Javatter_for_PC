package javatter.plugin.nuclear.JosouBuster;

import javatter.plugin.nuclear.StatusUtils;
import twitter4j.Status;

import com.orekyuu.javatter.account.TwitterManager;

public class JosouBusterUtil
{
	public static boolean isEnemyTweet(Status status)
	{
		String text = StatusUtils.getText(status);
		return text.contains("女装男子") && text.contains("ワンチャンセックス") && text.contains("(っ＞ω＜c)");
	}

	public static void Post(String Text)
	{
		try
		{
			TwitterManager.getInstance().getTwitter().updateStatus(Text);
		}
		catch(Exception e)
		{
		}
	}
}

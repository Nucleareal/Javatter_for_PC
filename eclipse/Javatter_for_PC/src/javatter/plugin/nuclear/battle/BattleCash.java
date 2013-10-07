package javatter.plugin.nuclear.battle;

import twitter4j.User;

import com.orekyuu.javatter.account.TwitterManager;

public class BattleCash
{
	private static User MyUser;

	public static User getMyUser()
	{
		if(MyUser == null) try{ MyUser = TwitterManager.getInstance().getTwitter().verifyCredentials(); } catch(Exception e) { }
		return MyUser;
	}

}

package javatter.plugin.nuclear.multiaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.util.SaveData;

public class AccountController
{
	private List<AccountWrapper> _list;
	private User _owner;
	private static final String _acs0 = "nuclear.acs0:";
	private static final String _acs1 = "nuclear.acs1:";
	private static final Random _rand = new Random();
	private static final long _seed = 1341_398L;

	public void load(SaveData data)
	{
		refreshRandom();

		boolean cont = true;
		for(int i = 0; cont; i++)
		{
			String key = data.getString(_acs0+i);
			String val = data.getString(_acs1+i);

			if(key == val) break;

			AuthorizedData auth = TwitterAuthorizer.auth(CryptCoder.decode(_rand, key), CryptCoder.decode(_rand, val));

			if(auth.isAuthorized) _list.add(new AccountWrapper(auth.user, auth.twitter));
		}
	}

	public void save(SaveData data, int begin)
	{
		refreshRandom();

		for(int i = begin; i < _list.size(); i++)
		{
			Twitter twitter = _list.get(i)._twitter;

			String acs0 = "key";
			String acs1 = "val";
			AccessToken token = null;
			try
			{
				token = twitter.getOAuthAccessToken();
				acs0 = token.getToken();
				acs1 = token.getTokenSecret();
			}
			catch(Exception e)
			{
			}
			data.setString(_acs0+i, CryptCoder.encode(_rand, acs0));
			data.setString(_acs1+i, CryptCoder.encode(_rand, acs1));
		}
	}

	public void save(SaveData data)
	{
		save(data, 0);
	}

	public void add(SaveData data, AuthorizedData auth)
	{
		if(auth.isAuthorized)  _list.add(new AccountWrapper(auth.user, auth.twitter));

		save(data, _list.size()-1);
	}

	public User getOwnerUser()
	{
		if(null == _owner)
		{
			try
			{
				_owner = TwitterManager.getInstance().getTwitter().verifyCredentials();
			}
			catch(Exception e)
			{
			}
		}
		return _owner;
	}

	public void actionForAll(IMultiAccountAction actioner)
	{
		for(AccountWrapper aw : _list)
		{
			actioner.action(aw);
		}
	}

	private void refreshRandom()
	{
		_rand.setSeed(_seed);
	}

	private static final AccountController _ins = new AccountController();
	private AccountController()
	{
		_list = new ArrayList<AccountWrapper>();
	}
	public static final AccountController getInstance()
	{
		return _ins;
	}
}

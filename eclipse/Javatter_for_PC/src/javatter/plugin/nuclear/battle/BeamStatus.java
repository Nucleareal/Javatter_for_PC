package javatter.plugin.nuclear.battle;

import java.awt.Color;
import java.util.Random;

import javatter.plugin.nuclear.NumberUtil;

import javax.swing.JOptionPane;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.controller.UserStreamController;

public class BeamStatus extends UserStreamController implements UserStreamListener
{
	private BeamStatus(){}
	private static BeamStatus _ins = new BeamStatus();
	public static BeamStatus get(){return _ins;}


	private BeamButtonRefresher _bbr;
	private int _power;
	private int _damage;

	public void init()
	{
		_power = 0;
		_damage = 0;
		load();
	}

	public int decr()
	{
		int result = _power > 100 ? 100 : _power;
		_power -= result;
		_bbr.refreshButton();
		return result;
	}

	public int nodecr()
	{
		int result = _power > 100 ? 100 : _power;
		return result;
	}

	public boolean isMaxPower()
	{
		return _power >= 255;
	}

	public boolean isAvailableBeam()
	{
		return _power >= 64;
	}

	public void increment(int i)
	{
		_power += i;
		if(_power > 255) _power = 255;
		_bbr.refreshButton();
	}

	public Color getColor()
	{
		int c = NumberUtil.clamp(0, 255, _power-_damage);
		return new Color(_power, c, c);
	}

	public boolean isFullPower()
	{
		return _power >= 100;
	}

	public void addDamage(int i)
	{
		_damage += i;
		if(_damage > 255)
		{
			JOptionPane.showConfirmDialog(null, "あなたは死にました\n\n\nち～ん(笑)");
			System.exit(0);
		}
		else
		{
		}
	}

	public boolean isLeftPower()
	{
		return _power > 0;
	}

	public String getHP()
	{
		return "Java体力: "+(100 - (_damage*100)/255)+"%";
	}

	public String getText()
	{
		if(!isAvailableBeam())
			return "javaビーム";
		if(!isFullPower())
			return "Javaビーム";
		return "JAVAビーム";
	}

	public boolean getRandomEmet()
	{
		if(isFullPower()) return false;
		return new Random().nextInt(12) == 0;
	}

	public void setRefresher(BeamButtonRefresher bbr)
	{
		_bbr = bbr;
	}

	public void load()
	{
		PluginMain._data.setDefaultValue("JavaPower", _power);
		_power = PluginMain._data.getInt("JavaPower");
	}

	public void save()
	{
		PluginMain._data.setInt("JavaPower", _power);
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onScrubGeo(long arg0, long arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStallWarning(StallWarning arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStatus(Status arg0)
	{
		increment(2);
	}

	@Override
	public void onTrackLimitationNotice(int arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onException(Exception e)
	{
		System.out.println("JB Plugin Debug:");
		e.printStackTrace();
	}

	@Override
	public void onBlock(User arg0, User arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDeletionNotice(long arg0, long arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDirectMessage(DirectMessage arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onFavorite(User src, User target, Status s)
	{
		if(src.getId() == target.getId()) return;
		long id = -1L;
		try
		{
			id = TwitterManager.getInstance().getTwitter().getId();
		}
		catch (IllegalStateException | TwitterException e)
		{
			e.printStackTrace();
		}
		if(src.getId() == id)
		{
			merculy(1, true);
			return;
		}
		if(target.getId() == id)
		{
			merculy(4, false);
			return;
		}
		return;
	}

	private long prev = System.currentTimeMillis();

	private void merculy(int i, boolean isBomb)
	{
		long n = System.currentTimeMillis();
		if(isBomb && n - prev < 1000) return;
		_damage = NumberUtil.clamp(0, 255, _damage-i);
	}

	@Override
	public void onFollow(User arg0, User arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onFriendList(long[] arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUnblock(User arg0, User arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListCreation(User arg0, UserList arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserProfileUpdate(User arg0)
	{
		// TODO 自動生成されたメソッド・スタブ

	}

	/* UserStream Methods */
}

package javatter.plugin.nuclear.battle;

import java.awt.Color;
import java.util.Random;

import javatter.plugin.nuclear.NumberUtil;

import javax.swing.JOptionPane;

import twitter4j.Status;

import com.orekyuu.javatter.logic.UserStreamLogic;
import com.orekyuu.javatter.viewobserver.UserStreamViewObserver;

public class BeamStatus implements UserStreamLogic
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

	public String getHP()
	{
		return "Java体力: "+(100 - (_damage*100)/255)+"%";
	}

	public String getText()
	{
		if(!isAvailableBeam())
			return "まだ撃てない";
		if(!isFullPower())
			return "Javaビーム";
		return "Javaビーム！";
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
	public Status getStatus()
	{
		return null;
	}

	@Override
	public void onStatus(Status status)
	{
		increment(2);
	}

	@Override
	public void onReplyTweet(Status status)
	{
	}

	@Override
	public void onRetweetTweet(Status status)
	{
	}

	@Override
	public void setView(UserStreamViewObserver view)
	{
	}

	/* UserStream Methods */
}

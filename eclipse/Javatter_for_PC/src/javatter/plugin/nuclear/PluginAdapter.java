package javatter.plugin.nuclear;

import com.orekyuu.javatter.controller.UserStreamController;
import com.orekyuu.javatter.logic.UserStreamLogic;
import com.orekyuu.javatter.plugin.JavatterPlugin;
import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public abstract class PluginAdapter extends JavatterPlugin
{
	private UserStreamController _cont;
	private UserStreamLogic _logic;
	private Class<? extends PluginConfigAdapter> _cq;
	protected SaveData _data;

	protected boolean init(Class<? extends PluginModelAdapter> clazz, Class<? extends PluginConfigAdapter> claqq)
	{
		boolean result = true;
		_data = getSaveData();
		_cq = claqq;
		_cont = new UserStreamController();
		try
		{
			_logic = clazz.getConstructor().newInstance();
			_cont.setModel(_logic);
			addUserStreamListener(_cont);
		}
		catch(Exception e)
		{
			result = false;
		}
		return result;
	}

	protected void setDefault(String key, String value)
	{
		getSaveData().setDefaultValue(key, value);
	}

	protected void setDefault(String key, boolean value)
	{
		getSaveData().setDefaultValue(key, value);
	}

	protected void setDefault(String key, int value)
	{
		getSaveData().setDefaultValue(key, value);
	}

	protected void setDefault(String key, float value)
	{
		getSaveData().setDefaultValue(key, value);
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		IJavatterTab ins = null;
		try
		{
			ins = _cq.getConstructor(SaveData.class).newInstance(new Object[]{_data});
		}
		catch(Exception e)
		{
		}
		return ins;
	}

	@Override
	public String getPluginName()
	{
		return "Nuclear's";
	}

	@Override
	public String getVersion()
	{
		return "v1.0.0 for α";
	}
}

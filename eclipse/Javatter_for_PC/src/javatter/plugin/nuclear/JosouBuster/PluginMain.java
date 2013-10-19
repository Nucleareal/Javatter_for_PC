package javatter.plugin.nuclear.JosouBuster;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	protected static SaveData __data;

	@Override
	public void init()
	{
		super.init(JosouBusterModel.class, JosouBusterConfig.class);
		__data = getSaveData();
		__data.setDefaultValue("Enemy", "");
		JosouBusterStock.Load(__data.getString("Enemy"));
	}

	@Override
	public String getPluginName()
	{
		return "女装バスター";
	}

	@Override
	public String getVersion()
	{
		return "1 for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new JosouBusterConfig(getSaveData());
	}

	public static void SaveEnemies(String enemies)
	{
		__data.setString("Enemy", enemies);
	}
}

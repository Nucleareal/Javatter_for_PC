package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	protected static SaveData _data;

	@Override
	public void init()
	{
		super.init(JavatterBattleModel.class, JavatterBattleConfig.class);
		AudioWrapper.get().load();
		_data = getSaveData();
		_data.setDefaultValue("isAvailable", false);
		_data.setDefaultValue("isReplyBeam", false);
		Refrecter.tryIn(getMainView());
	}

	@Override
	public String getPluginName()
	{
		return "JavatterBattle Plugin";
	}

	@Override
	public String getVersion()
	{
		return "5 for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new JavatterBattleConfig(getSaveData());
	}
}

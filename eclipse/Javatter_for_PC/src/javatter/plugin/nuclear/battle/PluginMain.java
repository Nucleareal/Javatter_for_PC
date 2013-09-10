package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.controller.UserStreamController;
import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	protected static SaveData _data;
	private static PluginMain _ins;

	@Override
	public void init()
	{
		super.init(JavatterBattleModel.class, JavatterBattleConfig.class);
		AudioWrapper.get().load();
		_data = getSaveData();
		setDefault("isAvailable", false);
		setDefault("isReplyBeam", false);
		setDefault("isAutoGuard", true);
		setDefault("isAlertOnBeam", true);
		BeamStatus.get().init();
		UserStreamController us = new UserStreamController();
		us.setModel(BeamStatus.get());
		addUserStreamListener(us);

		Refrecter.tryIn(getMainView());

		_ins = this;
	}

	public static PluginMain get()
	{
		return _ins;
	}

	@Override
	public String getPluginName()
	{
		return "JavatterBattle Plugin";
	}

	@Override
	public String getVersion()
	{
		return "7 for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new JavatterBattleConfig(getSaveData());
	}
}

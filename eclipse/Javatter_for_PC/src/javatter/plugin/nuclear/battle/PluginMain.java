package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.CheckData;
import javatter.plugin.nuclear.PluginAdapter;
import javatter.plugin.nuclear.UpdateChecker;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	protected static SaveData _data;
	private static PluginMain _ins;
	public static final String NAME = "JavatterBattle";
	private static CheckData _cd;

	public PluginMain()
	{
		_cd = new CheckData();
		_cd.name = NAME;
		_cd.version = version();
	}

	public static CheckData getCheckData()
	{
		return _cd;
	}

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
		addUserStreamListener(BeamStatus.get());

		Refrecter.tryIn(getMainView());

		UpdateChecker.checkNeedToUpdate(version(), NAME);
		getMainView().getMainFrame().addWindowListener(new CloseSaver());

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
		return version()+" for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new JavatterBattleConfig(getSaveData());
	}

	public String version()
	{
		return "15";
	}
}

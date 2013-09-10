package javatter.plugin.nuclear.btob;

import com.orekyuu.javatter.controller.UserStreamController;
import com.orekyuu.javatter.plugin.JavatterPlugin;
import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class BeamToBeam extends JavatterPlugin
{
	private static BeamToBeam INSTAMCE;

	public static boolean isActive;
	public static boolean isMyself;

	public static String username;

	public BeamToBeam()
	{
		INSTAMCE = this;
	}

	@Override
	public String getPluginName()
	{
		return "Beam-To-Beam";
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

	@Override
	public void init()
	{
		username = this.userName;

		this.getSaveData().setDefaultValue("active", true);
		this.getSaveData().setDefaultValue("myself", false);

		isActive = this.getSaveData().getBoolean("active");
		isMyself = this.getSaveData().getBoolean("myself");

		UserStreamController usc = new UserStreamController();
		usc.setModel(new BeamListener());
		addUserStreamListener(usc);
	}

	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new BeamConfigView(this.getSaveData());
	}

	public static BeamToBeam getPlugin()
	{
		return INSTAMCE;
	}

	public SaveData getPluginSaveData()
	{
		return this.getSaveData();
	}
}
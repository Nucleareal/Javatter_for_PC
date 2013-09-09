package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.StatusUtils;
import twitter4j.Status;

import com.orekyuu.javatter.util.JavaBeamUtil;

public class JavaBattleUtil
{

	public static boolean isJavaBeam(Status status)
	{
		if(PluginMain._data.getBoolean("isReplyBeam") && StatusUtils.isReplyToMe(status))
		{
			String text = StatusUtils.removeMentions(status);
			return JavaBeamUtil.isJavaBeam(text);
		}
		return JavaBeamUtil.isJavaBeam(StatusUtils.getText(status));
	}

}

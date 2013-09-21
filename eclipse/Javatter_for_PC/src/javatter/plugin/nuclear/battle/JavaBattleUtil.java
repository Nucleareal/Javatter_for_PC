package javatter.plugin.nuclear.battle;

import java.util.regex.Pattern;

import javatter.plugin.nuclear.StatusUtils;
import twitter4j.Status;

import com.orekyuu.javatter.util.JavaBeamUtil;

public class JavaBattleUtil
{
	public static boolean isJavaBeam(Status status)
	{
		if(!StatusUtils.isFromJavatter(status))return false;

		if(PluginMain._data.getBoolean("isReplyBeam") && StatusUtils.isReplyToMe(status))
		{
			String text = StatusUtils.removeMentions(status);
			return JavaBeamUtil.isJavaBeam(text);
		}
		return JavaBeamUtil.isJavaBeam(StatusUtils.getText(status));
	}

	public static boolean isJavaLazer(Status status)
	{
		if(!StatusUtils.isFromJavatter(status)) return false;
		String begin = "雄大なるJavaの歴史よ、太古よりのJava神よ、我にJavaの力を与え給え。今、Javaの力を解き放つ！Java波動七式";
		String text = StatusUtils.removeMentions(status);
		boolean result = text.startsWith(begin);
		String[] arr = text.split(" ");
		for(String s : arr)
		{
			try
			{
				Integer i = Integer.valueOf(s);
				result &= i.intValue() % 17 == 0;
			}
			catch(Exception e)
			{
			}
		}
		return result;
	}

	public static boolean isJavaSlime(Status status)
	{
		if(!StatusUtils.isFromJavatter(status)) return false;
		String text = StatusUtils.removeMentions(status);
		String regex = "Javaスライムｼﾞｮ(ﾎﾞ)+[…]+";
		Pattern p = Pattern.compile(regex);
		return p.matcher(text).find();
	}

	public static boolean isSimeSlayer(Status status)
	{
		if(!StatusUtils.isFromJavatter(status)) return false;
		if(!StatusUtils.isReplyToMe(status)) return false;
		Status s = StatusUtils.getInReplyToStatus(status);
		if(!StatusUtils.isMyStatus(s) || !isJavaSlime(s)) return false;
		String begin = "そのスライム、斬らせてもらう";
		String text = StatusUtils.removeMentions(status);
		String regex = begin+"！+";
		Pattern p = Pattern.compile(regex);
		return p.matcher(text).find();
	}
}

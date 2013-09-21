package javatter.plugin.nuclear.battle;

import java.util.Random;

import javatter.plugin.nuclear.StringUtil;

public class JavatterBeamPulser
{
	public static String shot(String text)
	{
		if(BeamStatus.get().isMaxPower() && eq0(16)) return special(text);
		BeamStatus.get().decr();
		if(BeamStatus.get().getRandomEmet()) return slime(text);
		return beam(text);
	}

	private static String special(String text)
	{
		String sx = text+"雄大なるJavaの歴史よ、太古よりのJava神よ、我にJavaの力を与え給え。今、Javaの力を解き放つ！Java波動七式";
		sx += StringUtil.repeat("！", 3+rand(10));
		sx += " "+getFooter();
		BeamStatus.get().decr();
		return sx;
	}

	private static String slime(String text)
	{
		String be = StringUtil.repeat("ﾎﾞ", 3+rand(10));
		String ww = StringUtil.repeat("…", 3+rand(10));
		String sx = text+"Javaスライムｼﾞｮ"+be+ww;
		return sx;
	}

	private static String beam(String text)
	{
		String be = StringUtil.repeat("ﾋﾞ", 3+rand(20));
		String ww = StringUtil.repeat("w", 3+rand(20));
		String sx = text+"Javaビーム"+be+ww;
		return sx;
	}

	private static String getFooter()
	{
		int a = rand();
		for(; a % 17 == 0; a++);
		return String.valueOf(a);
	}

	private static boolean eq0(int n)
	{
		return new Random().nextInt(n) == 0;
	}

	private static int rand()
	{
		return new Random().nextInt();
	}

	private static int rand(int n)
	{
		return new Random().nextInt(n);
	}
}

package javatter.plugin.nuclear;

public class NumberUtil
{

	public static String getBaranceResult(int at, int df, String lt, String gt, String eq)
	{
		if(at > df) return lt;
		if(df > at) return gt;
		return eq;
	}

}

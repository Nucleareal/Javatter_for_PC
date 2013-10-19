package javatter.plugin.nuclear.JosouBuster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JosouBusterStock
{
	private static List<Long> EnemyIDs;
	private static HashMap<Long, Integer> EnemyCounter;

	public static boolean Enqueue(long id)
	{
		boolean res = EnemyIDs.contains(Long.valueOf(id));
		if(!res)
		{
			EnemyIDs.add(Long.valueOf(id));
			EnemyCounter.put(Long.valueOf(id), Integer.valueOf(0));
		}
		return !res;
	}

	public static void Load(String data)
	{
		String[] arr = data.split(",");
		for(String s : arr)
		{
			try
			{
				long l = Long.valueOf(s).longValue();
				EnemyIDs.add(Long.valueOf(l));
			}
			catch(Exception e)
			{
			}
		}
	}

	public static void Save()
	{
		String result = "";
		for(Long l : EnemyIDs)
		{
			result += l.longValue() + " ";
		}
		result = result.trim().replaceAll(" ", ",");
		PluginMain.SaveEnemies(result);
	}

	public static boolean IsRegistered(long id)
	{
		return EnemyIDs.contains(Long.valueOf(id));
	}

	public static void IncrimentBusterCounter(long id)
	{
		Integer value = EnemyCounter.get(Long.valueOf(id));
		if(value.intValue() >= 10)
		{
			JosouBusterModel.JosouBuster();
			value = Integer.valueOf(-1);
		}
		EnemyCounter.put(Long.valueOf(id), Integer.valueOf(value.intValue()+1));
	}

	public static void ResetBusterCounter(long id)
	{
		EnemyCounter.put(Long.valueOf(id), Integer.valueOf(0));
	}

	static
	{
		EnemyIDs = new ArrayList<>();
		EnemyCounter = new HashMap<>();
	}
}

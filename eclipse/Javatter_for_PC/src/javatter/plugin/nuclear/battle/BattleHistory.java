package javatter.plugin.nuclear.battle;

import java.util.ArrayDeque;
import java.util.Deque;

public class BattleHistory
{
	static
	{
		Instance = new BattleHistory();
	}
	private BattleHistory()
	{
	}
	public static final BattleHistory Instance;
	public static BattleHistory getIns()
	{
		return Instance;
	}

	private Deque<String> queue;

	{
		queue = new ArrayDeque<String>();
	}

	public void AddHistory(String result)
	{
		queue.addLast(result);
		System.out.println(result);
	}
}

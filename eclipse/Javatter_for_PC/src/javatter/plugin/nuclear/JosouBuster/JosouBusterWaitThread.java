package javatter.plugin.nuclear.JosouBuster;

public class JosouBusterWaitThread
{
	public static void Start()
	{
		JosouBusterWaitThreadWorker thread = new JosouBusterWaitThreadWorker();
		thread.start();
	}

	public static void OnCallBack()
	{
		JosouBusterModel.Fishing();
	}
}

package javatter.plugin.nuclear.JosouBuster;

public class JosouBusterWaitThreadWorker extends Thread
{
	public void run()
	{
		try
		{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
		}
		JosouBusterWaitThread.OnCallBack();
	}
}

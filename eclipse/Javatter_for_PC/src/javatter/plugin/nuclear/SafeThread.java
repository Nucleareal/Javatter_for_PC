package javatter.plugin.nuclear;

public abstract class SafeThread extends Thread
{
	private boolean _isRunning = true;

	public void enableThread()
	{
		_isRunning = true;
	}

	public void run()
	{
		while(_isRunning)
		{
			try
			{
				safeRun();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				exit();
			}
		}
	}

	public void exit()
	{
		_isRunning = false;
	}

	protected abstract void safeRun() throws Exception;
}

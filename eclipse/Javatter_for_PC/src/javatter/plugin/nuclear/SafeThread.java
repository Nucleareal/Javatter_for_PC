package javatter.plugin.nuclear;

public abstract class SafeThread extends Thread
{
	private boolean _isRunning = true;

	public void enableThread()
	{
		_isRunning = true;
	}

	protected abstract void initialize();

	protected abstract void finalize();

	public void run()
	{
		initialize();
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
		finalize();
	}

	public void exit()
	{
		_isRunning = false;
	}

	protected abstract void safeRun() throws Exception;
}

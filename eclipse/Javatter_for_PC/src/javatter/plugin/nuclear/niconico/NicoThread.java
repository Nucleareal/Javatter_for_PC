package javatter.plugin.nuclear.niconico;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javatter.plugin.nuclear.SafeThread;
import twitter4j.Status;

public class NicoThread extends SafeThread
{
	private BlockingQueue<Status> _queue;
	private int _counter;

	@Override
	protected void initialize()
	{
		_counter = 0;
		try
		{
			Controller.initialize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		_queue = new ArrayBlockingQueue<>(100, true);
	}

	@Override
	protected void finalize()
	{
	}

	@Override
	protected void safeRun() throws Exception
	{
		if(!_queue.isEmpty())
		{
			Status s = _queue.poll();
			Controller.onStatus(s);
		}
		Controller.repaint();
		if(++_counter % 60 == 0) refreshFontSpeed();
	}

	private void refreshFontSpeed()
	{
		PluginMain.refreshFontSpeed();
	}

	public void onStatus(Status status)
	{
		_queue.add(status);
	}
}

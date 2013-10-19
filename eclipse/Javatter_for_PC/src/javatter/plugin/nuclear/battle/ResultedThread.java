package javatter.plugin.nuclear.battle;

import javatter.plugin.nuclear.SafeThread;

public class ResultedThread extends SafeThread
{
	private ActionAndFinishableListener handler;
	private DrawingResult res;

	public ResultedThread(ActionAndFinishableListener _handler, DrawingResult ress)
	{
		handler = _handler;
		res = ress;
	}

	@Override
	protected void initialize()
	{
	}

	@Override
	protected void finalize()
	{
		switch(res)
		{
			case Action : handler.DoAction(); break;
			case Finish : handler.DoFinish(); break;
			default:
				break;
		}
	}

	@Override
	protected void safeRun() throws Exception
	{
		exit();
	}
}

package javatter.plugin.nuclear.battle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Audio
{
	public Clip clip;
	public AudioInputStream stream;


	public void open()
	{
		try
		{
			clip.open(stream);
		}
		catch(Exception e)
		{
		}
	}

	public boolean play()
	{
		boolean res;
		if(res = !clip.isActive())
		{
			clip.setFramePosition(0);
			clip.start();
		}
		return res;
	}
}

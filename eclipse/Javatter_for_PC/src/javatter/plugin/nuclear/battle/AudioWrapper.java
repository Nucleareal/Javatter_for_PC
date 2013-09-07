package javatter.plugin.nuclear.battle;

import java.net.URL;
import java.util.HashMap;

import javatter.plugin.nuclear.AlertUtil;

import javax.sound.sampled.AudioSystem;

public class AudioWrapper
{
	private HashMap<String, Audio> _cp;

	public void init()
	{
		_cp = new HashMap<>();
	}

	public void load()
	{
		load("encount", "encount");
	}

	public void load(String name, String filename)
	{
		try
		{
			Audio au = new Audio();
			final URL url = getClass().getResource("/sound/"+filename+".wav");
			au.stream = AudioSystem.getAudioInputStream(url);
			au.clip = AudioSystem.getClip();
			au.open();
			_cp.put(name, au);

			AlertUtil.show(getClass().getName());
		}
		catch(Exception e)
		{
		}
	}

	public void play(String name)
	{
		if(_cp.containsKey(name))
		{
			if(_cp.get(name).play());
		}
	}


	private AudioWrapper(){}
	private static final AudioWrapper _ins = new AudioWrapper();
	public static AudioWrapper get()
	{
		return _ins;
	}
}

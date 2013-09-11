package javatter.plugin.nuclear;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class URIOpener implements ActionListener
{
	private URI _uri;

	public URIOpener(String uri)
	{
		try
		{
			_uri = new URI(uri);
		}
		catch(Exception e)
		{
		}
	}

	public URIOpener(URI uri)
	{
		_uri = uri;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Desktop.getDesktop().browse(_uri);
		}
		catch(Exception l)
		{
		}
	}

}

package javatter.plugin.nuclear.multiaccount;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import com.orekyuu.javatter.account.TwitterManager;

public class TwitterAuthorizer
{
	public static AuthorizedData auth(String key, String val)
	{
		AuthorizedData data = new AuthorizedData();

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(TwitterManager.getInstance().getConsumerKey());
		builder.setOAuthConsumerSecret(TwitterManager.getInstance().getConsumerSecret());
		builder.setOAuthAccessToken(key);
		builder.setOAuthAccessTokenSecret(val);
		Twitter twitter = new TwitterFactory(builder.build()).getInstance();
		try
		{
			data.twitter = twitter;
			data.user = twitter.verifyCredentials();
		}
		catch (TwitterException e)
		{
			data.isAuthorized = false;
		}
		return data;
	}

	public static AuthorizedData auth()
	{
		AuthorizedData data = new AuthorizedData();
		try
		{
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(TwitterManager.getInstance().getConsumerKey(), TwitterManager.getInstance().getConsumerSecret());
			RequestToken request = twitter.getOAuthRequestToken();
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URI(request.getAuthorizationURL()));

			Object value = JOptionPane.showInputDialog(null, "PINコードを入力してください");
			if(null != value)
			{
				AccessToken token = twitter.getOAuthAccessToken(request, (String)value);
				data = auth(token.getToken(), token.getTokenSecret());
			}
		}
		catch(Exception e)
		{
			data.isAuthorized = false;
		}
		return data;
	}
}

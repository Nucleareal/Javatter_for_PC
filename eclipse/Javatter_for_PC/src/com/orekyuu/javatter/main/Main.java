package com.orekyuu.javatter.main;

import java.math.BigInteger;
import java.net.MalformedURLException;

import twitter4j.TwitterException;

public class Main {

	public static void main(String[] args) throws MalformedURLException, TwitterException {

		/*SaveDataManager.getInstance().init();
		JavatterConfig.getInstance().init();

		MainWindowView view=new MainWindowView();
		MainWindowController controller=new MainWindowController(view);
		view.setTweetController(controller);

		view.create();
		controller.start();*/

			int[] ar = new int[] { 561, 1105, 1729, 2465, 2821, 6601, 8911, 10585, 15841, 29341, 41041, 46657, 52633, 62745, 63973, 75361, 101101, 115921, 126217, 162401, 172081, 188461, 252601, 278545, 294409, 314821, 334153, 340561, 399001, 410041, 449065, 488881, 512461 };

		for(int i = 0; i < ar.length; i++)
			System.out.println(new BigInteger(String.valueOf(ar[i])).isProbablePrime(1));
	}
}

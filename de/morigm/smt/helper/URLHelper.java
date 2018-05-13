package de.morigm.smt.helper;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class URLHelper 
{
		public static Integer getResponseCode(String url)
		{
			String uri = url.replaceAll("://", " ").split(" ")[0];
			try 
			{
				if(uri.equalsIgnoreCase("http"))
				{
					HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		            return con.getResponseCode();
				}
				if(uri.equalsIgnoreCase("https"))
				{
					HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
		            return con.getResponseCode();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return null;
		}
}

package de.morigm.smt.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPDownloader implements Downloader {

    @Override
    public InputStream getInput(String s) 
    {
        try 
        {
            HttpURLConnection con = (HttpURLConnection) new URL(s).openConnection();
            return con.getInputStream();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(HTTPDownloader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
}

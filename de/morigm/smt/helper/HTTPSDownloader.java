package de.morigm.smt.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

public class HTTPSDownloader implements Downloader {

    @Override
    public InputStream getInput(String s) 
    {
        try 
        {
            HttpsURLConnection con = (HttpsURLConnection) new URL(s).openConnection();
            return con.getInputStream();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(HTTPSDownloader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}

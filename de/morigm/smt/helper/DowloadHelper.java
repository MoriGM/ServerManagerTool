package de.morigm.smt.helper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DowloadHelper {
    
    Map<String,Downloader> downloaders = new HashMap<>();
    
    public void registerDownloaders()
    {
       downloaders.put("http", new HTTPDownloader());
       downloaders.put("https", new HTTPSDownloader());
    }
    
    public InputStream getDownloadStream(String s)
    {
        String uri = s.replaceAll("::/", " ").split(" ")[0];
        if(!downloaders.containsKey(uri))
            return null;
        return downloaders.get(uri).getInput(s);
    }
}

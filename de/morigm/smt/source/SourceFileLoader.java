package de.morigm.smt.source;

import de.morigm.smt.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SourceFileLoader 
{
    List<SourceFolder> folder = new ArrayList<>();
    
    public void load()
    {
        try 
        {
            for(SourceData sc : Main.getInstance().getSourceListManager().getSources())
            {
                URL url = new URL(sc.getConnection() + "list");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while(reader.ready())
                {
                    String s = reader.readLine();
                    folder.add(new SourceFolder(s.split(" ")[0], sc.getConnection(),sc.getVersion()));
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public List<SourceFolder> getFolder() 
    {
        return folder;
    }

   
    
    
}
